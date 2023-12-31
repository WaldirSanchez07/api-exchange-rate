package com.wcsp.msexchangeratecalculate.webclient;

import com.wcsp.msexchangeratecalculate.dto.ExchangeRateLogReq;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ExchangeRateLogsClient {

  @Value("${rest.clients.default.url}")
  private String baseUrl;

  @Value("${rest.clients.exchange-rate-logs.services.save-log}")
  private String exchangeRateLogsSaveLogUrl;

  public Mono<Void> saveRequest(ExchangeRateLogReq request, String token) {
    return WebClient.create()
            .post()
            .uri(baseUrl + exchangeRateLogsSaveLogUrl)
            .header("Authorization", token)
            .bodyValue(request)
            .retrieve()
            .bodyToMono(Void.class);
  }

}
