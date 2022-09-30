package com.napoleao.desafio.sicredi.modelos;

public enum Resultado {

    APROVADA("Pauta aprovada"),
    REPROVADA("Pauta reprovada"),
    EMPATE("Empate");

    private String resultado;

    Resultado(String resultado){
        this.resultado = resultado;
    }

    public String getResultado(){
        return resultado;
    }
}
