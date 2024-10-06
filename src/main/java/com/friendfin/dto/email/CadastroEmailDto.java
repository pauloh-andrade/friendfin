package com.friendfin.dto.email;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CadastroEmailDto(
        @NotNull(message = "ID do usuário não pode ser nulo")
        long idUsuario,
        @NotBlank(message = "Endereço de email não pode estar em branco")
        @Email(message = "Endereço de email inválido")
        @Size(max = 100, message = "O endereço de email deve ter no máximo 100 caracteres")
        String email
) {
}
