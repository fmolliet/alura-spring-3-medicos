package io.winty.alura.learningspring.domain.consulta.validacoes;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import io.winty.alura.learningspring.domain.consulta.DadosAgendamentoConsulta;
import io.winty.alura.learningspring.infra.exception.ValidacaoException;

@Component
public class ValidadorHorarioAntecedencia implements ValidadorDadosAgendamentoConsulta{
    public void validar(DadosAgendamentoConsulta dados) {
        var dataConsulta = dados.data();
        var agora = LocalDateTime.now();
        var diferencaEmMinutos = Duration.between(agora, dataConsulta).toMinutes();
        if (diferencaEmMinutos < 30) {
            throw new ValidacaoException("Consulta deve ser agendada com antecedência mínima de 30 minutos");
        }
    }
}
