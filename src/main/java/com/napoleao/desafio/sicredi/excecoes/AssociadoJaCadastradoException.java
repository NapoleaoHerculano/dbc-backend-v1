package com.napoleao.desafio.sicredi.excecoes;

public class AssociadoJaCadastradoException extends Exception {
    public AssociadoJaCadastradoException() {
    }

    @Override
    public String getMessage() {
        return "Já existe um associado cadastrado com o CPF enviado!";
    }
}
