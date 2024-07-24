package com.cryp73r.passbook.repository;

import com.cryp73r.passbook.model.Platform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlatformRepository extends JpaRepository<Platform, Long> {

    @Query("SELECT plt FROM Platform plt WHERE plt.pltCode = :pltCode")
    Optional<Platform> findByPlatformCode(String pltCode);
}
