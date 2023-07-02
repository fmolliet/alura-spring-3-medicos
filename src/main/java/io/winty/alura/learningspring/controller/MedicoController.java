package io.winty.alura.learningspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.winty.alura.learningspring.medico.DadosCadastroMedico;
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
}
