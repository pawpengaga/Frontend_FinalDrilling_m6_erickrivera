package com.pawpengaga.frontend.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;

@Data
public class Alumno {

  private Long id;
  private String rut;
  private String nombre;
  private String direccion;
  private Set<Materia> materiaList = new HashSet<>();

}
