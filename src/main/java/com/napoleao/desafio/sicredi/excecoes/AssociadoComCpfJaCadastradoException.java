package com.napoleao.desafio.sicredi.excecoes;

public class AssociadoComCpfJaCadastradoException extends Exception {
    public AssociadoComCpfJaCadastradoException() {
    }

    @Override
    public String getMessage() {
        return "Já existe um associado cadastrado com o CPF enviado!";
    }
}
