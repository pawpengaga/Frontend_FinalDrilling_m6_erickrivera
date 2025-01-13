package com.pawpengaga.frontend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.pawpengaga.frontend.model.Alumno;

@Service
public class AlumnoService {

  @Autowired
  RestTemplate restTemplate;

  String ruta;

  /* ************************************************* */

  public List<Alumno> listarAlumnosApi(){

    Alumno alumnoContenedor = null;
    ruta = "http://localhost:3000/api/v1/alumnos";

    HttpEntity<Alumno> request = new HttpEntity<Alumno>(alumnoContenedor);
    ResponseEntity<List<Alumno>> response = restTemplate.exchange(ruta, HttpMethod.GET, request, new ParameterizedTypeReference<List<Alumno>>(){});
    
    // Teoria: Si podemos retornar aqui el cuerpo de la respuesta
    // No hay ninguna razón por la que no podríamos además retornar los headers

    return response.getBody();

  }

  public void crearAlumnoApi(Alumno alumno){

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    ruta = "http://localhost:3000/api/v1/alumnos/grabar";

    HttpEntity<Alumno> request = new HttpEntity<Alumno>(alumno, headers);
    restTemplate.postForEntity(ruta, request, String.class);

  }




}
