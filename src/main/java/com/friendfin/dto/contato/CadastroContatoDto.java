package com.friendfin.dto.contato;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CadastroContatoDto(
        @NotNull(message = "DDI não pode ser nulo")
        int ddi,
        @NotNull(message = "DDD não pode ser nulo")
        int ddd,
        @NotNull(message = "Telefone não pode ser nulo")
        @Size(min = 8, max = 15, message = "O telefone deve ter entre 8 e 15 dígitos")
        long telefone
) {

}
