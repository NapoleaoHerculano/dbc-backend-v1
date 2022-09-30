package com.napoleao.desafio.sicredi.controladores;

import com.napoleao.desafio.sicredi.dtos.PautaDto;
import com.napoleao.desafio.sicredi.excecoes.AssociadoNaoEncontradoException;
import com.napoleao.desafio.sicredi.formularios.PautaForm;
import com.napoleao.desafio.sicredi.servicos.PautaService;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/pautas")
@ApiModel(value = "Contralode de Pautas")
public class PautaController {

    @Autowired
    private PautaService pautaService;

    @PostMapping
    @ApiOperation(value = "Realiza o cadastro de uma pauta no sistema")
    public ResponseEntity<PautaDto> cadastrarPauta(@RequestHeader("Authorization") String token, @RequestBody @Valid PautaForm pautaForm){
        try {
            return new ResponseEntity<>(pautaService.cadastrarPauta(token, pautaForm), HttpStatus.CREATED);
        } catch (AssociadoNaoEncontradoException e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
        }
    }
}
