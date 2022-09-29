package com.napoleao.desafio.sicredi.controladores;

import com.napoleao.desafio.sicredi.dtos.ContabilizacaoDaSessaoDto;
import com.napoleao.desafio.sicredi.dtos.VotoDto;
import com.napoleao.desafio.sicredi.excecoes.*;
import com.napoleao.desafio.sicredi.modelos.Decisao;
import com.napoleao.desafio.sicredi.servicos.VotoService;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/v1/votos")
@ApiModel(value = "Controlador de Votos")
public class VotoController {

    @Autowired
    private VotoService votoService;

    @PostMapping("/sessao/{idSessao}/votar-a-favor")
    @ApiOperation(value = "Realiza a inclusão de um voto a favor da pauta considerada na sessão em evidência")
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
    @ApiOperation(value = "Realiza a inclusão de um voto contra a pauta considerada na sessão em evidência")
    public ResponseEntity<VotoDto> votarContra(@RequestHeader("Authorization") String token, @PathVariable Long idSessao){
        try {
            return new ResponseEntity<>(votoService.votar(token, idSessao, Decisao.NAO), HttpStatus.CREATED);
        } catch (AssociadoNaoEncontradoException | PautaNaoEncontradaException | SessaoNaoEncontradaException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (AssociadoJaVotouNaPautaException | SessaoJaEncerradaException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/sessao/{idSessao}/contabilizar")
    @ApiOperation(value = "Contabiliza os votos contra e a favor, e logo após retorna o resultado da votação")
    public ResponseEntity<ContabilizacaoDaSessaoDto> resultadoDaVotacao(@PathVariable Long idSessao){
        try{
            return new ResponseEntity<>(votoService.resultadoDaVotacao(idSessao), HttpStatus.OK);
        } catch (SessaoNaoEncontradaException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
