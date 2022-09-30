package com.napoleao.desafio.sicredi.controladores;

import com.napoleao.desafio.sicredi.modelos.Associado;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestEntityManager
@Transactional
@Profile("test")
public class PautaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestEntityManager testEntityManager;

    private String token;

    @Before
    public void setUp() throws Exception{
        Associado associado = new Associado();
        associado.setNome("Chico dos Testes");
        associado.setCpf("14836586003");
        associado.setLogin("chico.testes");
        associado.setSenha(new BCryptPasswordEncoder().encode("123456"));

        testEntityManager.persist(associado);

        URI uri = new URI("/token");
        String requisicao = "{" +
                "\"login\":\"chico.testes\"," +
                "\"senha\":\"123456\"" +
                "}";

        String respostaAutenticacao = mockMvc.perform(MockMvcRequestBuilders
                        .post(uri)
                        .content(requisicao)
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers
                                .status()
                                .is(200))
                        .andReturn().getResponse().getContentAsString();

        JSONObject jsonObject = new JSONObject(respostaAutenticacao);
        token = jsonObject.getString("accessToken");
    }

    @Test
    public void deveriaRetornar201CasoOsDadosDaPautaSejamValidos() throws Exception{
        URI uri = new URI("/v1/pautas");
        String requisicao = "{" +
                "\"descricao\":\"Pauta Teste\"}";

        mockMvc.perform(MockMvcRequestBuilders
                        .post(uri)
                        .content(requisicao)
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(201));
    }

    @Test
    public void deveriaRetornar403CasoOTokenEnviadoSejaInvalido() throws Exception{
        URI uri = new URI("/v1/pautas");
        String requisicao = "{" +
                "\"descricao\":\"Pauta Teste\"}";

        mockMvc.perform(MockMvcRequestBuilders
                        .post(uri)
                        .content(requisicao)
                        .header("Authorization", "Bearer " + "token-de-mentira")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(403));
    }

    @Test
    public void deveriaRetornar400CasoOsDadosDaPautaSejamInvalidos() throws Exception{
        URI uri = new URI("/v1/pautas");
        String requisicao = "{" +
                "\"descricao\":\"\"}";

        mockMvc.perform(MockMvcRequestBuilders
                        .post(uri)
                        .content(requisicao)
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(400));
    }



}
