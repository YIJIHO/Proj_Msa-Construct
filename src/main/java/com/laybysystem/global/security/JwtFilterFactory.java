package com.laybysystem.global.security;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtFilterFactory {
    private final JwtProvider jwtProvider;
    public JwtFilter testAuthorization() {
        System.out.println("여기는들어오나?");
        return new JwtFilter(jwtProvider);
    }
}