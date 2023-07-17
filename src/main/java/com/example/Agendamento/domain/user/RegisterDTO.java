package com.example.Agendamento.domain.user;

import com.example.Agendamento.domain.profile.Profile;

public record RegisterDTO(String login, String password, String email, String name, int age, String telefone) {
}
