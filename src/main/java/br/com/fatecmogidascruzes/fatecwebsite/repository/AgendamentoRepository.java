package br.com.fatecmogidascruzes.fatecwebsite.repository;

import br.com.fatecmogidascruzes.fatecwebsite.domain.model.agendamento.Agendamento;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgendamentoRepository extends MongoRepository<Agendamento, String> {
}
