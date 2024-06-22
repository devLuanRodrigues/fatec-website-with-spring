package br.com.fatecmogidascruzes.fatecwebsite.repository;

import br.com.fatecmogidascruzes.fatecwebsite.domain.model.authentication.Authentication;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthenticationRepository extends MongoRepository<Authentication, String> {

    public boolean existsByEmailAndSenha(String email, String senha);
}
