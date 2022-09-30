package com.napoleao.desafio.sicredi.dtos;

import com.napoleao.desafio.sicredi.modelos.Decisao;
import com.napoleao.desafio.sicredi.modelos.Voto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VotoDto {

    private Long id;

    private Decisao decisao;

    private AssociadoDto associado;
    private PautaDto pauta;

    public VotoDto() {
    }

    public VotoDto(Voto voto) {
        this.id = voto.getId();
        this.decisao = voto.getDecisao();
        this.associado = new AssociadoDto(voto.getAssociado());
        this.pauta = new PautaDto(voto.getPauta());
    }
}
