package com.Aprova.demo.config;

import com.Aprova.demo.Entity.Usuario;
import com.Aprova.demo.Repository.UsuarioRepository;
import com.Aprova.demo.Service.JwtTokenService;
import com.Aprova.demo.Service.UserDetailsImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

@Component
public class UserAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Verifica se o endpoint requer autenticação (se for público, pula essa validação)
        if (checkIfEndpointIsNotPublic(request)) {
            String token = recoveryToken(request);

            // Só tenta validar se o token existir
            if (token != null) {
                try {
                    // Tenta ler o token. Se for "undefined" ou inválido, isso vai gerar erro
                    String subject = jwtTokenService.getSubjectFromToken(token);

                    if (subject != null) {
                        Usuario usuario = usuarioRepository.findByEmail(subject).orElse(null);

                        if (usuario != null) {
                            UserDetailsImpl userDetails = new UserDetailsImpl(usuario);
                            Authentication authentication =
                                    new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities());

                            SecurityContextHolder.getContext().setAuthentication(authentication);
                        }
                    }
                } catch (Exception e) {
                    // BLINDAGEM: Se o token for inválido (ex: "undefined"), cai aqui.
                    // Não fazemos nada (não autentica), mas também NÃO QUEBRA a requisição.
                    // O Spring Security vai barrar com 403 depois, pacificamente.
                    System.out.println("Aviso: Token inválido ignorado: " + e.getMessage());
                }
            }
        }
        // Continua o fluxo normal
        filterChain.doFilter(request, response);
    }

    private String recoveryToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null) {
            return authorizationHeader.replace("Bearer ", "");
        }
        return null;
    }

    private boolean checkIfEndpointIsNotPublic(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        return Arrays.stream(SecurityConfiguration.ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED).noneMatch(publicEndpoint ->
                requestURI.startsWith(publicEndpoint.replace("/**", ""))
        );
    }
}