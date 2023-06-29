package io.winty.alura.learningspring.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.winty.alura.learningspring.medico.DadosCadastroMedico;

@RestController
@RequestMapping("medicos")
public class MedicoController {
    
    @PostMapping
    public void cadastrar(@RequestBody DadosCadastroMedico json){
        System.out.println(json);
    }
}
