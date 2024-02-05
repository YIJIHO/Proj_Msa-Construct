package com.hh99.userservice.global.security;

import com.hh99.userservice.user.dto.UserDTO;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static io.jsonwebtoken.security.Keys.secretKeyFor;

@Component
public class JwtProvider implements InitializingBean {
    private final String secret;
    private final long tokenValidityInMilliseconds;
    private Key key;

    public JwtProvider(
            @Value("${jwt.token.secret-key}") String secret,
            @Value("${jwt.token.expire-time}") long tokenValidityInMilliseconds
    ){
        this.secret = secret;
        this.tokenValidityInMilliseconds = tokenValidityInMilliseconds;
    }

    @Override
    public void afterPropertiesSet() {
        this.key = secretKeyFor(SignatureAlgorithm.HS512);
    }

    public String createToken(UserDTO user){
        long now = (new Date()).getTime();
        Date validity = new Date(now + this.tokenValidityInMilliseconds);

        Map<String, Object> claims = new HashMap<>();
        claims.put("userEmail",user.getUserEmail());
        claims.put("userSeq",user.getUserSeq());
        claims.put("tokenValid",true);

        return Jwts.builder()
                .addClaims(claims)
                .signWith(key)
                .setExpiration(validity)
                .compact();
    }
    public String destroyToken(String token){
            if(getTokenInfo(token)){
                long now = (new Date()).getTime();
                Date validity = new Date(now);

                Map<String, Object> claims = new HashMap<>();

                return Jwts.builder()
                        .addClaims(claims)
                        .signWith(key)
                        .setExpiration(validity)
                        .compact();
            } else {
                return "유효하지않습니다.";
            }

    }
    public UserDTO getUserInfo(String token) {
        if(getTokenInfo(token)){
            token = token.replace("Bearer ","");
            Claims claims = Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(token)
                    .getBody();
            UserDTO user = new UserDTO();
            user.setUserEmail(claims.get("userEmail").toString());
            user.setUserSeq(Integer.parseInt(claims.get("userSeq").toString()));
            return user;
        } else {
            return null;
        }
    }
    public Boolean getTokenInfo(String token) {
        token = token.replace("Bearer ","");
        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();
        boolean valid = claims.get("tokenValid",Boolean.class);
        return valid;
    }
    public Authentication getAuthentication(String token){
        Claims claims = Jwts
                .parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        Collection<? extends GrantedAuthority> authorities =
                authorityOf(claims.get("userSeq", String.class));

        User principal = new User(claims.getSubject(), "", authorities);
        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }
    public List<GrantedAuthority> authorityOf(String userSeq) {
        if (userSeq == null) return Collections.emptyList();
        return Stream.of(new SimpleGrantedAuthority(userSeq)).collect(Collectors.toList());
    }
    public boolean validateToken(String token){
        try{
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        }
        catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            System.out.println("Invalid JWT Signature");
        }
        catch (ExpiredJwtException e) { System.out.println("JWT expired"); }
        catch (IllegalArgumentException e) { System.out.println("Illegal JWT");}
        return false;
    }
}