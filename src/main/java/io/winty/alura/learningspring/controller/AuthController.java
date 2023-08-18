package io.winty.alura.learningspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.winty.alura.learningspring.domain.usuario.DadosAuth;
import io.winty.alura.learningspring.domain.usuario.Usuario;
import io.winty.alura.learningspring.infra.security.DadosTokenJWT;
import io.winty.alura.learningspring.infra.security.TokenService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/login")
public class AuthController {
    @Autowired
    private AuthenticationManager manager;
    
    @Autowired
    private TokenService service;
    
    @PostMapping
    public ResponseEntity<DadosTokenJWT> login(@RequestBody @Valid DadosAuth dados){
        var authtoken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        var authentication = manager.authenticate(authtoken);
        var tokenjwt = service.gerarToken((Usuario)authentication.getPrincipal());
        
        return ResponseEntity.ok(new DadosTokenJWT(tokenjwt));
    }
}
