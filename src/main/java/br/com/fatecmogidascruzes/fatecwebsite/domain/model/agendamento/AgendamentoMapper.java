package br.com.fatecmogidascruzes.fatecwebsite.domain.model.agendamento;

import br.com.fatecmogidascruzes.fatecwebsite.infra.dtos.agendamento.AgendamentoResponseDTO;

import java.time.format.DateTimeFormatter;

public class AgendamentoMapper {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static AgendamentoResponseDTO toDTO(Agendamento agendamento) {
        return new AgendamentoResponseDTO(
                agendamento.getId(),
                agendamento.getTitulo(),
                agendamento.getDescricao(),
                agendamento.getData().format(DATE_FORMATTER)
        );
    }
}
