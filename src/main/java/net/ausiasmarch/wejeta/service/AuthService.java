package net.ausiasmarch.wejeta.service;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;
import net.ausiasmarch.wejeta.bean.LogindataBean;
import net.ausiasmarch.wejeta.helper.JWTHelper;

@Service
public class AuthService {

    public boolean checkLogin(LogindataBean oLogindataBean) {
        if (oLogindataBean.getEmail().equals("wejeta@ausiasmarch.net") && oLogindataBean.getPassword().equals("ausias")) {
            return true;
        } else {
            return false;
        }
    }

    private Map<String, String> getClaims() {
        Map<String, String> claims = new HashMap<>();        
        claims.put("email", "wejeta@ausiasmarch.net");
        return claims;
    };



    public String getToken() {        
        return JWTHelper.generateToken(getClaims());
    }
}