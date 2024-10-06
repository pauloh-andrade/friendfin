package com.friendfin.dto.localizacao;

import com.friendfin.model.Localizacao;

public record ResponseLocalizacaoDto(
        int id,
        double latitude,
        double longitude,
        String localidade
) {
    public ResponseLocalizacaoDto(Localizacao localizacao) {
        this(localizacao.getId(), localizacao.getLatitude(), localizacao.getLongitude(), localizacao.getLocalidade());
    }
}