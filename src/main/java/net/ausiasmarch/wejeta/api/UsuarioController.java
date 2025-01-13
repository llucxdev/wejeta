package net.ausiasmarch.wejeta.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.ausiasmarch.wejeta.service.UsuarioService;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    UsuarioService oUsuarioService;

    @GetMapping("/restricted")
    public ResponseEntity<String> restricted() {
        return ResponseEntity.ok("\"" + oUsuarioService.RestrictedArea() + "\"");
    }

}
