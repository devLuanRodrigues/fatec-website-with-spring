package br.com.fatecmogidascruzes.fatecwebsite.service;

import br.com.fatecmogidascruzes.fatecwebsite.model.Event;
import br.com.fatecmogidascruzes.fatecwebsite.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    public List<Event> getEventsByDate(String date) {
        return eventRepository.findByDate(date);
    }

    public Event addEvent(Event event) {
        return eventRepository.save(event);
    }

    public void deleteEvent(String id) {
        eventRepository.deleteById(id);
    }
}
