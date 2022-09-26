package com.napoleao.desafio.sicredi.excecoes;

public class SessaoJaEncerradaException extends Exception {

    public SessaoJaEncerradaException() {
    }

    @Override
    public String getMessage() {
        return "Não é possível votar. A sessão selecionada está encerrada!";
    }
}
