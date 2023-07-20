package com.example.Agendamento.controller;

import com.example.Agendamento.domain.profile.UpdateDataProfile;
import com.example.Agendamento.services.ProfileService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("profile")
public class ProfileController {

    @Autowired
    private ProfileService service;

    @PutMapping
    @Transactional
    public ResponseEntity updateProfile(@RequestBody @Valid UpdateDataProfile data) {
        var response = service.updateProfile(data);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{userId}")
    public ResponseEntity getProfileByUserId(@PathVariable String userId) {
        var profile  = service.getProfile(userId);
        return ResponseEntity.ok(profile);
    }
}
