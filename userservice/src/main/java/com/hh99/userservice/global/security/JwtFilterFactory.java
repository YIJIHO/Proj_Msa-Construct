package com.hh99.userservice.global.security;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtFilterFactory {
    private final JwtProvider jwtProvider;
    public JwtFilter testAuthorization() {
        return new JwtFilter(jwtProvider);
    }
}