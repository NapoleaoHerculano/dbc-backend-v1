package com.napoleao.desafio.sicredi.modelos;

import com.napoleao.desafio.sicredi.formularios.AssociadoForm;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Data
public class Associado implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;

    private String login;

    private String senha;

    @OneToMany(mappedBy = "associado")
    private List<Pauta> pautas = new ArrayList<>();

    @OneToMany(mappedBy = "associado")
    private List<Voto> votos = new ArrayList<>();

    @OneToMany(mappedBy = "associado")
    private List<Sessao> sessoes = new ArrayList<>();

    public Associado() {
    }

    public Associado(AssociadoForm associadoForm){
        this.nome = associadoForm.getNome();
        this.cpf = associadoForm.getCpf();
        this.login = associadoForm.getLogin();
        this.senha = new BCryptPasswordEncoder().encode(associadoForm.getSenha());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.nome;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
