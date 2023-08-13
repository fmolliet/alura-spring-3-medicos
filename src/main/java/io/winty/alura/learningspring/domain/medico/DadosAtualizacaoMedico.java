package io.winty.alura.learningspring.domain.medico;

import io.winty.alura.learningspring.domain.endereco.DadosEndereco;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoMedico(
        @NotNull Long id, String nome, String telefone, DadosEndereco endereco) {
}
