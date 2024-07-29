package com.cryp73r.passbook.controller;

import com.cryp73r.passbook.dto.CredentialDTO;
import com.cryp73r.passbook.model.Credential;
import com.cryp73r.passbook.service.CredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api")
public class CredentialController {

    @Autowired
    CredentialService credentialService;

    @PreAuthorize("hasRole('GENERAL_USER')")
    @PostMapping(value = "/credential", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CredentialDTO> addCredential(@RequestBody CredentialDTO credentialDTO) {
        CredentialDTO savedCredential = credentialService.addCredential(credentialDTO, getAuthenticatedUsername());
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedCredential.getRowId()).toUri();
        return ResponseEntity.created(location).body(savedCredential);
    }

    @PreAuthorize("hasRole('GENERAL_USER')")
    @GetMapping(value = "/credential/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CredentialDTO> getCredential(@PathVariable(name = "id") Long rowId) {
        return ResponseEntity.ok(credentialService.getCredential(rowId, getAuthenticatedUsername()));
    }

    @PreAuthorize("hasRole('GENERAL_USER')")
    @DeleteMapping("/credential/{id}")
    public ResponseEntity<Credential> deleteCredential(@PathVariable(name = "id") Long rowId) {
        credentialService.deleteCredential(rowId, getAuthenticatedUsername());
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
