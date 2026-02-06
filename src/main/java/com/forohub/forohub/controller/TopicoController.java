package com.forohub.forohub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.forohub.forohub.domain.curso.Curso;
import com.forohub.forohub.domain.topico.CrearTopicoDTO;
import com.forohub.forohub.domain.topico.Topico;
import com.forohub.forohub.domain.usuario.Usuario;
import com.forohub.forohub.service.CursoService;
import com.forohub.forohub.service.TopicoService;
import com.forohub.forohub.service.UsuarioService;

import jakarta.validation.Valid;

@RestController
public class TopicoController {

    @Autowired
    TopicoService topicoService;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    CursoService cursoService;

    @PostMapping("/topico")
    public ResponseEntity<?> crearTopico(@RequestBody @Valid CrearTopicoDTO crearTopicoDTO) {

        Usuario u = usuarioService.obtenerUsuarioPorId(crearTopicoDTO.usuarioId());
        Curso c = cursoService.obtenerCursoPorId(crearTopicoDTO.cursoId());

        Topico t = topicoService.crearTopico(new Topico(crearTopicoDTO, u, c));
        return ResponseEntity.ok(t);
    }

    @GetMapping("/topicos")
    public ResponseEntity<?> topicos() {
        List<Topico> topicos = topicoService.obtenerTodo();
        return ResponseEntity.ok(topicos);
    }

}
