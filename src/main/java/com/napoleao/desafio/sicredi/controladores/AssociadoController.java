package com.napoleao.desafio.sicredi.controladores;

import com.napoleao.desafio.sicredi.dtos.AssociadoDto;
import com.napoleao.desafio.sicredi.excecoes.AssociadoComCpfJaCadastradoException;
import com.napoleao.desafio.sicredi.excecoes.AssociadoComLoginJaCadastradoException;
import com.napoleao.desafio.sicredi.formularios.AssociadoForm;
import com.napoleao.desafio.sicredi.servicos.AssociadoService;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/associados")
@ApiModel(value = "Controlador de Associados")
public class AssociadoController {

    @Autowired
    private AssociadoService associadoService;

    @PostMapping
    @ApiOperation(value = "Cadastra um associado")
    public ResponseEntity<AssociadoDto> cadastrarAssociado(@RequestBody @Valid AssociadoForm associadoForm){
        try {
            return new ResponseEntity<>(associadoService.cadastrarAssociado(associadoForm), HttpStatus.CREATED);
        } catch (AssociadoComCpfJaCadastradoException | AssociadoComLoginJaCadastradoException e ) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
