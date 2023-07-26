package com.example.Agendamento.services;

import com.example.Agendamento.domain.event.DadosUpdateEvent;
import com.example.Agendamento.domain.event.Event;
import com.example.Agendamento.domain.user.User;
import com.example.Agendamento.repositories.EventRepository;
import com.example.Agendamento.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {

  @Autowired
  private EmailService emailService;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private EventRepository repository;

  @Scheduled(cron = "0 0 9 * * ?")
  public void sendEventReminders() {
    LocalDate today = LocalDate.now();
    List<Event> todayEvents = repository.findAllByDate(today);
    for (Event event : todayEvents) {
      Optional<User> optionalUser = userRepository.findById(event.getUserId());
      User user = optionalUser.get();
      String message = "Lembrete para o evento: " + event.getTitle();
      emailService.sendEmail(user.getEmail(), "Lembrete do Evento", message);
    }
  }

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
