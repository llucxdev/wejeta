package net.ausiasmarch.wejeta.service;

import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import net.ausiasmarch.wejeta.entity.UsuarioEntity;
import net.ausiasmarch.wejeta.repository.UsuarioRepository;

@Service
@AllArgsConstructor
public class UsuarioService {

    HttpServletRequest oHttpServletRequest;

    UsuarioRepository oUsuarioRepository;

    AuthService oAuthService;

    public String RestrictedArea() {
        if (oHttpServletRequest.getAttribute("email") == null) {
            return "No tienes permisos para acceder a esta zona";
        } else {
            return "Bienvenido a la zona restringida";
        }
    }

    public UsuarioEntity get(Long id){
        if (oAuthService.isSessionActive()) {
            return oUsuarioRepository.findById(id).get();
        } else {
            return null;
        }
        
    }



}
