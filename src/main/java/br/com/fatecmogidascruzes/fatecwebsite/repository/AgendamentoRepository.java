package br.com.fatecmogidascruzes.fatecwebsite.repository;

import br.com.fatecmogidascruzes.fatecwebsite.domain.model.evento.Evento;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgendamentoRepository extends MongoRepository<Evento, String> {
}
