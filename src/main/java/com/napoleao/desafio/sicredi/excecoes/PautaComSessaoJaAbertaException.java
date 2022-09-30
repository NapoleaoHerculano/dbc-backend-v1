package com.napoleao.desafio.sicredi.excecoes;

public class PautaComSessaoJaAbertaException extends Exception {

    public PautaComSessaoJaAbertaException() {
    }

    @Override
    public String getMessage() {
        return "Já existe uma sessão aberta ou encerrada com essa pauta vinculada!";
    }
}
