package com.pawpengaga.frontend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.pawpengaga.frontend.model.AuthResponse;
import com.pawpengaga.frontend.model.LoginRequest;

@Service
public class AuthService {

  @Autowired
  RestTemplate restTemplate;

  String ruta;

  /* ************************************************* */

  public AuthResponse loginApi(LoginRequest loginRequest){
    
    ruta = "http://localhost:3000/api/v1/auth/log-in";

    HttpEntity<LoginRequest> entity = new HttpEntity<>(loginRequest);
    ResponseEntity<AuthResponse> response = restTemplate.postForEntity(ruta, entity, AuthResponse.class);

    if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
      AuthResponse authResponse = response.getBody();
      return authResponse;
    }
    
    throw new RuntimeException("Hubo un error al hacer login...");

  }

}
