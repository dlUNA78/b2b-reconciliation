package com.Luna.b2b_reconciliation.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.ArrayList;

public class JwtFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        System.out.println(" Filtrando petición a: " + request.getRequestURI());
        String authHeader = request.getHeader("Authorization");

        if (request.getRequestURI().startsWith("/auth/")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Validación simple: Si el header existe y empieza con "Bearer ", el usuario está autenticado
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            UsernamePasswordAuthenticationToken auth =
                    new UsernamePasswordAuthenticationToken("user", null, new ArrayList<>());
            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        filterChain.doFilter(request, response);
    }
}