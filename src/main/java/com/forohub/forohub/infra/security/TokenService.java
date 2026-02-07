package com.forohub.forohub.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.forohub.forohub.domain.usuario.Usuario;

@Service
public class TokenService {

    private static final String SECRET = "12345678";

    public String generarToken(Usuario usuario) {
        try {
            var algoritmo = Algorithm.HMAC256(SECRET);
            return JWT.create()
                    .withIssuer("auth0")
                    .withSubject(usuario.getLogin())
                    .withExpiresAt(fechaExpiracion())
                    .sign(algoritmo);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error al generar el JWT", exception);
        }
    }

    public String validarToken(String token) {
        try {
            var algoritmo = Algorithm.HMAC256(SECRET);
            DecodedJWT decodedJWT = JWT.require(algoritmo)
                    .withIssuer("auth0")
                    .build()
                    .verify(token);
            return decodedJWT.getSubject();
        } catch (JWTVerificationException exception) {
            return null;
        }
    }

    private Instant fechaExpiracion() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
    }

}
