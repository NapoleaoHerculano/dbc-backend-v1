package com.napoleao.desafio.sicredi.modelos;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@Data
public class Voto {

    private Long id;

    @Enumerated(EnumType.STRING)
    private Decisao decisao;
}
