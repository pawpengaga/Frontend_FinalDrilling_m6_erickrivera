package com.pawpengaga.frontend.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TokenUtilities {

  public void guardarToken(String token, HttpServletResponse response){

    // El limpiador de cookies
    Cookie cookieToDelete = new Cookie("jtw_token", null);
    cookieToDelete.setHttpOnly(true);
    cookieToDelete.setSecure(true);
    cookieToDelete.setPath("/");
    cookieToDelete.setMaxAge(0);
    response.addCookie(cookieToDelete);

    // El guardador de cookies
    Cookie tokenCookie = new Cookie("jtw_token", token);
    tokenCookie.setHttpOnly(true);
    tokenCookie.setSecure(true);
    tokenCookie.setPath("/");
    tokenCookie.setMaxAge(60 * 60 * 24);

    response.addCookie(tokenCookie);
    System.out.println("Token guardado!");
  }

  public String obtenerJWT(){
    
    ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

    if (attributes == null) {
      return null;
    }

    HttpServletRequest request = attributes.getRequest();

    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
      for (Cookie cookie : cookies) {
        if ("jtw_token".equals(cookie.getName())) {
          return cookie.getValue();
        }
      }
    }

    return null;

  }

}
