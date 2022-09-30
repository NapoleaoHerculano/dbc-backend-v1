package com.napoleao.desafio.sicredi.repositorios;

import com.napoleao.desafio.sicredi.modelos.Associado;
import com.napoleao.desafio.sicredi.modelos.Pauta;
import com.napoleao.desafio.sicredi.modelos.Sessao;
import com.napoleao.desafio.sicredi.modelos.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VotoRepository extends JpaRepository<Voto, Long> {

    Optional<Voto> findBySessaoAndPautaAndAssociado(Sessao sessao, Pauta pauta, Associado associado);

    @Query(nativeQuery = true, value = "SELECT COUNT(*) FROM VOTO WHERE DECISAO = 'SIM' AND SESSAO_ID = ?1 ")
    Integer contabilizarVotosAFavor(Long idSessao);

    @Query(nativeQuery = true, value = "SELECT COUNT(*) FROM VOTO WHERE DECISAO = 'NAO' AND SESSAO_ID = ?1 ")
    Integer contabilizarVotosContra(Long idSessao);
}
