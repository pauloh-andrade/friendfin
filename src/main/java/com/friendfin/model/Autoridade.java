package com.friendfin.model;

import com.friendfin.dto.autoridade.AtualizacaoAutoridadeDto;
import com.friendfin.dto.autoridade.CadastroAutoridadeDto;
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
@Table(name = "t_ff_autoridade")
@EntityListeners(AuditingEntityListener.class)
public class Autoridade {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "autoridade")
    @SequenceGenerator(name = "autoridade", sequenceName = "seq_mi_autoridade", allocationSize = 1)
    @Column(name = "id_autoridade")
    private int id;

    @Column(name = "nm_autoridade", nullable = false)
    private String nome;

    @Column(name = "og_autoridade", nullable = false)
    private String orgao;

    @Column(name = "cg_autoridade", nullable = false)
    private String cargo;

    @Column(name = "lg_usuario", nullable = false)
    private String login;

    @Column(name = "sh_usuario", nullable = false)
    private String senha;

    @CreatedDate
    @Column(name = "dt_cadastro", nullable = false, updatable = false)
    private LocalDate dataCadastro;

    public Autoridade(CadastroAutoridadeDto cadastroAutoridadeDto) {
        this.nome = cadastroAutoridadeDto.nome();
        this.orgao = cadastroAutoridadeDto.orgao();
        this.cargo = cadastroAutoridadeDto.cargo();
        this.login = cadastroAutoridadeDto.login();
        this.senha = cadastroAutoridadeDto.senha();
    }

    public void atualizarInformacoes(AtualizacaoAutoridadeDto atualizacaoAutoridadeDto) {
        this.setNome(atualizacaoAutoridadeDto.nome());
        this.setOrgao(atualizacaoAutoridadeDto.orgao());
        this.setCargo(atualizacaoAutoridadeDto.cargo());
        this.setLogin(atualizacaoAutoridadeDto.login());
        this.setSenha(atualizacaoAutoridadeDto.senha());
    }

}
