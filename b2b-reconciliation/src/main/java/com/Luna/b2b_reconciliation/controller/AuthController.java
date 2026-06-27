package com.Luna.b2b_reconciliation.controller;

import com.Luna.b2b_reconciliation.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {

        if ("admin".equals(username) && "admin".equals(password)) {
            return jwtUtil.generateToken(username);
        }
        return "Error: Credenciales incorrectas";
    }
}