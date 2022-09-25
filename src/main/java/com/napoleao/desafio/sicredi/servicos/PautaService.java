package com.napoleao.desafio.sicredi.servicos;

import com.napoleao.desafio.sicredi.dtos.PautaDto;
import com.napoleao.desafio.sicredi.excecoes.AssociadoNaoEncontradoException;
import com.napoleao.desafio.sicredi.formularios.PautaForm;
import com.napoleao.desafio.sicredi.modelos.Associado;
import com.napoleao.desafio.sicredi.modelos.Pauta;
import com.napoleao.desafio.sicredi.repositorios.PautaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PautaService {

    @Autowired
    private PautaRepository pautaRepository;

    @Autowired
    private AssociadoService associadoService;

    @Autowired
    private TokenService tokenService;

    public PautaDto cadastrarPauta(String token, PautaForm pautaForm) throws AssociadoNaoEncontradoException {
        Long idAssociado = tokenService.getUserIdInToken(recuperarTokenDoHeader(token));
        Associado associado = associadoService.findUserById(idAssociado);

        Pauta pauta = new Pauta(pautaForm);
        pauta.setAssociado(associado);

        pautaRepository.save(pauta);

        return new PautaDto(pauta);

    }

    private String recuperarTokenDoHeader(String token){
        return token.substring(7);
    }

}
