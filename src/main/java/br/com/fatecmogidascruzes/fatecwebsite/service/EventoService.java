package br.com.fatecmogidascruzes.fatecwebsite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.fatecmogidascruzes.fatecwebsite.repository.EventoRestResource;
import br.com.fatecmogidascruzes.fatecwebsite.domain.Evento;

import java.util.List;

@Service
public class EventoService {
    @Autowired
    private EventoRestResource eventoRestResource;

    public List<Evento> getAllEventos() {
        return eventoRestResource.findAll();
    }

    public Evento getEventoById(String id) {
        return eventoRestResource.findById(Long.valueOf(id)).orElse(null);
    }

    public Evento saveEvento(Evento evento) {
        return eventoRestResource.save(evento);
    }

    public void deleteEvento(String id) {
        eventoRestResource.deleteById(Long.valueOf(id));
    }
}
