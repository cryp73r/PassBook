package com.cryp73r.passbook.mapper;

import com.cryp73r.passbook.dto.UserDTO;
import com.cryp73r.passbook.model.Role;
import com.cryp73r.passbook.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class UserMapper {

    @Autowired
    PasswordEncoder passwordEncoder;

    public UserDTO UserToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName(user.getFirstName());
        if (user.getMiddleName() != null) userDTO.setMiddleName(user.getMiddleName());
        userDTO.setLastName(user.getLastName());
        userDTO.setDisplayName(user.getDisplayName());
        userDTO.setUsername(user.getUsername());
        List<String> roleNames = new LinkedList<>();
        for (Role role : user.getRoles()) {
            roleNames.add(role.getRoleName());
        }
        userDTO.setRoleNames(roleNames);
        return userDTO;
    }

    public User UserDTOToUser(UserDTO userDTO) {
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
         if (userDTO.getMiddleName() != null) user.setMiddleName(userDTO.getMiddleName());
        user.setLastName(userDTO.getLastName());
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setDisplayName(userDTO.getFirstName(), userDTO.getMiddleName(), userDTO.getLastName());
        return user;
    }
}
