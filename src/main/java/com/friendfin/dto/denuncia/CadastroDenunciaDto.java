package com.friendfin.dto.denuncia;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record CadastroDenunciaDto(
        @NotNull(message = "ID do usuário não pode ser nulo")
        long idUsuario,
        @NotBlank(message = "Descrição da denúncia não pode estar em branco")
        @Size(max = 500, message = "A descrição da denúncia deve ter no máximo 500 caracteres")
        String descricao,
        @NotNull(message = "Data e hora da denúncia não podem ser nulas")
        LocalDateTime dataHoraDenuncia
) {}
