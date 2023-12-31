package com.example.Agendamento.controller;

import com.example.Agendamento.domain.profile.Profile;
import com.example.Agendamento.domain.user.AuthenticationDTO;
import com.example.Agendamento.domain.user.LoginResponseDTO;
import com.example.Agendamento.domain.user.RegisterDTO;
import com.example.Agendamento.domain.user.User;
import com.example.Agendamento.infra.security.TokenService;
import com.example.Agendamento.repositories.UserRepository;
import com.example.Agendamento.services.ProfileService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AutheticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository repository;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data) {
        if(this.repository.findByEmail(data.email()) != null) return ResponseEntity.badRequest().build();
        if(this.repository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.login(), encryptedPassword, data.email());
        var insertedUser = this.repository.save(newUser);
        Profile newProfile = new Profile(insertedUser.getId(), data.name(), data.age(), data.telefone());
        profileService.createProfile(newProfile);

        return ResponseEntity.ok().build();
    }

}
