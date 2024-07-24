package com.cryp73r.passbook.controller;

import com.cryp73r.passbook.dto.UserDTO;
import com.cryp73r.passbook.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    UserService userService;

    Logger logger = LogManager.getLogger(UserController.class);

    @PostMapping(value = "/user/signup", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> signUp(@RequestBody UserDTO userDto) throws JsonProcessingException {
        if (logger.isTraceEnabled()) {
            logger.trace("Signing up user: {} with below details", userDto.getFirstName());
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            logger.trace("UserDTO: {}", ow.writeValueAsString(userDto));
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", userService.signUp(userDto));
        return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(null);
    }

    @PostMapping(value = "/user", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> login(@RequestBody UserDTO userDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", userService.logIn(userDto));
        return ResponseEntity.noContent().headers(headers).build();
    }

    @PreAuthorize("hasRole('GENERAL_USER')")
    @GetMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> getCurrentUser() {
        return ResponseEntity.ok(userService.getCurrentUser(getAuthenticatedUsername()));
    }

    @PreAuthorize("hasRole('GENERAL_USER')")
    @DeleteMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteUser() {
        userService.deleteUser(getAuthenticatedUsername());
        return ResponseEntity.noContent().build();
    }

    private String getAuthenticatedUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        } else {
            return principal.toString();
        }
    }
}
