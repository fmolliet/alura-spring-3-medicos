package io.winty.alura.learningspring.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import io.winty.alura.learningspring.domain.consulta.AgendaDeConsultas;
import io.winty.alura.learningspring.domain.consulta.DadosAgendamentoConsulta;
import io.winty.alura.learningspring.domain.consulta.DadosDetalhamentoConsulta;
import io.winty.alura.learningspring.domain.medico.Especialidade;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.time.LocalDateTime;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class ConsultaControllerTest {
    
    @Autowired
    private MockMvc mvc;
    
    @Autowired
    private JacksonTester<DadosAgendamentoConsulta> requestJacksonTester;
    
    @Autowired
    private JacksonTester<DadosDetalhamentoConsulta> responseJacksonTester;
    
    @Mock
    private AgendaDeConsultas agendaDeConsultas;
    
    @Test
    @DisplayName("Deveria devoltar codigo http 400 quando informações estão invalidas")
    @WithMockUser
    void agendarCenario1() throws Exception{

        var response = mvc.perform(post("/consultas"))
            .andReturn().getResponse();
            
        assertEquals(response.getStatus(),HttpStatus.BAD_REQUEST.value());
    }
    
    @Test
    @DisplayName("Deveria devoltar codigo http 200 quando informações estão validas")
    @WithMockUser
    void agendarCenario2() throws Exception{
        var data = LocalDateTime.now().plusHours(1);
        var especialidade = Especialidade.CARDIOLOGIA;

        var response = mvc.perform(post("/consultas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJacksonTester.write(
                    new DadosAgendamentoConsulta(892117398458302465l, 890917287491862529l, data, especialidade)
                ).getJson()))
            .andReturn().getResponse();
            
        assertEquals(response.getStatus(),HttpStatus.BAD_REQUEST.value());
    }
}
