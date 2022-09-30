package com.napoleao.desafio.sicredi.dtos;

import com.napoleao.desafio.sicredi.modelos.Resultado;
import com.napoleao.desafio.sicredi.modelos.Sessao;
import com.napoleao.desafio.sicredi.modelos.Status;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Getter
@Setter
public class ContabilizacaoDaSessaoDto {

    private Long id;
    private LocalDateTime dataDeAbertura;
    private LocalDateTime dataDeFechamento;
    private PautaDto pauta;
    private Integer votosAFavor;
    private Integer votosContra;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Enumerated(EnumType.STRING)
    private Resultado resultado;

    public ContabilizacaoDaSessaoDto() {
    }

    public ContabilizacaoDaSessaoDto(Sessao sessao, Integer votosAFavor, Integer votosContra, Status status, Resultado resultado) {
        this.id = sessao.getId();
        this.dataDeAbertura = sessao.getDataDeAbertura();
        this.dataDeFechamento = sessao.getDataDeFechamento();
        this.pauta = new PautaDto(sessao.getPauta());
        this.votosAFavor = votosAFavor;
        this.votosContra = votosContra;
        this.status = status;
        this.resultado = resultado;
    }
}
