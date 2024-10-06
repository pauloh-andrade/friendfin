package com.friendfin.model;

import com.friendfin.dto.localizacao.AtualizacaoLocalizacaoDto;
import com.friendfin.dto.localizacao.CadastroLocalizacaoDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "t_ff_localizacoes")
public class Localizacao {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "localizacao")
    @SequenceGenerator(name = "localizacao", sequenceName = "seq_mi_localizacao", allocationSize = 1)
    @Column(name = "id_localizacao")
    private int id;

    @Column(name = "lc_latitude", nullable = false)
    private double latitude;

    @Column(name = "lc_longitude", nullable = false)
    private double longitude;

    @Column(name = "ds_localidade", nullable = false, length = 100)
    private String localidade;

    public Localizacao(CadastroLocalizacaoDto cadastroLocalizacaoDto) {
        this.latitude = cadastroLocalizacaoDto.latitude();
        this.longitude = cadastroLocalizacaoDto.longitude();
        this.localidade = cadastroLocalizacaoDto.localidade();
    }

    public void atualizarInformacoes(AtualizacaoLocalizacaoDto atualizacaoLocalizacaoDto) {
        this.setLatitude(atualizacaoLocalizacaoDto.latitude());
        this.setLongitude(atualizacaoLocalizacaoDto.longitude());
        this.setLocalidade(atualizacaoLocalizacaoDto.localidade());
    }
}
