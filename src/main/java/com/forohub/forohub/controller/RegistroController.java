package com.forohub.forohub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.forohub.forohub.domain.usuario.Usuario;
import com.forohub.forohub.domain.usuario.UsuarioRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/registro")
public class RegistroController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    public ResponseEntity<?> registrar(@RequestBody @Valid DatosRegistro datos) {
        String contraseniaEncriptada = passwordEncoder.encode(datos.contrasenia());

        Usuario usuario = new Usuario(
            null,
            datos.nombre(),
            datos.login(),
            contraseniaEncriptada,
            new java.util.ArrayList<>()
        );

        usuarioRepository.save(usuario);

        return ResponseEntity.ok("Usuario registrado exitosamente");
    }
}

record DatosRegistro(String nombre, String login, String contrasenia) {}
