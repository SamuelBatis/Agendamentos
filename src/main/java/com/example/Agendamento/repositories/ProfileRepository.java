package com.example.Agendamento.repositories;

import com.example.Agendamento.domain.profile.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProfileRepository extends MongoRepository<Profile, String> {
    Profile findByUserId(String userId);
}
