package io.winty.alura.learningspring.medico;

import io.winty.alura.learningspring.endereco.DadosEndereco;

public record DadosCadastroMedico(String nome, String email, String crm, Especialidade especialidade, DadosEndereco endereco) {}