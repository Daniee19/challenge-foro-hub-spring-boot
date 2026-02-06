package com.forohub.forohub.domain.topico;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.forohub.forohub.domain.curso.Curso;
import com.forohub.forohub.domain.respuesta.Respuesta;
import com.forohub.forohub.domain.usuario.Usuario;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDateTime fechaCreacion;
    private String status;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Usuario autor;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;

    @OneToMany(mappedBy = "topico")
    private List<Respuesta> respuestas = new ArrayList<>();

    public Topico(CrearTopicoDTO crearTopicoDTO, Usuario usuario, Curso curso) {
        this.id = null;
        this.titulo = crearTopicoDTO.titulo();
        this.mensaje = crearTopicoDTO.mensaje();
        this.autor = usuario;
        this.curso = curso;
        this.fechaCreacion = LocalDateTime.now();
        this.status = "activo";
        this.respuestas = new ArrayList<>();
    }
}
