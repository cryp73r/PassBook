package com.cryp73r.passbook.mapper;

import com.cryp73r.passbook.dto.CredentialDTO;
import com.cryp73r.passbook.model.Credential;
import org.springframework.stereotype.Component;

@Component
public class CredentialMapper {

    public CredentialDTO CredentialToCredentialDTO(Credential credential) {
        CredentialDTO credentialDTO = new CredentialDTO();
        credentialDTO.setRowId(credential.getRowId());
        credentialDTO.setUsername(credential.getUsername());
        credentialDTO.setPassword(credential.getPassword());
        credentialDTO.setPlatform(credential.getPlatform());
        credentialDTO.setCreatedAt(credential.getCreatedAt());
        credentialDTO.setUpdatedAt(credential.getUpdatedAt());
        return credentialDTO;
    }

    public Credential CredentialDTOToCredential(CredentialDTO credentialDTO, String owner) {
        Credential credential = new Credential();
        credential.setUsername(credentialDTO.getUsername());
        credential.setPassword(credentialDTO.getPassword());
        credential.setPlatform(credentialDTO.getPlatform());
        credential.setOwner(owner);
        return credential;
    }
}
