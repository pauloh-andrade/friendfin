package com.friendfin.dto.denuncia;

import com.friendfin.model.Denuncia;

import java.time.LocalDateTime;

public record ResponseDenunciaDto(
        int id,
        long idUsuario,
        String descricao,
        LocalDateTime dataHoraDenuncia
) {
    public ResponseDenunciaDto(Denuncia denuncia) {
        this(denuncia.getId(), denuncia.getUsuario().getId(), denuncia.getDescricao(), denuncia.getDataHoraDenuncia());
    }
}