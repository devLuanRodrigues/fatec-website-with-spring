package br.com.fatecmogidascruzes.fatecwebsite.service;

import br.com.fatecmogidascruzes.fatecwebsite.domain.model.evento.Evento;
import br.com.fatecmogidascruzes.fatecwebsite.repository.AgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventoService {

    @Autowired
    private AgendamentoRepository repository;

    public List<Evento> findAll() {
        return repository.findAll();
    }

    public Optional<Evento> findById(String id) {
        return repository.findById(id);
    }

    public Evento save(Evento evento) {
        return repository.save(evento);
    }


    public Evento edit(String id, Evento evento) {

        Evento eventoEncontrado = repository.findById(id).orElseThrow(() -> new RuntimeException("Agendamento não encontrado"));

        eventoEncontrado.setTitulo(evento.getTitulo());
        eventoEncontrado.setDescricao(evento.getDescricao());
        eventoEncontrado.setData(evento.getData());

        return repository.save(evento);
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }
}
