package com.pawpengaga.frontend.dao;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;

@Data
public class AlumnoDTO {

  private String rut;
  private String nombre;
  private String direccion;
  private Set<Long> materiaIds = new HashSet<>();

}
