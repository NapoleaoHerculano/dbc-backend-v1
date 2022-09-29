package com.napoleao.desafio.sicredi.controladores;

import com.napoleao.desafio.sicredi.dtos.SessaoDto;
import com.napoleao.desafio.sicredi.excecoes.AssociadoNaoEncontradoException;
import com.napoleao.desafio.sicredi.excecoes.PautaComSessaoJaAbertaException;
import com.napoleao.desafio.sicredi.excecoes.PautaNaoEncontradaException;
import com.napoleao.desafio.sicredi.formularios.SessaoForm;
import com.napoleao.desafio.sicredi.servicos.SessaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/sessao")
public class SessaoController {

    @Autowired
    private SessaoService sessaoService;

    @PostMapping("/abertura/pauta/{idPauta}")
    public ResponseEntity<SessaoDto> abrirSessaoDeVotacao(@RequestHeader("Authorization") String token, @PathVariable Long idPauta, @RequestBody(required = false) @Valid SessaoForm sessaoForm){
        try {
            return new ResponseEntity<>(sessaoService.abrirSessaoDeVotacao(token, idPauta, sessaoForm), HttpStatus.CREATED);
        } catch (AssociadoNaoEncontradoException | PautaNaoEncontradaException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (PautaComSessaoJaAbertaException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
