package com.example.Agendamento.controller;

import com.example.Agendamento.domain.event.CreateEventDTO;
import com.example.Agendamento.domain.event.DadosDetalhamentoEvent;
import com.example.Agendamento.domain.event.DadosUpdateEvent;
import com.example.Agendamento.domain.event.Event;
import com.example.Agendamento.services.EventService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("event")
public class EventController {

    @Autowired
    private EventService service;


    @PostMapping
    @Operation(summary = "criar um event", method = "POST")
    public ResponseEntity createEvent(@RequestBody @Valid CreateEventDTO data, UriComponentsBuilder uriComponentsBuilder) {
        Event event = new Event(data.userId(), data.title(), data.date(), data.hour(), data.description(), data.local());
        service.createEvent(event);
        var uri = uriComponentsBuilder.path("/event/{id}").buildAndExpand(event).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoEvent(event));
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "recuperar os events de um usuario", method = "GET")
    public ResponseEntity findAllEventByUserId(@PathVariable String userId) {
        var events = service.getEnventByUserId(userId);
        return ResponseEntity.ok(events);
    }

    @PutMapping
    @Operation(summary = "atualizar um event", method = "PUT")
    public ResponseEntity updateEvent(@RequestBody @Valid DadosUpdateEvent data) {
        var event = service.updateEvent(data);
        return ResponseEntity.ok(event);
    }

    @GetMapping("/{id}")
    @Operation(summary = "recuperar um event pelo id do event", method = "GET")
    public ResponseEntity findEventById(@PathVariable String id) {
        var event = service.findById(id);
        return ResponseEntity.ok(event);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "excluir um event pelo id", method = "GET")
    public ResponseEntity deleteEventById(@PathVariable String id) {
        service.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }

}
