package net.ausiasmarch.wejeta.service;

import org.springframework.stereotype.Service;

@Service
public class AuthService {

    public String getToken() {
        // generar el token jwt
        
        return "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTUxNjIzOTAyMn";
    }
}