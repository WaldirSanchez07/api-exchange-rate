package com.wcsp.msexchangeratecalculate.service;

import com.wcsp.msexchangeratecalculate.webclient.ExchangeRateClient;
import com.wcsp.msexchangeratecalculate.webclient.ExchangeRateLogsClient;
import com.wcsp.msexchangeratecalculate.dto.CalculateReq;
import com.wcsp.msexchangeratecalculate.mapper.ExchangeRateLogMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ExchangeRateService {

  private final ExchangeRateClient exchangeRateClient;
  private final ExchangeRateLogsClient exchangeRateLogsClient;

  public Mono<Double> calculateExchangeRate(CalculateReq request, String token) {
    return exchangeRateClient
            .getExchangeRate(request.getOriginCurrency(), request.getFateCurrency(), token)
            .flatMap(exchangeRate -> {
              var amount = calculateAmount(request.getOriginAmount(), exchangeRate);
              return exchangeRateLogsClient
                      .saveRequest(ExchangeRateLogMapper.INSTANCE.map(request, exchangeRate, amount), token)
                      .thenReturn(amount);
            }).switchIfEmpty(Mono.just(0.0));
  }

  private Double calculateAmount(Double originAmount, Double exchangeRate) {
    return Math.round(originAmount * exchangeRate * 100.0) / 100.0;
  }

}
