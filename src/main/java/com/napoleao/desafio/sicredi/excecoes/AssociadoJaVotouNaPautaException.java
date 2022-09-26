package com.napoleao.desafio.sicredi.excecoes;

public class AssociadoJaVotouNaPautaException extends Exception {
    public AssociadoJaVotouNaPautaException() {
    }

    @Override
    public String getMessage() {
        return "Não é possível votar. O associado já votou nesta pauta!";
    }
}
