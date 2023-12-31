package com.wcsp.apigateway.config;

import com.wcsp.apigateway.dto.TokenResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

@Component
public class AuthorizationFilter extends AbstractGatewayFilterFactory<AuthorizationFilter.Config> {

  @Value("${auth.uri}")
  private String authUrl;

  private WebClient.Builder webClient;

  public AuthorizationFilter(WebClient.Builder webClient) {
    super(Config.class);
    this.webClient = webClient;
  }

  @Override
  public GatewayFilter apply(Config config) {
    return (((exchange, chain) -> {
      if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION))
        return onError(exchange, HttpStatus.BAD_REQUEST);

      String tokenHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
      String[] chunks = tokenHeader.split(" ");

      System.out.println(exchange.getRequest().getPath().toString());
      System.out.println(exchange.getRequest().getMethod().toString());

      if (chunks.length != 2 || !chunks[0].equals("Bearer")) return onError(exchange, HttpStatus.BAD_REQUEST);

      UriComponentsBuilder uriBuilder = UriComponentsBuilder
              .fromUriString(authUrl + "/validate-access")
              .queryParam("token", chunks[1])
              .queryParam("uri", exchange.getRequest().getPath().toString());

      return webClient.build()
              .get()
              .uri(uriBuilder.build().toUriString())
              .retrieve()
              .bodyToMono(TokenResponse.class)
              .flatMap(t -> t.getToken().isEmpty()
                      ? onError(exchange, HttpStatus.UNAUTHORIZED)
                      : chain.filter(exchange));
    }));
  }

  public Mono<Void> onError(ServerWebExchange exchange, HttpStatus status) {
    ServerHttpResponse response = exchange.getResponse();
    response.setStatusCode(status);
    return response.setComplete();
  }

  public static class Config {
  }

}
