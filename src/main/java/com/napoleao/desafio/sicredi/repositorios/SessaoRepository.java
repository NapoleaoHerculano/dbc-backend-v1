package com.napoleao.desafio.sicredi.repositorios;

import com.napoleao.desafio.sicredi.modelos.Sessao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessaoRepository extends JpaRepository<Sessao, Long> {
}
