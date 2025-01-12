package com.pawpengaga.frontend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pawpengaga.frontend.service.AlumnoService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class UserWebController {

  @Autowired
  AlumnoService alumnoService;

  /* ************************************************* */

  @GetMapping("/alumnos")
  public String listarAlumnos(Model model){
    model.addAttribute("alumnos", alumnoService.listarAlumnosApi());
    return "alumnos";
  }


}
