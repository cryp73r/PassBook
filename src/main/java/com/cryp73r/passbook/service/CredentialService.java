package com.cryp73r.passbook.service;

import com.cryp73r.passbook.dto.CredentialDTO;
import com.cryp73r.passbook.exception.BadRequestException;
import com.cryp73r.passbook.exception.ResourceNotFoundException;
import com.cryp73r.passbook.mapper.CredentialMapper;
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

    @Autowired
    CredentialMapper credentialMapper;

    public CredentialService() {}

    public CredentialDTO addCredential(CredentialDTO credentialDTO, String owner) {
        Platform platform = platformRepository.findByPlatformCode(credentialDTO.getPlatform().getPltCode()).orElseThrow(() -> new BadRequestException("Platform not found"));
        credentialDTO.setPlatform(platform);
        Credential credential = credentialMapper.CredentialDTOToCredential(credentialDTO, owner);
        credentialRepository.save(credential);
        return credentialMapper.CredentialToCredentialDTO(credential);
    }

    public CredentialDTO getCredential(Long rowId, String owner) {
        return credentialMapper.CredentialToCredentialDTO(credentialRepository.findByIdAndOwner(rowId, owner).orElseThrow(() -> new ResourceNotFoundException("Credential not found")));
    }

    public void deleteCredential(Long rowId, String owner) {
        credentialRepository.deleteByIdAndOwner(rowId, owner);
    }
}
