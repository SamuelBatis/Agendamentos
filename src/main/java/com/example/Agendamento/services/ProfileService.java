package com.example.Agendamento.services;

import com.example.Agendamento.domain.profile.Profile;
import com.example.Agendamento.domain.profile.UpdateDataProfile;
import com.example.Agendamento.repositories.ProfileRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProfileService {

    @Autowired
    private ProfileRepository repository;

    public Profile getProfile(String userId) {
        return repository.findByUserId(userId);
    }

    public Profile createProfile(Profile profile) {
        return repository.save(profile);
    }

    public Profile updateProfile(@Valid UpdateDataProfile data) {
        var profile = repository.findByUserId(data.userId());
        profile.updateInformation(data);
        repository.save(profile);
        return profile;
    }
}
