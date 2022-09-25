package com.napoleao.desafio.sicredi.controladores;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AssociadoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void deveriaRetornar201CasoOsDadosDeCadastroSejamValidos() throws Exception {
        URI uri = new URI("/v1/associados");
        String json = "{\"nome\":\"Chico dos Testes\",\"cpf\":\"70607118482\"}";

        mockMvc.perform(MockMvcRequestBuilders
                .post(uri)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(201));
    }

    @Test
    public void deveriaRetornar400CasoOsDadosDeCadastroSejamInvalidos() throws Exception {
        URI uri = new URI("/v1/associados");
        String json = "{\"nome\":\"\",\"cpf\":\"\"}";

        mockMvc.perform(MockMvcRequestBuilders
                        .post(uri)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(400));
    }

    @Test
    public void deveriaRetornar400CasoJaExistaUmAssociadoCadastradoComCpfEnviado() throws Exception {
        URI uri = new URI("/v1/associados");
        String json = "{\"nome\":\"Joao dos Testes\",\"cpf\":\"50509837034\"}";

        mockMvc.perform(MockMvcRequestBuilders
                        .post(uri)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON));

        mockMvc.perform(MockMvcRequestBuilders
                        .post(uri)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(400));
    }

}
