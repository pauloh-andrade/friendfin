package com.friendfin.model;

import com.friendfin.dto.contato.AtualizacaoContatoDto;
import com.friendfin.dto.contato.CadastroContatoDto;
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
@Table(name = "t_ff_cont_usuario")
@EntityListeners(AuditingEntityListener.class)
public class Contato {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contato_usuario")
    @SequenceGenerator(name = "contato_usuario", sequenceName = "seq_mi_contato_usuario", allocationSize = 1)
    @Column(name = "id_contato")
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @Column(name = "nr_ddi", nullable = false)
    private int ddi;

    @Column(name = "nr_ddd", nullable = false)
    private int ddd;

    @Column(name = "nr_telefone", nullable = false)
    private long telefone;

    @CreatedDate
    @Column(name = "dt_cadastro", nullable = false, updatable = false)
    private LocalDate dataCadastro;

    public Contato(CadastroContatoDto cadastroContatoDto) {
        this.ddi = cadastroContatoDto.ddi();
        this.ddd = cadastroContatoDto.ddd();
        this.telefone = cadastroContatoDto.telefone();
    }

    public void atualizarInformacoes(AtualizacaoContatoDto atualizacaoContatoDto) {
        this.setDdi(atualizacaoContatoDto.ddi());
        this.setDdd(atualizacaoContatoDto.ddd());
        this.setTelefone(atualizacaoContatoDto.telefone());
    }
}
