package com.forohub.forohub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.forohub.forohub.domain.usuario.Usuario;
import com.forohub.forohub.domain.usuario.UsuarioRepository;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;

    public Usuario obtenerUsuarioPorId(Long id) {
        return usuarioRepository.findById(id).get();
    }
}
