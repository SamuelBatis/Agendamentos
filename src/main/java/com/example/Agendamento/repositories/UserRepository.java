package com.example.Agendamento.repositories;

import com.example.Agendamento.domain.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.core.userdetails.UserDetails;


public interface UserRepository extends MongoRepository<User, String> {
    User findByEmail(String email);
    UserDetails findByLogin(String login);
}
