package br.com.fatecmogidascruzes.fatecwebsite.domain.model.evento;

import br.com.fatecmogidascruzes.fatecwebsite.infra.dtos.evento.EventoResponseDTO;

import java.time.format.DateTimeFormatter;

public class EventoMapper {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static EventoResponseDTO toDTO(Evento evento) {
        return new EventoResponseDTO(
                evento.getId(),
                evento.getTitulo(),
                evento.getDescricao(),
                evento.getData().format(DATE_FORMATTER)
        );
    }
}
