package com.example.Agendamento.domain.event;

import java.time.LocalDate;
import java.time.LocalTime;

public record DadosDetalhamentoEvent(
        String id,
        String userId,
        String title,
        LocalDate date,
        LocalTime hour,
        String description,
        String local
) {
    public DadosDetalhamentoEvent(Event data){
        this(data.getId(), data.getUserId(), data.getTitle(), data.getDate(), data.getHour(), data.getDescription(), data.getLocal());
    }
}
