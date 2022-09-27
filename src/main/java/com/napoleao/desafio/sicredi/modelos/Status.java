package com.napoleao.desafio.sicredi.modelos;

public enum Status {
    EM_ANDAMENTO("Sessão em andamento"),
    ENCERRADA("Sessão encerrada");

    private String status;

    Status(String status){
        this.status = status;
    }

    public String getDescisao(){
        return status;
    }
}
