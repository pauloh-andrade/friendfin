package com.friendfin.dto.email;

import com.friendfin.model.Email;

import java.time.LocalDate;

public record ResponseEmailDto(
        long id,
        long idUsuario,
        String email,
        LocalDate dataCadastro
) {
    public ResponseEmailDto(Email email) {
        this(email.getId(), email.getUsuario().getId(), email.getEmail(), email.getDataCadastro());
    }
}