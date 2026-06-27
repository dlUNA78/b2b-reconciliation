package com.Luna.b2b_reconciliation.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // 1. Deshabilitamos CSRF (Crucial para APIs REST)
                .csrf(csrf -> csrf.disable())
                // 2. Definimos reglas de acceso
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/login").permitAll() // Autorización explícita a la ruta login
                        .anyRequest().authenticated()
                )
                // 3. Stateless es OBLIGATORIO para JWT
                .sessionManagement(session -> session.sessionCreationPolicy(org.springframework.security.config.http.SessionCreationPolicy.STATELESS))
                // 4. Agregamos el filtro JWT antes de la autenticación de usuario/pass por defecto
                .addFilterBefore(new JwtFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}