package com.napoleao.desafio.sicredi.excecoes;

public class PautaNaoEncontradaException extends Exception {
    public PautaNaoEncontradaException() {
    }

    @Override
    public String getMessage() {
        return "Pauta não encontrada!";
    }
}
