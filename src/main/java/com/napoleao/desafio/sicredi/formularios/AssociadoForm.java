package com.napoleao.desafio.sicredi.formularios;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class AssociadoForm {

    @NotBlank
    private String nome;

    @NotBlank
    @CPF
    private String cpf;
}
