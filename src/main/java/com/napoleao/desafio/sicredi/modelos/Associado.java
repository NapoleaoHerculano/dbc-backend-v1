package com.napoleao.desafio.sicredi.modelos;

import com.napoleao.desafio.sicredi.formularios.AssociadoForm;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Associado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;

    @OneToMany(mappedBy = "associado")
    private List<Pauta> pautas = new ArrayList<>();

    @OneToMany(mappedBy = "associado")
    private List<Voto> votos = new ArrayList<>();

    public Associado() {
    }

    public Associado(AssociadoForm associadoForm){
        this.nome = associadoForm.getNome();
        this.cpf = associadoForm.getCpf();
    }
}
