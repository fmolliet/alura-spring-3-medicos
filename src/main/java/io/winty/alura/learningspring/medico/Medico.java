package io.winty.alura.learningspring.medico;

import io.winty.alura.learningspring.endereco.Endereco;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name="medicos")
@Entity(name="Medico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Medico {
    public Medico(DadosCadastroMedico dados) {
        ativo = true;
        nome = dados.nome();
        email = dados.email();
        crm = dados.crm();
        telefone = dados.telefone();
        especialidade = dados.especialidade();
        endereco = new Endereco(dados.endereco());
    }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;
    
    @Embedded
    private Endereco endereco;
    
    private Boolean ativo;

    public void atualizarInformacoes(DadosAtualizacaoMedico dados) {
        if ( dados.nome() != null){
            this.nome = dados.nome();
        }
        
        if ( dados.telefone() != null){
            this.telefone = dados.telefone();
        }
        
        if ( dados.endereco() != null){
            this.endereco.atualizarInformacoes(dados.endereco());
        }
    }

    public void desativar() {
        ativo = false;
    }
}
