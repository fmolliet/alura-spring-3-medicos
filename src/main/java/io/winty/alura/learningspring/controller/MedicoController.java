package io.winty.alura.learningspring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.winty.alura.learningspring.medico.DadosAtualizacaoMedico;
import io.winty.alura.learningspring.medico.DadosCadastroMedico;
import io.winty.alura.learningspring.medico.DadosListagemMedico;
import io.winty.alura.learningspring.medico.Medico;
import io.winty.alura.learningspring.medico.MedicoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("medicos")
public class MedicoController {
    
    @Autowired
    private MedicoRepository repository;
    
    @PostMapping
    @Transactional  
    public void cadastrar(@RequestBody @Valid DadosCadastroMedico dados){
        repository.save(new Medico(dados));
    }
    
    @GetMapping 
    public Page<DadosListagemMedico> listar(@PageableDefault(size = 10, sort= {"nome"}) Pageable paginacao){
        return repository.findAll(paginacao).map(DadosListagemMedico::new);
    }
    
    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados){
        var medico = repository.getReferenceById(dados.id());
        
        medico.atualizarInformacoes(dados);
    }
}
