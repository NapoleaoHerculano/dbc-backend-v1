package com.napoleao.desafio.sicredi.excecoes;

public class AssociadoNaoEncontradoException extends Exception {

    public AssociadoNaoEncontradoException() {
    }

    @Override
    public String getMessage() {
        return "Associado não encontrado!";
    }
}
