package com.cryp73r.passbook.service;

import com.cryp73r.passbook.dto.UserDTO;
import com.cryp73r.passbook.exception.BadCredentialsException;
import com.cryp73r.passbook.exception.InternalServerException;
import com.cryp73r.passbook.mapper.UserMapper;
import com.cryp73r.passbook.model.User;
import com.cryp73r.passbook.repository.UserRepository;
import com.cryp73r.passbook.security.jwt.JwtUtils;
import com.cryp73r.passbook.security.service.ClientUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleService roleService;

    @Autowired
    UserMapper userMapper;

    @Autowired
    private ClientUserDetailsService clientUserDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtils jwtUtils;

    public UserService() {
    }

    @Transactional
    public String signUp(UserDTO userDTO) {
        User user = userMapper.UserDTOToUser(userDTO);
        if (!userDTO.getRoleNames().isEmpty()) {
            for (String roleName : userDTO.getRoleNames()) {
                user.addRole(roleService.getRole(roleName));
            }
        } else {
            user.addRole(roleService.getRole("GENERAL_USER"));
        }
        try {
            userRepository.save(user);
            UserDetails userDetails = clientUserDetailsService.loadUserByUsername(user.getUsername());
            return "Bearer " + jwtUtils.generateTokenFromUsername(userDetails);
        } catch (Exception e) {
            throw new InternalServerException("Error encountered while saving or generating token");
        }
    }

    @Transactional
    public String logIn(UserDTO userDTO) {
        try {
            UserDetails userDetails = clientUserDetailsService.loadUserByUsername(userDTO.getUsername());
            if (passwordEncoder.matches(userDTO.getPassword(), userDetails.getPassword())) {
                return "Bearer " + jwtUtils.generateTokenFromUsername(userDetails);
            } else {
                throw new BadCredentialsException("Incorrect username or password");
            }
        } catch (UsernameNotFoundException e) {
            throw new BadCredentialsException("Incorrect username or password");
        }
    }

    public UserDTO getCurrentUser(String authenticatedUsername) {
        return userMapper.UserToUserDTO(userRepository.findByUsername(authenticatedUsername).orElseThrow(() -> new BadCredentialsException("User not found")));
    }

    @Transactional
    public void deleteUser(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new BadCredentialsException("User not found"));
        user.removeAllRoles();
        userRepository.delete(user);
    }
}
