package com.friendfin.dto.fiscalizacao;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record AtualizacaoFiscalizacaoDto(
        @NotNull(message = "ID da fiscalização não pode ser nulo")
        int id,
        @NotNull(message = "ID da autoridade não pode ser nulo")
        int idAutoridade,
        @NotNull(message = "ID da denúncia não pode ser nulo")
        int idDenuncia,
        @NotBlank(message = "Descrição da fiscalização não pode estar em branco")
        @Size(max = 500, message = "A descrição da fiscalização deve ter no máximo 500 caracteres")
        String descricao,
        @NotNull(message = "Data e hora da fiscalização não podem ser nulas")
        LocalDateTime dataHoraFiscalizacao
) {
}
