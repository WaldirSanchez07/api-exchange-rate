package com.wcsp.msauthentication.security;

import com.wcsp.msauthentication.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtProvider {

  @Value("${jwt.secret}")
  private String secret;

  @PostConstruct
  protected void init() {
    secret = Base64.getEncoder().encodeToString(secret.getBytes());
  }

  public String createToken(User user) {
    Map<String, Object> claims = new HashMap<>();
    claims = Jwts.claims().setSubject(user.getUsername());
    claims.put("id", user.getId());
    claims.put("rol", user.getRol());
    Date now = new Date();
    Date exp = new Date(now.getTime() + 3600000);
    return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(exp)
            .signWith(SignatureAlgorithm.HS512, secret)
            .compact();
  }

  public Mono<String> getProperty(String token, String property) {
    try {
      Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
      return Mono.just(claims.get(property).toString());
    } catch (Exception ex) {
      return Mono.error(new Throwable("Invalid token or expired!"));
    }
  }

}
