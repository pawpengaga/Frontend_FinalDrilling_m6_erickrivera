package com.pawpengaga.frontend.controller;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pawpengaga.frontend.dao.AlumnoDTO;
import com.pawpengaga.frontend.model.Alumno;
import com.pawpengaga.frontend.model.Materia;
import com.pawpengaga.frontend.service.AlumnoService;
import com.pawpengaga.frontend.service.MateriaService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class UserWebController {

  @Autowired
  AlumnoService alumnoService;

  @Autowired
  MateriaService materiaService;

  /* ************************************************* */

  @PostMapping("/materias/nuevo")
  public String nuevaMateria(@ModelAttribute Materia materia, Model model){

    materiaService.crearMateriaApi(materia);

    model.addAttribute("mensaje", "Materia creada!");
    model.addAttribute("tipo", "success");
    return "redirect:/alumnos/nuevo";

  }

  @GetMapping("/alumnos")
  public String listarAlumnos(Model model){
    model.addAttribute("alumnos", alumnoService.listarAlumnosApi());
    return "alumnos";
  }

  @GetMapping("/alumnos/nuevo")
  public String nuevoAlumnoForm(Model model){
    model.addAttribute("alumno", new Alumno());
    model.addAttribute("materia", new Materia());
    model.addAttribute("materias", materiaService.listarMateriasApi());
    return "alumno-form";

  }

  @PostMapping("/alumnos/nuevo")
  public String crearAlumno(@ModelAttribute AlumnoDTO alumnoFormDto, Model model) {

    Alumno alumno = new Alumno();
    alumno.setRut(alumnoFormDto.getRut());
    alumno.setNombre(alumnoFormDto.getNombre());
    alumno.setDireccion(alumnoFormDto.getDireccion());

    Set<Materia> materias = alumnoFormDto.getMateriaIds().stream()
      .map(id -> {
          Materia materia = new Materia();
          materia.setId(id);
          return materia;
      })
      .collect(Collectors.toSet());
    alumno.setMateriaList(materias);


    alumnoService.crearAlumnoApi(alumno);
    return "redirect:/alumnos";
  }




}
