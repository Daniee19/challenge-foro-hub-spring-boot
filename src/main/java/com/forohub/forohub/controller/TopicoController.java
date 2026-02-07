package com.forohub.forohub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.forohub.forohub.domain.topico.ActualizarTopicoDTO;
import com.forohub.forohub.domain.topico.CrearTopicoDTO;
import com.forohub.forohub.domain.topico.Topico;
import com.forohub.forohub.service.TopicoService;

import jakarta.validation.Valid;

@RestController
public class TopicoController {

    @Autowired
    TopicoService topicoService;

    @PostMapping("/topico")
    public ResponseEntity<?> crearTopico(@RequestBody @Valid CrearTopicoDTO crearTopicoDTO) {
        Topico t = topicoService.crearTopico(crearTopicoDTO);
        return ResponseEntity.ok(t);
    }

    @GetMapping("/topicos")
    public ResponseEntity<?> topicos() {
        List<Topico> topicos = topicoService.obtenerTodo();
        return ResponseEntity.ok(topicos);
    }

    @GetMapping("/topico/{id}")
    public ResponseEntity<?> obtenerTopicoPorId(@PathVariable Long id) {
        return ResponseEntity.ok(topicoService.obtenerPorId(id));
    }

    @PutMapping("/topico/{id}")
    public ResponseEntity<?> actualizarTopicoPorId(@PathVariable Long id,
            @RequestBody @Valid ActualizarTopicoDTO actualizarTopicoDTO) {
        Topico t = topicoService.actualizarTopico(id, actualizarTopicoDTO);
        return ResponseEntity.ok(t);
    }

    @DeleteMapping("/topicos/{id}")
    public ResponseEntity<?> eliminarTopicoPorId(@PathVariable Long id) {
        try {
            topicoService.eliminarTopico(id);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return ResponseEntity.ok(exception.getMessage());
        }
        return ResponseEntity.ok("Se eliminó el topico con el id: " + id);
    }
}
