package com.napoleao.desafio.sicredi.dtos;

import com.napoleao.desafio.sicredi.modelos.Associado;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssociadoDto {

    private Long id;
    private String nome;
    private String cpf;

    public AssociadoDto() {
    }

    public AssociadoDto(Associado associado) {
        this.id = associado.getId();
        this.nome = associado.getNome();
        this.cpf = associado.getCpf();
    }
}
