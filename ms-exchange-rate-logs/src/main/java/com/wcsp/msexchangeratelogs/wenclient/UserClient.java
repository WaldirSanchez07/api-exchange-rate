package com.wcsp.msexchangeratelogs.wenclient;

import com.wcsp.msexchangeratelogs.dto.UserLogResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

@Service
public class UserClient {

  @Value("${rest.clients.default.url}")
  private String baseUrl;

  @Value("${rest.clients.auth.services.find-user-by-token}")
  private String userFindByTokenUrl;

  public Mono<UserLogResponse> getUserByToken(String token) {
    UriComponentsBuilder uriBuilder = UriComponentsBuilder
            .fromUriString(baseUrl + userFindByTokenUrl)
            .queryParam("token", token.substring(7));

    return WebClient.create()
            .get()
            .uri(uriBuilder.build().toUriString())
            .retrieve()
            .bodyToMono(UserLogResponse.class);
  }

}
