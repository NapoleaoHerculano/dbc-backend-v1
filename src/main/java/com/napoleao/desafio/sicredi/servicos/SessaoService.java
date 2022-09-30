package com.napoleao.desafio.sicredi.servicos;

import com.napoleao.desafio.sicredi.dtos.SessaoDto;
import com.napoleao.desafio.sicredi.excecoes.AssociadoNaoEncontradoException;
import com.napoleao.desafio.sicredi.excecoes.PautaComSessaoJaAbertaException;
import com.napoleao.desafio.sicredi.excecoes.PautaNaoEncontradaException;
import com.napoleao.desafio.sicredi.excecoes.SessaoNaoEncontradaException;
import com.napoleao.desafio.sicredi.formularios.SessaoForm;
import com.napoleao.desafio.sicredi.modelos.Associado;
import com.napoleao.desafio.sicredi.modelos.Pauta;
import com.napoleao.desafio.sicredi.modelos.Sessao;
import com.napoleao.desafio.sicredi.repositorios.SessaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class SessaoService {

    @Autowired
    private SessaoRepository sessaoRepository;

    @Autowired
    private PautaService pautaService;

    @Autowired
    private AssociadoService associadoService;

    @Autowired
    private TokenService tokenService;

    public SessaoDto abrirSessaoDeVotacao(String token, Long idPauta, SessaoForm sessaoForm) throws AssociadoNaoEncontradoException, PautaNaoEncontradaException, PautaComSessaoJaAbertaException {
        Long idAssociado = tokenService.getUserIdInToken(recuperarTokenDoHeader(token));
        Associado associado = associadoService.findUserById(idAssociado);

        Pauta pauta = pautaService.buscarPautaPeloId(idPauta);

        Optional<Sessao> sessaoOptional = sessaoRepository.findByPauta(pauta);
        if (sessaoOptional.isPresent()){
            throw new PautaComSessaoJaAbertaException();
        }

        Sessao sessao = new Sessao();
        sessao.setAssociado(associado);
        sessao.setPauta(pauta);
        sessao.setDataDeAbertura(LocalDateTime.now());
        if (sessaoForm == null){
            sessao.setDataDeFechamento(LocalDateTime.now().plusMinutes(1));
        }else {
            sessao.setDataDeFechamento(LocalDateTime.now().plusMinutes(sessaoForm.getDuracaoDaSessao()));
        }

        sessaoRepository.save(sessao);

        return new SessaoDto(sessao);
    }

    public Sessao buscarSessaoPeloId(Long id) throws SessaoNaoEncontradaException {
        Optional<Sessao> sessaoOptional = sessaoRepository.findById(id);
        if (!sessaoOptional.isPresent()){
            throw new SessaoNaoEncontradaException();
        }
        return sessaoOptional.get();
    }

    private String recuperarTokenDoHeader(String token){
        return token.substring(7);
    }

}
