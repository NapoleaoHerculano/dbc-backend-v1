package com.napoleao.desafio.sicredi.servicos;

import com.napoleao.desafio.sicredi.dtos.AssociadoDto;
import com.napoleao.desafio.sicredi.excecoes.AssociadoJaCadastradoException;
import com.napoleao.desafio.sicredi.excecoes.AssociadoNaoEncontradoException;
import com.napoleao.desafio.sicredi.formularios.AssociadoForm;
import com.napoleao.desafio.sicredi.modelos.Associado;
import com.napoleao.desafio.sicredi.repositorios.AssociadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AssociadoService {

    @Autowired
    private AssociadoRepository associadoRepository;

    public AssociadoDto cadastrarAssociado(AssociadoForm associadoForm) throws AssociadoJaCadastradoException {
        Optional<Associado> associadoOptional = associadoRepository.findByCpf(associadoForm.getCpf());
        if (associadoOptional.isPresent()){
            throw new AssociadoJaCadastradoException();
        }

        Associado associado = new Associado(associadoForm);

        associadoRepository.save(associado);

        return new AssociadoDto(associado);

    }

    public Associado findUserById(Long id) throws AssociadoNaoEncontradoException {
        Optional<Associado> associadoOptional = associadoRepository.findById(id);
        if (!associadoOptional.isPresent()){
            throw new AssociadoNaoEncontradoException();
        }
        return associadoOptional.get();
    }
}
