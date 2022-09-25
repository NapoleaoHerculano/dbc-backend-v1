package com.napoleao.desafio.sicredi.modelos;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Pauta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;

    @ManyToOne
    private Associado associado;

    @OneToMany(mappedBy = "pauta")
    private List<Voto> votos = new ArrayList<>();
}
