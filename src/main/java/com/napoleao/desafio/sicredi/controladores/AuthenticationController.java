package com.napoleao.desafio.sicredi.controladores;

import com.napoleao.desafio.sicredi.dtos.TokenDto;
import com.napoleao.desafio.sicredi.formularios.LoginForm;
import com.napoleao.desafio.sicredi.servicos.TokenService;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/token")
@ApiModel(value = "Controlador de Autenticacão")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    @ApiOperation(value = "Gera um token JWT para autenticação do associado no sistema")
    public ResponseEntity<TokenDto> autenticateUser(@RequestBody LoginForm loginForm){
        UsernamePasswordAuthenticationToken loginData = loginForm.converter();

        try {
            Authentication authentication = authenticationManager.authenticate(loginData);
            String token = tokenService.generateToken(authentication);
            return ResponseEntity.ok(new TokenDto(token));
        } catch (AuthenticationException e){
            return ResponseEntity.badRequest().build();
        }
    }
}
