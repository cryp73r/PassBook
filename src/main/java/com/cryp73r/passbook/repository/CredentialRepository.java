package com.cryp73r.passbook.repository;

import com.cryp73r.passbook.model.Credential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CredentialRepository extends JpaRepository<Credential, Long> {

    @Query("SELECT crdtl FROM Credential crdtl WHERE crdtl.rowId = :id AND crdtl.owner = :owner")
    Optional<Credential> findByIdAndOwner(Long id, String owner);

    @Query("DELETE FROM Credential crdtl WHERE crdtl.rowId = :id AND crdtl.owner = :owner")
    void deleteByIdAndOwner(Long id, String owner);
}
