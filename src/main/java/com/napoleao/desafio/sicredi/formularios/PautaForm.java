package com.napoleao.desafio.sicredi.formularios;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class PautaForm {

    @NotBlank
    private String descricao;
}
