package com.napoleao.desafio.sicredi.excecoes;

public class AssociadoComLoginJaCadastradoException extends Exception{
    public AssociadoComLoginJaCadastradoException() {
    }

    @Override
    public String getMessage() {
        return "Já existe um associado cadastrado com o login enviado!";
    }
}
