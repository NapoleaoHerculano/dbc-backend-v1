package com.napoleao.desafio.sicredi.controladores;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Profile("test")
public class AssociadoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void deveriaRetornar201CasoOsDadosDeCadastroSejamValidos() throws Exception {
        URI uri = new URI("/v1/associados");
        String requisicao = "{" +
                        "\"nome\":\"Chico dos Testes\"," +
                        "\"cpf\":\"14836586003\"," +
                        "\"login\":\"chico.testes\"," +
                        "\"senha\":\"123456\"" +
                        "}";

        mockMvc.perform(MockMvcRequestBuilders
                .post(uri)
                .content(requisicao)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(201));
    }

    @Test
    public void deveriaRetornar400CasoAlgumDosDadosDeCadastroSejamInvalidos() throws Exception {
        URI uri = new URI("/v1/associados");
        String json = "{" +
                "\"nome\":\"Joao dos Testes\"," +
                "\"cpf\":\"93979774074\"," +
                "\"login\":\"\"," +
                "\"senha\":\"123456\"" +
                "}";

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
        String requisicao01 = "{" +
                "\"nome\":\"Jose dos Testes\"," +
                "\"cpf\":\"82000156029\"," +
                "\"login\":\"jose.testes\"," +
                "\"senha\":\"123456\"" +
                "}";

        String requisicao02 = "{" +
                "\"nome\":\"Pedro dos Testes\"," +
                "\"cpf\":\"82000156029\"," +
                "\"login\":\"pedro.testes\"," +
                "\"senha\":\"123456\"" +
                "}";

        mockMvc.perform(MockMvcRequestBuilders
                        .post(uri)
                        .content(requisicao01)
                        .contentType(MediaType.APPLICATION_JSON));

        mockMvc.perform(MockMvcRequestBuilders
                        .post(uri)
                        .content(requisicao02)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(400));
    }

    @Test
    public void deveriaRetornar400CasoJaExistaUmAssociadoCadastradoComLoginEnviado() throws Exception {
        URI uri = new URI("/v1/associados");
        String requisicao01 = "{" +
                "\"nome\":\"Biu dos Testes\"," +
                "\"cpf\":\"34565511002\"," +
                "\"login\":\"biu.testes\"," +
                "\"senha\":\"123456\"" +
                "}";

        String requisicao02 = "{" +
                "\"nome\":\"Biu dos Testes\"," +
                "\"cpf\":\"56863853000\"," +
                "\"login\":\"biu.testes\"," +
                "\"senha\":\"123456\"" +
                "}";

        mockMvc.perform(MockMvcRequestBuilders
                .post(uri)
                .content(requisicao01)
                .contentType(MediaType.APPLICATION_JSON));

        mockMvc.perform(MockMvcRequestBuilders
                        .post(uri)
                        .content(requisicao02)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(400));
    }

}
