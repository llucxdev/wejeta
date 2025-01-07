package net.ausiasmarch.wejeta.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.ausiasmarch.wejeta.service.AuthService;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {
    
    @Autowired
    AuthService oAuthService;

    @PostMapping("/login")
    public ResponseEntity<String> login() {
        // aqui recibimos el usuario y el hash de la contraseña
        // comprobamos si el usuario y el hash de la contraseña son correctos
        // si son correctos devolvemos el token jwt
        // si no son correctos devolvemos un error 401
        return ResponseEntity.ok("\"" + oAuthService.getToken() + "\"");                                
    }

}
