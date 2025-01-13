package com.pawpengaga.frontend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.pawpengaga.frontend.model.Alumno;
import com.pawpengaga.frontend.model.Materia;

@Service
public class MateriaService {

  @Autowired
  RestTemplate restTemplate;

  String ruta;

  /* ************************************************* */

  public List<Materia> listarMateriasApi(){

    Materia materiaContenedora = null;
    ruta = "http://localhost:3000/api/v1/materias";

    HttpEntity<Materia> request = new HttpEntity<Materia>(materiaContenedora);
    ResponseEntity<List<Materia>> response = restTemplate.exchange(ruta, HttpMethod.GET, request, new ParameterizedTypeReference<List<Materia>>(){});

    return response.getBody();

  }

}
