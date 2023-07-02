package io.winty.alura.learningspring.endereco;

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


}
