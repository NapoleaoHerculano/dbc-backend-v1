package com.napoleao.desafio.sicredi.modelos;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class Sessao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataDeAbertura;
    private LocalDateTime dataDeFechamento;

    @OneToOne
    private Pauta pauta;

    @ManyToOne
    private Associado associado;

}
