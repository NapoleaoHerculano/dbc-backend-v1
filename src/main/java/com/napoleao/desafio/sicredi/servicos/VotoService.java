package com.napoleao.desafio.sicredi.servicos;

import com.napoleao.desafio.sicredi.dtos.VotoDto;
import com.napoleao.desafio.sicredi.excecoes.*;
import com.napoleao.desafio.sicredi.modelos.*;
import com.napoleao.desafio.sicredi.repositorios.VotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class VotoService {

    @Autowired
    private VotoRepository votoRepository;

    @Autowired
    private AssociadoService associadoService;

    @Autowired
    private SessaoService sessaoService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private PautaService pautaService;

    public VotoDto votar(String token, Long idSessao, Decisao decisao) throws AssociadoNaoEncontradoException, SessaoNaoEncontradaException, PautaNaoEncontradaException, AssociadoJaVotouNaPautaException, SessaoJaEncerradaException {
        Long idAssociado = tokenService.getUserIdInToken(recuperarTokenDoHeader(token));
        Associado associado = associadoService.findUserById(idAssociado);

        Sessao sessao = sessaoService.buscarSessaoPeloId(idSessao);
        if (LocalDateTime.now().isAfter(sessao.getDataDeFechamento())){
            throw new SessaoJaEncerradaException();
        }

        Pauta pauta = pautaService.buscarPautaPeloId(sessao.getPauta().getId());

        Optional<Voto> votoOptional = votoRepository.findBySessaoAndPautaAndAssociado(sessao, pauta, associado);
        if (votoOptional.isPresent()){
            throw new AssociadoJaVotouNaPautaException();
        }

        Voto voto = new Voto();
        voto.setAssociado(associado);
        voto.setSessao(sessao);
        voto.setPauta(pauta);
        voto.setDecisao(decisao);

        votoRepository.save(voto);

        return new VotoDto(voto);
    }

    private String recuperarTokenDoHeader(String token){
        return token.substring(7);
    }
}
