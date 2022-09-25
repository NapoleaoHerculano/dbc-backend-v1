package com.napoleao.desafio.sicredi.servicos;

import com.napoleao.desafio.sicredi.modelos.Associado;
import com.napoleao.desafio.sicredi.repositorios.AssociadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private AssociadoRepository associadoRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<Associado> associado = associadoRepository.findByLogin(login);

        if (associado.isPresent()){
            return associado.get();
        }

        throw new UsernameNotFoundException("Usuario invalido!");
    }
}
