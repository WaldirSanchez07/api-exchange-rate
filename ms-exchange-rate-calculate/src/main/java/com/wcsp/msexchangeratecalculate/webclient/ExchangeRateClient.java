package com.wcsp.msexchangeratecalculate.webclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

@Service
public class ExchangeRateClient {

  @Value("${rest.clients.default.url}")
  private String baseUrl;

  @Value("${rest.clients.exchange-rate.services.find-by-currency}")
  private String exchangeRateFindByCurrencyUrl;

  public Mono<Double> getExchangeRate(String originCurrency, String fateCurrency, String token) {

    UriComponentsBuilder uriBuilder = UriComponentsBuilder
            .fromUriString(baseUrl + exchangeRateFindByCurrencyUrl)
            .queryParam("origin", originCurrency)
            .queryParam("fate", fateCurrency);

    return WebClient.create()
            .get()
            .uri(uriBuilder.build().toUriString())
            .header("Authorization", token)
            .retrieve()
            .bodyToMono(Double.class);
  }

}
