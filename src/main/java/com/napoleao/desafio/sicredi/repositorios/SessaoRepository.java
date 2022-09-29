package com.napoleao.desafio.sicredi.repositorios;

import com.napoleao.desafio.sicredi.modelos.Pauta;
import com.napoleao.desafio.sicredi.modelos.Sessao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessaoRepository extends JpaRepository<Sessao, Long> {

    Optional<Sessao> findByPauta(Pauta pauta);
}
