package com.cryp73r.passbook.controller;

import com.cryp73r.passbook.model.Platform;
import com.cryp73r.passbook.repository.PlatformRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class PlatformController {

    @Autowired
    PlatformRepository platformRepository;

    @PreAuthorize("hasAnyRole('ADMINISTRATOR', 'SUPER_USER')")
    @PostMapping(value = "/platform", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Platform addPlatform(@RequestBody Platform platform) {
        return platformRepository.save(platform);
    }
}
