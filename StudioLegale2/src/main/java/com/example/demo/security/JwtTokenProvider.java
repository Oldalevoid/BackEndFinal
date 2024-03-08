package com.example.demo.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

import javax.crypto.SecretKey;

@Component
public class JwtTokenProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);
   
    // Chiave segreta per la firma del token JWT
    private static final String jwtSecret = "daf66e01593f61a15b857cf433aae03a005812b31234e149036bcc8dee755dbb";

    // Scadenza del token JWT in millisecondi (30 minuti)
    private static final long jwtExpirationDate = 60400000;

    // genera il token JWT
    public String generateToken(Authentication authentication){
        String username = authentication.getName();

        Date currentDate = new Date();

        Date expireDate = new Date(currentDate.getTime() + jwtExpirationDate);

        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(key())  // Modifica qui per utilizzare la chiave segreta come stringa
                .compact();
        return token;
    }

    private Key key(){
        // Utilizza la chiave segreta come stringa per creare una SecretKey
        SecretKey secretKey = Keys.hmacShaKeyFor(jwtSecret.getBytes());
        return secretKey;
    }

    // ottiene il nome utente dal token Jwt
    public String getUsername(String token){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key())  // Utilizza la chiave segreta come stringa
                .build()
                .parseClaimsJws(token)
                .getBody();
        String username = claims.getSubject();
        return username;
    }

    // convalida il token Jwt
    public boolean validateToken(String token){
        try{
            Jwts.parserBuilder()
                    .setSigningKey(key())  // Utilizza la chiave segreta come stringa
                    .build()
                    .parse(token);
            return true;
        } catch (MalformedJwtException e) {
            logger.error("Token JWT non valido: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("Il token JWT è scaduto: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("Il token JWT non è supportato: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("La stringa dei dati del token JWT è vuota: {}", e.getMessage());
        }
        return false;
    }   
}

