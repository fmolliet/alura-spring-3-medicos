package io.winty.alura.learningspring.domain.consulta.validacoes;

import io.winty.alura.learningspring.domain.consulta.DadosAgendamentoConsulta;

public interface ValidadorDadosAgendamentoConsulta {
    void validar(DadosAgendamentoConsulta dados);
}
