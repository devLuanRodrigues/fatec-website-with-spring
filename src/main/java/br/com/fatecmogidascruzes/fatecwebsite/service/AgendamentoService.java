package br.com.fatecmogidascruzes.fatecwebsite.service;

import br.com.fatecmogidascruzes.fatecwebsite.model.Agendamento;
import br.com.fatecmogidascruzes.fatecwebsite.repository.AgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository repository;

    public List<Agendamento> findAll() {
        return repository.findAll();
    }

    public Optional<Agendamento> findById(String id) {
        return repository.findById(id);
    }

    public Agendamento save(Agendamento agendamento) {
        return repository.save(agendamento);
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }
}
