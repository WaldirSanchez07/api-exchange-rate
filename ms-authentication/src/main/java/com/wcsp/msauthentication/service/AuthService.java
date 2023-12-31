package com.wcsp.msauthentication.service;

import com.wcsp.msauthentication.dto.request.LoginRequest;
import com.wcsp.msauthentication.dto.response.TokenResponse;
import com.wcsp.msauthentication.repository.UserRepository;
import com.wcsp.msauthentication.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtProvider jwtProvider;
  private final PermissionService permissionService;

  public Mono<TokenResponse> login(LoginRequest request) {
    return userRepository.findByUsername(request.getUsername())
            .filter(user -> passwordEncoder.matches(request.getPassword(), user.getPassword()))
            .map(user -> new TokenResponse(jwtProvider.createToken(user)))
            .switchIfEmpty(Mono.empty());
  }

  public Mono<TokenResponse> validateAccess(String token, String uri) {
    return jwtProvider.getProperty(token, "rol")
            .filter(rol -> !rol.isEmpty())
            .flatMap(rol -> permissionService
                    .findByUriAndRol(uri, rol)
                    .map(permission -> new TokenResponse(token))
            ).switchIfEmpty(Mono.just(new TokenResponse("")))
            .onErrorResume(e -> Mono.just(new TokenResponse("")));
  }

}
