package io.winty.alura.learningspring.infra.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.winty.alura.learningspring.domain.usuario.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class TokenSecurityFilter extends OncePerRequestFilter{

    @Autowired
    private TokenService service;
    
    @Autowired
    private UsuarioRepository repository;
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String tokenJwt = recuperarToken(request);   
        if (tokenJwt!=null){
            var subject = service.getSubject(tokenJwt);
            var usuario = repository.findByLogin(subject);
            
            var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
            
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
           
        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        
        if (authHeader != null){
            return authHeader.replace("Bearer ", "");
        }
        return null;
        
    }

    
}
