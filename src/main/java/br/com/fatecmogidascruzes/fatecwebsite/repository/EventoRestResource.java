package br.com.fatecmogidascruzes.fatecwebsite.repository;

import br.com.fatecmogidascruzes.fatecwebsite.domain.Evento;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoRestResource extends MongoRepository<Evento, Long> {

}
