package com.example.Agendamento.repositories;

import com.example.Agendamento.domain.event.Event;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public interface EventRepository extends MongoRepository<Event, String> {
    List<Event> findByUserId(String userId);

    List<Event> findAllByDate(LocalDate date);

    default Event findEventById(String id) {
        Optional<Event> event = findById(id);
        return event.orElseThrow(() -> new RuntimeException("Event not found with id " + id));
    }
}
