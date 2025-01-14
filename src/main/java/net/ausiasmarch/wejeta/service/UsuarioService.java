package net.ausiasmarch.wejeta.service;

import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UsuarioService {

    HttpServletRequest oHttpServletRequest;

    public String RestrictedArea() {
        if (oHttpServletRequest.getAttribute("email") == null) {
            return "No tienes permisos para acceder a esta zona";
        } else {
            return "Bienvenido a la zona restringida";
        }
    }

}
