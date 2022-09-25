package com.napoleao.desafio.sicredi.filtros;

import com.napoleao.desafio.sicredi.excecoes.AssociadoNaoEncontradoException;
import com.napoleao.desafio.sicredi.modelos.Associado;
import com.napoleao.desafio.sicredi.servicos.AssociadoService;
import com.napoleao.desafio.sicredi.servicos.TokenService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationByTokenFilter extends OncePerRequestFilter {

    private TokenService tokenService;

    private AssociadoService associadoService;

    public AuthenticationByTokenFilter(TokenService tokenService, AssociadoService associadoService){
        this.tokenService = tokenService;
        this.associadoService = associadoService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = recoverToken(request);
        boolean isValid = tokenService.isValidToken(token);
        if (isValid){
            try {
                autenticateUser(token);
            } catch (AssociadoNaoEncontradoException e) {
                throw new RuntimeException(e);
            }
        }
        filterChain.doFilter(request, response);
    }

    private void autenticateUser(String token) throws AssociadoNaoEncontradoException {
        Long idUsuario = tokenService.getUserIdInToken(token);
        Associado associado = associadoService.findUserById(idUsuario);

        if (associado != null){
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(associado, null, associado.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
    }

    private String recoverToken(HttpServletRequest httpServletRequest){
        String token = httpServletRequest.getHeader("Authorization");
        if (token == null || !token.startsWith("Bearer ")){
            return null;
        }

        return token.substring(7, token.length());
    }
}
