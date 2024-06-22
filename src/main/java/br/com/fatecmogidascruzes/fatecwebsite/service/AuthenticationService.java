package br.com.fatecmogidascruzes.fatecwebsite.service;

import br.com.fatecmogidascruzes.fatecwebsite.repository.AuthenticationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.UUID;

@Service
public class AuthenticationService {

    @Autowired
    private AuthenticationRepository authenticationRepository;

    public boolean authenticate(String email, String senha) {
        System.out.println("Email: " + email + " Senha: " + senha);
        var result = authenticationRepository.existsByEmailAndSenha(email, senha);
        System.out.println("Result: " + result);
        return authenticationRepository.existsByEmailAndSenha(email, senha);
    }

    public String generateToken(String email) throws NoSuchAlgorithmException {
        String combinedInput = email + UUID.randomUUID();

        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(combinedInput.getBytes(StandardCharsets.UTF_8));

        return Base64.getUrlEncoder().withoutPadding().encodeToString(hash);
    }

}
