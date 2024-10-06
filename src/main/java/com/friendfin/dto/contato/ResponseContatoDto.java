package com.friendfin.dto.contato;

import com.friendfin.model.Contato;

import java.time.LocalDate;

public record ResponseContatoDto(
        long id,
        long idUsuario,
        int ddi,
        int ddd,
        long telefone,
        LocalDate dataCadastro
) {
    public ResponseContatoDto(Contato contato) {
        this(contato.getId(), contato.getUsuario().getId(), contato.getDdi(), contato.getDdd(), contato.getTelefone(), contato.getDataCadastro());
    }
}