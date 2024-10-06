package com.friendfin.model;

import com.friendfin.dto.denuncia.AtualizacaoDenunciaDto;
import com.friendfin.dto.denuncia.CadastroDenunciaDto;
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
@Table(name = "t_ff_denuncias")
@EntityListeners(AuditingEntityListener.class)
public class Denuncia {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "denuncia")
    @SequenceGenerator(name = "denuncia", sequenceName = "seq_mi_denuncia", allocationSize = 1)
    @Column(name = "id_denuncia")
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @Column(name = "ds_denuncia", nullable = false, length = 100)
    private String descricao;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dh_denuncia", nullable = false)
    private LocalDateTime dataHoraDenuncia;

    public Denuncia(CadastroDenunciaDto cadastroDenunciaDto) {
        this.descricao = cadastroDenunciaDto.descricao();
        this.dataHoraDenuncia = cadastroDenunciaDto.dataHoraDenuncia();
    }

    public void atualizarInformacoes(AtualizacaoDenunciaDto atualizacaoDenunciaDto) {
        this.setDescricao(atualizacaoDenunciaDto.descricao());
    }
}
