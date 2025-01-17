package com.pawpengaga.frontend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.pawpengaga.frontend.model.AuthResponse;
import com.pawpengaga.frontend.model.LoginRequest;
import com.pawpengaga.frontend.service.AuthService;
import com.pawpengaga.frontend.utils.TokenUtilities;

import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/")
public class AuthWebController {

  @Autowired
  AuthService authService;

  static TokenUtilities tokenUtilities = new TokenUtilities();

  @GetMapping("/login")
	public ModelAndView inicio() {
		System.out.println("llega al controlador Front");
		ModelAndView mav = new ModelAndView("login");
		return mav;
	}

@PostMapping("/login")
public String login(
    @RequestParam("username") String username,
    @RequestParam("password") String password,
    HttpServletResponse response,
    Model model) {

    LoginRequest loginRequest = new LoginRequest(username, password);
    AuthResponse authResponse = authService.loginApi(loginRequest);
    tokenUtilities.guardarToken(authResponse.getJwt(), response);

    model.addAttribute("token", tokenUtilities.obtenerJWT());
    
    model.addAttribute("mensaje", "Sesión iniciada!");
    model.addAttribute("tipo", "success");

    return "index";
}


}
