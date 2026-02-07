package com.forohub.forohub.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.forohub.forohub.domain.curso.Curso;
import com.forohub.forohub.domain.topico.ActualizarTopicoDTO;
import com.forohub.forohub.domain.topico.CrearTopicoDTO;
import com.forohub.forohub.domain.topico.Topico;
import com.forohub.forohub.domain.topico.TopicoRepository;
import com.forohub.forohub.domain.usuario.Usuario;

@Service
public class TopicoService {

    @Autowired
    TopicoRepository topicoRepository;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    CursoService cursoService;

    public Topico crearTopico(CrearTopicoDTO crearTopicoDTO) {
        Usuario u = usuarioService.obtenerUsuarioPorId(crearTopicoDTO.usuarioId());
        Curso c = cursoService.obtenerCursoPorId(crearTopicoDTO.cursoId());
        Topico t = new Topico(crearTopicoDTO, u, c);
        return topicoRepository.save(t);
    }

    public Topico actualizarTopico(Long id, ActualizarTopicoDTO actualizarTopicoDTO) {
        if (!isPresent(id)) {
            System.out.println("No existe el Topico con id: " + id);
            return null;
        }
        // * Se obtiene el topico a actualizar (entidad Padre)
        Topico t = obtenerPorId(id);
        if (actualizarTopicoDTO.cursoId() != null) {
            // Se busca el curso a actualizar
            Curso c = cursoService.obtenerCursoPorId(actualizarTopicoDTO.cursoId());
            // Se setean los nuevos valores del parametro actualizarTopicoDTO a la entidad
            // -> Topico t y al curso tambien se le modifica los valores
            t.actualizarTopicoConCurso(actualizarTopicoDTO, c);
        } else {
            // Si no se agrego nada de curso, solo actualizar el Topico
            t.actualizarTopico(actualizarTopicoDTO);
        }
        return topicoRepository.save(t);
    }// Fin del actualizarTopico

    public void eliminarTopico(Long id) {
        if (!isPresent(id)) {
            System.out.println("No se puede eliminar un registro que no existe");
        }

        topicoRepository.deleteById(id);
    }

    public List<Topico> obtenerTodo() {
        return topicoRepository.findAll();
    }

    public Topico obtenerPorId(Long id) {
        return topicoRepository.findById(id).get();
    }

    public boolean isPresent(Long id) {
        return topicoRepository.findById(id).isPresent();
    }
}
