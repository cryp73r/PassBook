package com.cryp73r.passbook.controller;

import com.cryp73r.passbook.model.Credential;
import com.cryp73r.passbook.security.jwt.JwtUtils;
import com.cryp73r.passbook.service.CredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api")
public class CredentialController {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    CredentialService credentialService;

    @PreAuthorize("hasRole('USER')")
    @PostMapping(value = "/credential", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Credential> addCredential(@RequestBody Credential credential) {
        Credential savedCredential = credentialService.addCredential(credential, "test");
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedCredential.getRowid()).toUri();
        return ResponseEntity.created(location).body(savedCredential);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping(value = "/credential/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Credential> getCredential(@PathVariable(name = "id") Long rowId) {
        return ResponseEntity.ok(credentialService.getCredential(rowId));
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/credential/{id}")
    public ResponseEntity<Credential> deleteCredential(@PathVariable(name = "id") Long rowId) {
        credentialService.deleteCredential(rowId);
        return ResponseEntity.noContent().build();
    }
}
