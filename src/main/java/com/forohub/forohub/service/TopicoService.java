package com.forohub.forohub.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.forohub.forohub.domain.topico.Topico;
import com.forohub.forohub.domain.topico.TopicoRepository;

@Service
public class TopicoService {

    @Autowired
    TopicoRepository topicoRepository;

    public Topico crearTopico(Topico topico) {
        return topicoRepository.save(topico);
    }

    public List<Topico> obtenerTodo() {
        return topicoRepository.findAll();
    }
}
