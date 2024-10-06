package com.friendfin.model;

import com.friendfin.dto.usuario.AtualizacaoUsuarioDto;
import com.friendfin.dto.usuario.CadastroUsuarioDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor

@Entity
@Table(name="t_ff_usuario")
@EntityListeners(AuditingEntityListener.class)
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario")
    @SequenceGenerator(name = "usuario", sequenceName = "seq_mi_usuario", allocationSize = 1)
    @Column(name="id_usuario")
    private int id;

    @Column(name="nr_cpf", nullable = false)
    private long cpf;

    @Column(name="nr_rg", nullable = false)
    private String rg;

    @Column(name="nm_usuario", nullable = false)
    private String nome;

    @Column(name="lg_usuario", nullable = false)
    private String login;

    @Column(name="sh_usuario", nullable = false)
    private String senha;

    @Column(name="dt_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @CreatedDate
    @Column(name="dt_cadastro", nullable = false, updatable = false)
    private LocalDate dataCadastro;

    public Usuario(CadastroUsuarioDto cadastroUsuarioDto) {
        this.cpf = cadastroUsuarioDto.cpf();
        this.rg = cadastroUsuarioDto.rg();
        this.nome = cadastroUsuarioDto.nome();
        this.login = cadastroUsuarioDto.login();
        this.senha = cadastroUsuarioDto.senha();
        this.dataNascimento = cadastroUsuarioDto.dataNascimento();
    }

    public void atualizarInformacoesUsuario(AtualizacaoUsuarioDto atualizacaoUsuarioDto) {
        this.cpf = atualizacaoUsuarioDto.cpf();
        this.rg = atualizacaoUsuarioDto.rg();
        this.nome = atualizacaoUsuarioDto.nome();
        this.login = atualizacaoUsuarioDto.login();
        this.senha = atualizacaoUsuarioDto.senha();
        this.dataNascimento = atualizacaoUsuarioDto.dataNascimento();
    }
}
