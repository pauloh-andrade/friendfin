package com.friendfin.model;

import com.friendfin.dto.email.AtualizacaoEmailDto;
import com.friendfin.dto.email.CadastroEmailDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "t_ff_email_usuario")
@EntityListeners(AuditingEntityListener.class)
public class Email {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "email_usuario")
    @SequenceGenerator(name = "email_usuario", sequenceName = "seq_mi_email_usuario", allocationSize = 1)
    @Column(name = "id_email")
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @Column(name = "ds_email", nullable = false)
    private String email;

    @CreatedDate
    @Column(name = "dt_cadastro", nullable = false, updatable = false)
    private LocalDate dataCadastro;

    public Email(CadastroEmailDto cadastroEmailDto) {
        this.email = cadastroEmailDto.email();
    }

    public void atualizarInformacoes(AtualizacaoEmailDto atualizacaoEmailDto) {
        this.setEmail(atualizacaoEmailDto.email());
    }
}
