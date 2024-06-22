package br.com.fatecmogidascruzes.fatecwebsite.infra.controllers.authentication;

import br.com.fatecmogidascruzes.fatecwebsite.infra.dtos.authentication.AuthenticationRequestDTO;
import br.com.fatecmogidascruzes.fatecwebsite.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping()
    public ResponseEntity<HashMap<String, String>> login(@RequestBody AuthenticationRequestDTO request) throws NoSuchAlgorithmException {

        HashMap<String, String> response = new HashMap<>();

        if (authenticationService.authenticate(request.email(), request.password())) {
            String token = authenticationService.generateToken(request.email());
            response.put("status", "ok");
            response.put("token", token);

            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", token);

            return new ResponseEntity<>(response, headers, HttpStatus.OK);
        }

        response.put("status", "error");
        response.put("message", "Invalid credentials");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }
}
