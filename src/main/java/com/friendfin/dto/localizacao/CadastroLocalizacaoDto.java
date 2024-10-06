package com.friendfin.dto.localizacao;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CadastroLocalizacaoDto(
        @NotNull(message = "Latitude não pode ser nula")
        double latitude,
        @NotNull(message = "Longitude não pode ser nula")
        double longitude,
        @NotBlank(message = "Localidade não pode estar em branco")
        @Size(max = 100, message = "A localidade deve ter no máximo 100 caracteres")
        String localidade
) {}
