package com.napoleao.desafio.sicredi.modelos;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Voto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Decisao decisao = Decisao.SIM;

    @ManyToOne
    private Associado associado;

    @ManyToOne
    private Pauta pauta;
}
