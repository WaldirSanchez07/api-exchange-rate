package com.wcsp.msauthentication.service;

import com.wcsp.msauthentication.dto.request.UserRequest;
import com.wcsp.msauthentication.model.User;
import com.wcsp.msauthentication.repository.UserRepository;
import com.wcsp.msauthentication.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtProvider jwtProvider;

  public Mono<User> save(UserRequest request) {
    return userRepository.findByUsername(request.getUsername())
            .flatMap(existingUser -> Mono.error(new RuntimeException("Usuario ya existe")))
            .switchIfEmpty(Mono.defer(() -> {
              String password = passwordEncoder.encode(request.getPassword());
              User usuarioSave = User.builder()
                      .username(request.getUsername())
                      .password(password)
                      .rol(request.getRol())
                      .build();
              return userRepository.save(usuarioSave);
            })).cast(User.class);
  }

  public Mono<User> findUserByToken(String token) {
    return jwtProvider.getProperty(token, "id")
            .flatMap(id -> userRepository.findById(Integer.parseInt(id)));
  }

  public Flux<User> findAll() {
    return userRepository.findAll();
  }

}
