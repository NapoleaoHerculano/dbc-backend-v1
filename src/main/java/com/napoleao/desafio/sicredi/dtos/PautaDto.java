package com.napoleao.desafio.sicredi.dtos;

import com.napoleao.desafio.sicredi.modelos.Pauta;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PautaDto {

    private Long id;
    private String descricao;

    public PautaDto() {
    }

    public PautaDto(Pauta pauta) {
        this.id = pauta.getId();
        this.descricao = pauta.getDescricao();
    }
}
