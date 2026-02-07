package com.forohub.forohub.domain.topico;

public record ActualizarTopicoDTO(
        String titulo,
        String mensaje,
        String estado,
        Long cursoId) {
}
