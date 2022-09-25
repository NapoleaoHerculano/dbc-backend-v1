package com.napoleao.desafio.sicredi.dtos;

import com.napoleao.desafio.sicredi.modelos.Sessao;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SessaoDto {

    private Long id;

    private LocalDateTime dataDeAbertura;
    private LocalDateTime dataDeFechamento;

    private PautaDto pauta;
    private AssociadoDto associado;

    public SessaoDto() {
    }

    public SessaoDto(Sessao sessao) {
        this.id = sessao.getId();
        this.dataDeAbertura = sessao.getDataDeAbertura();
        this.dataDeFechamento = sessao.getDataDeFechamento();
        this.pauta = new PautaDto(sessao.getPauta());
        this.associado = new AssociadoDto(sessao.getAssociado());
    }
}
