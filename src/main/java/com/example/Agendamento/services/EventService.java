package com.example.Agendamento.services;

import com.example.Agendamento.domain.event.DadosUpdateEvent;
import com.example.Agendamento.domain.event.Event;
import com.example.Agendamento.repositories.EventRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    @Autowired
    private EventRepository repository;

    public void createEvent(Event event) {
        repository.save(event);
    }

    public List<Event> getEnventByUserId(String userId) {
        return repository.findByUserId(userId);
    }

    public Optional<Event> findById(String id) {
        return repository.findById(id);
    }

    public Event updateEvent(@Valid DadosUpdateEvent data) {
        var event = repository.findEventById(data.id());
        event.updateInformation(data);
        repository.save(event);
        return event;
    }

    public void deleteEvent(String id) {
        repository.deleteById(id);
    }


}
