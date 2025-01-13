package net.ausiasmarch.wejeta.helper;

import java.util.Date;
import java.util.Map;
import java.util.UUID;
import javax.crypto.SecretKey;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JWTHelper {

    private static final String SUBJECT="authentication";
    private static final String ISSUER="wejeta.ausiasmarch.net";

    private static SecretKey getSecretKey() {
        String secretKey = "wejeta.ausias.54654654654654654bcvb54c6v5b46cv4b65c4v6b5c4v6b54c6seddffjrjrifjfdn";
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public static String generateToken(Map<String, String> claims) {
        return Jwts.builder()
                .id(UUID.randomUUID().toString())
                .claims(claims)
                .subject(SUBJECT)
                .issuer(ISSUER)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 6000000))
                .signWith(getSecretKey(), Jwts.SIG.HS256)
                .compact();
    }

    private static Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().verifyWith(getSecretKey()).build().parseSignedClaims(token).getPayload();
    }

    public static String validateToken(String sToken) {
        Claims oClaims = getAllClaimsFromToken(sToken);

        if (oClaims.getExpiration().before(new Date())) {
            return null;
        }

        if (oClaims.getIssuedAt().after(new Date())) {
            return null;
        }        

        if (!oClaims.getIssuer().equals(ISSUER)) {
            return null;
        }

        if (!oClaims.getSubject().equals(SUBJECT)) {
            return null;
        }
        
        return oClaims.get("email", String.class);

    }

}
