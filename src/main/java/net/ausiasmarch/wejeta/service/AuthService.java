package net.ausiasmarch.wejeta.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import net.ausiasmarch.wejeta.bean.LogindataBean;

@Service
public class AuthService {

    public boolean checkLogin(LogindataBean oLogindataBean) {
        if (oLogindataBean.getUser().equals("wejeta") && oLogindataBean.getPassword().equals("ausias")) {
            return true;
        } else {
            return false;
        }
    }

    private Map<String, String> getClaims() {
        Map<String, String> claims = new HashMap<>();
        claims.put("name", "wejeta");
        claims.put("email", "wejeta@ausiasmarch.net");
        return claims;
    };

    private SecretKey getSecretKey() {
        String secretKey = "wejeta.ausias.54654654654654654bcvb54c6v5b46cv4b65c4v6b5c4v6b54c6seddffjrjrifjfdn";
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public String getToken() {
        // generar el token jwt

        return Jwts.builder()
                .id(UUID.randomUUID().toString())
                .claims(getClaims())
                .subject("authentication")
                .issuer("wejeta.ausiasmarch.net")
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 6000000))
                .signWith(getSecretKey(), Jwts.SIG.HS256)
                .compact();

    }
}