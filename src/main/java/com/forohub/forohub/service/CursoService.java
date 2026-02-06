package com.forohub.forohub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.forohub.forohub.domain.curso.Curso;
import com.forohub.forohub.domain.curso.CursoRepository;

@Service
public class CursoService {
    @Autowired
    CursoRepository cursoRepository;

    public Curso obtenerCursoPorId(Long id) {
        return cursoRepository.findById(id).get();
    }
}
