package com.friendfin.dto.usuario;

import com.friendfin.model.Usuario;
import java.time.LocalDate;

public record ResponseUsuarioDto(
        int id,
        long cpf,
        String rg,
        String nome,
        String login,
        String senha,
        LocalDate dataNascimento,
        LocalDate dataCadastro
) {
    public ResponseUsuarioDto(Usuario usuario) {
        this(usuario.getId(), usuario.getCpf(), usuario.getRg(), usuario.getNome(), usuario.getLogin(), usuario.getSenha(), usuario.getDataNascimento(), usuario.getDataCadastro());
    }
}
