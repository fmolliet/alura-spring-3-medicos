package io.winty.alura.learningspring.domain.consulta.validacoes;

import java.time.DayOfWeek;

import io.winty.alura.learningspring.domain.consulta.DadosAgendamentoConsulta;
import io.winty.alura.learningspring.infra.exception.ValidacaoException;

public class ValidadorHorarioFuncionamentoClinica {
    
    public void validar(DadosAgendamentoConsulta dados){
        var dataConsulta = dados.data();
        
        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesDaAberturaDaClinica = dataConsulta.getHour() < 7;
        var depoisDoEncerramentoDaClinica = dataConsulta.getHour() > 18;
        if (domingo || antesDaAberturaDaClinica || depoisDoEncerramentoDaClinica) {
            throw new ValidacaoException("Consulta fora do horário de funcionamento da clínica");
        }
    }
}
