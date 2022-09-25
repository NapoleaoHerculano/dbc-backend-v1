package com.napoleao.desafio.sicredi.controladores;

import com.napoleao.desafio.sicredi.dtos.PautaDto;
import com.napoleao.desafio.sicredi.excecoes.AssociadoNaoEncontradoException;
import com.napoleao.desafio.sicredi.formularios.PautaForm;
import com.napoleao.desafio.sicredi.servicos.PautaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/v1/pautas")
public class PautaController {

    @Autowired
    private PautaService pautaService;

    @PostMapping
    public ResponseEntity<PautaDto> cadastrarPauta(@RequestHeader("Authorization") String token, @RequestBody PautaForm pautaForm){
        try {
            return new ResponseEntity<>(pautaService.cadastrarPauta(token, pautaForm), HttpStatus.CREATED);
        } catch (AssociadoNaoEncontradoException e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }
}
