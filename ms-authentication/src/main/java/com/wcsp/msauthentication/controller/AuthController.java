package com.wcsp.msauthentication.controller;

import com.wcsp.msauthentication.dto.request.LoginRequest;
import com.wcsp.msauthentication.dto.request.UserRequest;
import com.wcsp.msauthentication.dto.response.TokenResponse;
import com.wcsp.msauthentication.model.User;
import com.wcsp.msauthentication.service.AuthService;
import com.wcsp.msauthentication.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/auth")
public class AuthController {

  private final AuthService authService;
  private final UserService userService;

  @PostMapping("/user/create")
  public Mono<User> userCreate(@RequestBody UserRequest request) {
    return userService.save(request);
  }

  @GetMapping("/user/list")
  public Flux<User> UserList() {
    return userService.findAll();
  }

  @GetMapping("/user/find-by-token")
  public Mono<User> UserByToken(@RequestParam String token) {
    return userService.findUserByToken(token);
  }

  @PostMapping("/login")
  public Mono<TokenResponse> login(@RequestBody LoginRequest request) {
    return authService.login(request);
  }

  @GetMapping("/validate-access")
  public Mono<TokenResponse> validateAccess(@RequestParam String token, @RequestParam String uri) {
    return authService.validateAccess(token, uri);
  }

}
