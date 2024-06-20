package br.com.fatecmogidascruzes.fatecwebsite.controller;

import br.com.fatecmogidascruzes.fatecwebsite.model.Event;
import br.com.fatecmogidascruzes.fatecwebsite.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/events")
public class EventController {
    @Autowired
    private EventService eventService;

    @GetMapping("/{date}")
    public List<Event> getEventsByDate(@PathVariable String date) {
        return eventService.getEventsByDate(date);
    }

    @PostMapping
    public Event addEvent(@RequestBody Event event) {
        return eventService.addEvent(event);
    }

    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable String id) {
        eventService.deleteEvent(id);
    }
}

