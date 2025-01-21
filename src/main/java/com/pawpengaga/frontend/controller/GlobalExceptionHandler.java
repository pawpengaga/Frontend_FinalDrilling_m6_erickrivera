package com.pawpengaga.frontend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(HttpClientErrorException.class)
  public String handleHttpClientError(HttpClientErrorException exception, Model model){

    if (exception.getStatusCode() == HttpStatus.UNAUTHORIZED) {
      model.addAttribute("mensaje", "Por favor, inicie sesión para continuar...");
      model.addAttribute("tipo", "warning");
      return "login";
    }

    if (exception.getStatusCode() == HttpStatus.FORBIDDEN) {
      model.addAttribute("mensaje", "Usted no posee los permisos necesarios para realizar esta acción");
      model.addAttribute("tipo", "info");
      return "exceptions-page";
    }

    if (exception.getStatusCode() == HttpStatus.NOT_FOUND) {
      model.addAttribute("mensaje", "El recurso que busca no se ha encontrado...");
      model.addAttribute("tipo", "danger");
      return "exceptions-page";
    }
    
    return "exceptions-page";

  }
}
