package br.com.fatecmogidascruzes.fatecwebsite.domain.model.authentication;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "usuarios")
public class Authentication {

    private String email;

    private String senha;

}
