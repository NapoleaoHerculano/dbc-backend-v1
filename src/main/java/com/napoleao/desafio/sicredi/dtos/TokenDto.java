package com.napoleao.desafio.sicredi.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenDto {
    private String accessToken;

    private String type = "Bearer";
    private String expiresIn = "1800000";

    public TokenDto(String accessToken) {
        this.accessToken = accessToken;
    }
}
