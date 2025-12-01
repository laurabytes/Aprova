package com.Aprova.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private UserAuthenticationFilter userAuthenticationFilter;

    public static final String[] ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED = {

            "/swagger-ui.html",
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/api/usuarios/criar",
            "/api/usuarios/login"
    };

    public static final String[] ENDPOINTS_DE_DEMONSTRACAO = {
            "/api/seguranca/**"
    };

    public static final String[] ENDPOINTS_WITH_AUTHENTICATION_REQUIRED = {
            "/api/flashcards/**"
    };

    // ADMIN
    public static final String[] ENDPOINTS_ADMINISTRADOR = {
            "/api/usuarios/atualizar/*",
            "/api/usuarios/apagar/*",
            "/api/usuarios/*",
            "/api/usuarios/atualizar-status/*",
            "/api/usuarios/listar"
    };

    // USUÁRIO
    public static final String[] ENDPOINTS_USUARIO = {

            // SESSÕES
            "/api/sessoes-estudo/listar",
            "/api/sessoes-estudo/criar",
            "/api/sessoes-estudo/apagar/*",
            "/api/sessoes-estudo/atualizar/*",
            "/api/sessoes-estudo/*",

            // MATERIAS
            "/api/materias/criar",
            "/api/materias/listar",
            "/api/materias/*",
            "/api/materias/apagar/*",
            "/api/materias/atualizar/*",

            // METAS
            "/api/metas/listar",
            "/api/metas/criar",
            "/api/metas/*",
            "/api/metas/apagar/*",
            "/api/metas/atualizar/*",
            "/api/metas/atualizar-status/*",

            // FLASHCARDS
            "/api/flashcards/criar",
            "/api/flashcards/listar",
            "/api/flashcards/*",
            "/api/flashcards/apagar/*",
            "/api/flashcards/atualizar/*"
    };


    public SecurityConfiguration(UserAuthenticationFilter userAuthenticationFilter) {
        this.userAuthenticationFilter = userAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        // swagger liberado
                        .requestMatchers(ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED).permitAll()
                        // exemplos
                        .requestMatchers(ENDPOINTS_DE_DEMONSTRACAO).permitAll()
                        // pré-flight
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        // Admin
                        .requestMatchers(ENDPOINTS_ADMINISTRADOR).hasAuthority("ADMINISTRADOR")
                        // Usuário
                        .requestMatchers(ENDPOINTS_USUARIO).hasAuthority("USUARIO")
                        // Flashcards autenticados
                        .requestMatchers(ENDPOINTS_WITH_AUTHENTICATION_REQUIRED).authenticated()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(userAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
