package com.napoleao.desafio.sicredi.controladores;

import com.napoleao.desafio.sicredi.dtos.AssociadoDto;
import com.napoleao.desafio.sicredi.excecoes.AssociadoJaCadastradoException;
import com.napoleao.desafio.sicredi.formularios.AssociadoForm;
import com.napoleao.desafio.sicredi.servicos.AssociadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/v1/associados")
public class AssociadoController {

    @Autowired
    private AssociadoService associadoService;

    @PostMapping
    public ResponseEntity<AssociadoDto> cadastrarAssociado(@RequestBody AssociadoForm associadoForm){
        try {
            return new ResponseEntity<>(associadoService.cadastrarAssociado(associadoForm), HttpStatus.CREATED);
        } catch (AssociadoJaCadastradoException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
