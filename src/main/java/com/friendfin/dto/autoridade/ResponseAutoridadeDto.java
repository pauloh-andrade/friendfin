package com.friendfin.dto.autoridade;

import com.friendfin.model.Autoridade;

import java.time.LocalDate;

public record ResponseAutoridadeDto(
        int id,
        String nome,
        String orgao,
        String cargo,
        String login,
        String senha,
        LocalDate dataCadastro
) {
    public ResponseAutoridadeDto(Autoridade autoridade) {
        this(autoridade.getId(), autoridade.getNome(), autoridade.getOrgao(), autoridade.getCargo(), autoridade.getLogin(), autoridade.getSenha(), autoridade.getDataCadastro());
    }
}
