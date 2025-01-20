package net.ausiasmarch.wejeta.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import net.ausiasmarch.wejeta.entity.UsuarioEntity;
import net.ausiasmarch.wejeta.exception.ResourceNotFoundException;
import net.ausiasmarch.wejeta.repository.TipousuarioRepository;
import net.ausiasmarch.wejeta.repository.UsuarioRepository;
import net.ausiasmarch.wejeta.entity.TipousuarioEntity;

@Service
@AllArgsConstructor
public class UsuarioService {

    HttpServletRequest oHttpServletRequest;

    UsuarioRepository oUsuarioRepository;

    AuthService oAuthService;

    TipousuarioRepository oTipousuarioRepository;

    public String RestrictedArea() {
        if (oHttpServletRequest.getAttribute("email") == null) {
            return "No tienes permisos para acceder a esta zona";
        } else {
            return "Bienvenido a la zona restringida";
        }
    }

    public UsuarioEntity get(Long id) {
        return oUsuarioRepository.findById(id).get();
    }

    public Page<UsuarioEntity> getPage(Pageable oPageable) {
        return oUsuarioRepository.findAll(oPageable);
    }

    public UsuarioEntity create(UsuarioEntity oUsuarioEntity) {

        TipousuarioEntity oTipousuarioEntity = oTipousuarioRepository.findById(oUsuarioEntity.getTipousuario().getId())
                .orElseThrow(
                        () -> new ResourceNotFoundException("No se encuentra el tipo de usuario"));

        oUsuarioEntity.setTipousuario(oTipousuarioEntity);
        return oUsuarioRepository.save(oUsuarioEntity);
    }

    public UsuarioEntity update(UsuarioEntity oUsuarioEntity) {
        UsuarioEntity oUsuarioEntityFromDatabase = oUsuarioRepository.findById(oUsuarioEntity.getId()).get();
        if (oUsuarioEntity.getNombre() != null) {
            oUsuarioEntityFromDatabase.setNombre(oUsuarioEntity.getNombre());
        }
        if (oUsuarioEntity.getApellido1() != null) {
            oUsuarioEntityFromDatabase.setApellido1(oUsuarioEntity.getApellido1());
        }
        if (oUsuarioEntity.getApellido2() != null) {
            oUsuarioEntityFromDatabase.setApellido2(oUsuarioEntity.getApellido2());
        }
        if (oUsuarioEntity.getEmail() != null) {
            oUsuarioEntityFromDatabase.setEmail(oUsuarioEntity.getEmail());
        }
        if (oUsuarioEntity.getPassword() != null) {
            oUsuarioEntityFromDatabase.setPassword(oUsuarioEntity.getPassword());
        }
        if (oUsuarioEntity.getTipousuario() != null) {
            oUsuarioEntityFromDatabase
                    .setTipousuario(oTipousuarioRepository.findById(oUsuarioEntity.getTipousuario().getId()).get());
        }
        return oUsuarioRepository.save(oUsuarioEntityFromDatabase);
    }

    public Long delete(Long id) {
        oUsuarioRepository.deleteById(id);
        return 1L;
    }
}
