package com.napoleao.desafio.sicredi.controladores;

import com.napoleao.desafio.sicredi.dtos.VotoDto;
import com.napoleao.desafio.sicredi.excecoes.*;
import com.napoleao.desafio.sicredi.modelos.Decisao;
import com.napoleao.desafio.sicredi.servicos.VotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/v1/votos")
public class VotoController {

    @Autowired
    private VotoService votoService;

    @PostMapping("/sessao/{idSessao}/votar-a-favor")
    public ResponseEntity<VotoDto> votarAFavor(@RequestHeader("Authorization") String token, @PathVariable Long idSessao){
        try {
            return new ResponseEntity<>(votoService.votar(token, idSessao, Decisao.SIM), HttpStatus.CREATED);
        } catch (AssociadoNaoEncontradoException | PautaNaoEncontradaException | SessaoNaoEncontradaException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (AssociadoJaVotouNaPautaException | SessaoJaEncerradaException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/sessao/{idSessao}/votar-contra")
    public ResponseEntity<VotoDto> votarContra(@RequestHeader("Authorization") String token, @PathVariable Long idSessao){
        try {
            return new ResponseEntity<>(votoService.votar(token, idSessao, Decisao.NAO), HttpStatus.CREATED);
        } catch (AssociadoNaoEncontradoException | PautaNaoEncontradaException | SessaoNaoEncontradaException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (AssociadoJaVotouNaPautaException | SessaoJaEncerradaException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
