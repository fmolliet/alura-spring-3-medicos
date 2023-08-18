package io.winty.alura.learningspring.domain.paciente;

import jakarta.validation.constraints.NotNull;
import io.winty.alura.learningspring.domain.endereco.DadosEndereco;

public record DadosAtualizacaoPaciente(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco) {
}
