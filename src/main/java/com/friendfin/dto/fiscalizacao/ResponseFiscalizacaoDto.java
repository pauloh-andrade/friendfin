package com.friendfin.dto.fiscalizacao;

import com.friendfin.model.Fiscalizacao;

import java.time.LocalDateTime;

public record ResponseFiscalizacaoDto(
        int id,
        int idAutoridade,
        int idDenuncia,
        String descricao,
        LocalDateTime dataHoraFiscalizacao
) {
    public ResponseFiscalizacaoDto(Fiscalizacao fiscalizacao) {
        this(fiscalizacao.getId(), fiscalizacao.getAutoridade().getId(), fiscalizacao.getDenuncia().getId(), fiscalizacao.getDescricao(), fiscalizacao.getDataHoraFiscalizacao());
    }
}