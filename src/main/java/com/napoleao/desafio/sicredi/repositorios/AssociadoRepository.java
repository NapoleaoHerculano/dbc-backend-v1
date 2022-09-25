package com.napoleao.desafio.sicredi.repositorios;

import com.napoleao.desafio.sicredi.modelos.Associado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AssociadoRepository extends JpaRepository<Associado, Long> {

    Optional<Associado> findByCpf(String cpf);
}
