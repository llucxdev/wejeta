package net.ausiasmarch.wejeta.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.ausiasmarch.wejeta.bean.LogindataBean;
import net.ausiasmarch.wejeta.repository.UsuarioRepository;

@Service
public class AuthService {

    @Autowired
    JWTService JWTHelper;

    @Autowired
    UsuarioRepository oUsuarioRepository;

    public boolean checkLogin(LogindataBean oLogindataBean) {
        if (oUsuarioRepository.findByEmailAndPassword(oLogindataBean.getEmail(), oLogindataBean.getPassword())
                .isPresent()) {
            return true;
        } else {
            return false;
        }
    }

    private Map<String, String> getClaims(String email) {
        Map<String, String> claims = new HashMap<>();
        claims.put("email",  email);
        return claims;
    };

    public String getToken(String email) {
        return JWTHelper.generateToken(getClaims(email));
    }


    
}
