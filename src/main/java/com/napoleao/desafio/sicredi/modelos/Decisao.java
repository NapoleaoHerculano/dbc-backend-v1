package com.napoleao.desafio.sicredi.modelos;

public enum Decisao {
    SIM("Sim"),
    NAO("Não");

    private String decisao;

    Decisao(String decisao){
        this.decisao = decisao;
    }

    public String getDecisao() {
        return decisao;
    }
}
