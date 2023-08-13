package io.winty.alura.learningspring.domain.endereco;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {
    private String logradouro;
    private String bairro;
    private String cep;
    private String cidade;
    private String uf;
    private String complemento;
    private String numero;

    public Endereco(DadosEndereco endereco) {
        logradouro = endereco.logradouro();
        bairro = endereco.bairro();
        cep = endereco.cep();
        cidade = endereco.cidade();
        uf = endereco.uf();
        complemento = endereco.complemento();
        numero = endereco.numero();
    }

    public void atualizarInformacoes(DadosEndereco dados) {
        if (dados.logradouro() != null) {
            logradouro = dados.logradouro();
        }
        if (dados.bairro() != null) {
            bairro = dados.bairro();
        }
        if (dados.cep() != null) {
            cep = dados.cep();
        }
        if (dados.cidade() != null) {
            cidade = dados.cidade();
        }
        if (dados.uf() != null) {
            uf = dados.uf();
        }
        if (dados.complemento() != null) {
            complemento = dados.complemento();
        }
        if (dados.numero() != null) {
            numero = dados.numero();
        }
    }

}
