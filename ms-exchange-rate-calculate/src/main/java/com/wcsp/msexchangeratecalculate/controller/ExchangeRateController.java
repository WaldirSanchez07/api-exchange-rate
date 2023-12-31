package com.wcsp.msexchangeratecalculate.controller;

import com.wcsp.msexchangeratecalculate.dto.CalculateReq;
import com.wcsp.msexchangeratecalculate.service.ExchangeRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/exchange-rate-calculate")
public class ExchangeRateController {

  private final ExchangeRateService exchangeRateService;

  @PostMapping
  public Mono<Double> calculateExchangeRate(
          @RequestBody CalculateReq request,
          @RequestHeader(HttpHeaders.AUTHORIZATION) String authorization
  ) {
    return exchangeRateService.calculateExchangeRate(request, authorization);
  }

}
