package io.winty.alura.learningspring.medico;

import io.winty.alura.learningspring.endereco.Endereco;

public record DadosDetalhamentoMedico(Long id, String nome, String email, String crm, Especialidade especialidade, Endereco endereco){
    public DadosDetalhamentoMedico( Medico medico ){
        this(medico.getId(),medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade(), medico.getEndereco() );
    }
}
