package com.friendfin.dto.usuario;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record CadastroUsuarioDto(
        @NotNull(message = "o campo não pode ser nulo")
        @Size(max = 11, message = "O cpf deve ter no máximo 11 caracteres")
        long cpf,
        @NotBlank(message = "o campo não pode estar em branco")
        @Size(max = 20, message = "O RG deve ter no máximo 20 caracteres")
        String rg,
        @NotBlank(message = "o campo não pode estar em branco")
        @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
        String nome,
        @NotBlank(message = "o campo não pode estar em branco")
        @Size(max = 100, message = "O login deve ter no máximo 100 caracteres")
        String login,
        @NotBlank(message = "o campo não pode estar em branco")
        @Size(max = 100, message = "A senha deve ter no máximo 100 caracteres")
        String senha,
        @PastOrPresent
        @NotNull
        LocalDate dataNascimento
) {}

