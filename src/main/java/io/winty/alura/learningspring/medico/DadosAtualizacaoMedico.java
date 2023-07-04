package io.winty.alura.learningspring.medico;

import io.winty.alura.learningspring.endereco.DadosEndereco;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoMedico(
        @NotNull Long id, String nome, String telefone, DadosEndereco endereco) {
}
