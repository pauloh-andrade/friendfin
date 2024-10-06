package com.friendfin.model;

import com.friendfin.dto.fiscalizacao.AtualizacaoFiscalizacaoDto;
import com.friendfin.dto.fiscalizacao.CadastroFiscalizacaoDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "t_ff_fiscalizacoes")
@EntityListeners(AuditingEntityListener.class)
public class Fiscalizacao {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fiscalizacao")
    @SequenceGenerator(name = "fiscalizacao", sequenceName = "seq_mi_fiscalizacao", allocationSize = 1)
    @Column(name = "id_fiscalizacao")
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_autoridade", nullable = false)
    private Autoridade autoridade;

    @ManyToOne
    @JoinColumn(name = "id_denuncia", nullable = false)
    private Denuncia denuncia;

    @Column(name = "ds_fiscalizacao", nullable = false, length = 100)
    private String descricao;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dh_fiscalizacao", nullable = false)
    private LocalDateTime dataHoraFiscalizacao;

    public Fiscalizacao(CadastroFiscalizacaoDto cadastroFiscalizacaoDto) {
        this.descricao = cadastroFiscalizacaoDto.descricao();
        this.dataHoraFiscalizacao = cadastroFiscalizacaoDto.dataHoraFiscalizacao();
    }

    public void atualizarInformacoes(AtualizacaoFiscalizacaoDto atualizacaoFiscalizacaoDto) {
        this.setDescricao(atualizacaoFiscalizacaoDto.descricao());
    }

}
