package com.cryp73r.passbook.service;

import com.cryp73r.passbook.model.Credential;
import com.cryp73r.passbook.model.Platform;
import com.cryp73r.passbook.repository.CredentialRepository;
import com.cryp73r.passbook.repository.PlatformRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CredentialService {

    @Autowired
    PlatformRepository platformRepository;

    @Autowired
    CredentialRepository credentialRepository;

    public CredentialService() {}

    public Credential addCredential(Credential credential, String username) {
        Platform platform = platformRepository.findByPlatformCode(credential.getPlatform().getPltCode()).orElseThrow(() -> new RuntimeException("Platform not found"));
        credential.setPlatform(platform);
        credential.setOwner(username);
        return credentialRepository.save(credential);
    }

    public Credential getCredential(Long rowId) {
        return credentialRepository.findById(rowId).orElseThrow(() -> new RuntimeException("Credential not found"));
    }

    public void deleteCredential(Long rowId) {
        credentialRepository.deleteById(rowId);
    }
}
