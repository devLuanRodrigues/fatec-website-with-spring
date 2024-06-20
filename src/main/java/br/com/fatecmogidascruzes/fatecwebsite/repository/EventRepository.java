package br.com.fatecmogidascruzes.fatecwebsite.repository;

import br.com.fatecmogidascruzes.fatecwebsite.model.Event;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EventRepository extends MongoRepository<Event, String> {
    List<Event> findByDate(String date);
}

