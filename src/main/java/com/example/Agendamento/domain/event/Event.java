package com.example.Agendamento.domain.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Data
@Document("events")
public class Event {

    @Id
    private String id;
    private String userId;
    private String title;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate date;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private LocalTime hour;
    private String description;
    private String local;
    public Event(String userId, String title, LocalDate date, LocalTime hour, String description, String local) {
        this.userId = userId;
        this.title = title;
        this.date = date;
        this.hour = hour;
        this.description = description;
        this.local = local;
    }

    public Event() {}

    public void updateInformation(DadosUpdateEvent data) {
        if(data.title() != null) {
            this.title = data.title();
        }
        if(data.date() != null) {
            this.date = data.date();
        }
        if(data.hour() != null) {
            this.hour = data.hour();
        }
        if(data.description() != null) {
            this.description = data.description();
        }
        if(data.local() != null) {
            this.local = data.local();
        }
    }


}
