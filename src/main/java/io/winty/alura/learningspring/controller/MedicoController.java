package io.winty.alura.learningspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import io.winty.alura.learningspring.domain.medico.DadosAtualizacaoMedico;
import io.winty.alura.learningspring.domain.medico.DadosCadastroMedico;
import io.winty.alura.learningspring.domain.medico.DadosDetalhamentoMedico;
import io.winty.alura.learningspring.domain.medico.DadosListagemMedico;
import io.winty.alura.learningspring.domain.medico.Medico;
import io.winty.alura.learningspring.domain.medico.MedicoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("medicos")
public class MedicoController {
    
    @Autowired
    private MedicoRepository repository;
    
    @PostMapping
    @Transactional  
    public ResponseEntity<DadosDetalhamentoMedico> cadastrar(@RequestBody @Valid DadosCadastroMedico dados, UriComponentsBuilder uriBuilder){
        Medico medico = new Medico(dados);
        repository.save(medico);
        
        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
        
        return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico));
    }
    
    @GetMapping("/{id}") 
    public ResponseEntity<DadosDetalhamentoMedico> detalhamento(@PathVariable("id") Long id){
        var medico = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }
    
    @GetMapping 
    public ResponseEntity<Page<DadosListagemMedico>> listar(@PageableDefault(size = 10, sort= {"nome"}) Pageable paginacao){
        return ResponseEntity.ok(repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new));
    }
    
    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoMedico> atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados){
        var medico = repository.getReferenceById(dados.id());
        
        medico.atualizarInformacoes(dados);
        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }
    
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> excluir(@PathVariable("id") Long id){
        // deleta de maneira lógica
        /**repository.deleteById(id);*/
        var medico = repository.getReferenceById(id);
        medico.desativar();
        return ResponseEntity.noContent().build();
    }
}
