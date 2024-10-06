package com.friendfin.dto.autoridade;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CadastroAutoridadeDto(
        @NotBlank(message = "Nome não pode estar em branco")
        @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
        String nome,
        @NotBlank(message = "Órgão não pode estar em branco")
        @Size(max = 100, message = "O órgão deve ter no máximo 100 caracteres")
        String orgao,
        @NotBlank(message = "Cargo não pode estar em branco")
        @Size(max = 100, message = "O cargo deve ter no máximo 100 caracteres")
        String cargo,
        @NotBlank(message = "Login não pode estar em branco")
        @Size(max = 30, message = "O login deve ter no máximo 30 caracteres")
        String login,
        @NotBlank(message = "Senha não pode estar em branco")
        @Size(max = 30, message = "A senha deve ter no máximo 30 caracteres")
        String senha
) {
}
