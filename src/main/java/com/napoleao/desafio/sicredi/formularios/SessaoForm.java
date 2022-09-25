package com.napoleao.desafio.sicredi.formularios;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class SessaoForm {

    @NotNull
    private Integer duracaoDaSessao;
}
