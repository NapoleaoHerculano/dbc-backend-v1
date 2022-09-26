package com.napoleao.desafio.sicredi.excecoes;

public class SessaoNaoEncontradaException extends Exception {
    public SessaoNaoEncontradaException() {
    }

    @Override
    public String getMessage() {
        return "Sessão não encontrada!";
    }
}
