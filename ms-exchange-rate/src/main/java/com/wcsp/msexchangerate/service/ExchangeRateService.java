package com.wcsp.msexchangerate.service;

import com.wcsp.msexchangerate.dto.ExchangeRateCreateReq;
import com.wcsp.msexchangerate.dto.ExchangeRateUpdateReq;
import com.wcsp.msexchangerate.mapper.ExchangeRateMapper;
import com.wcsp.msexchangerate.model.ExchangeRate;
import com.wcsp.msexchangerate.repository.ExchangeRateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ExchangeRateService {

  private final ExchangeRateRepository exchangeRateRepository;

  public Mono<Object> create(ExchangeRateCreateReq request) {
    return exchangeRateRepository
            .findByOriginCurrencyAndFateCurrency(request.getOriginCurrency(), request.getFateCurrency())
            .flatMap(exist -> Mono.error(new Throwable("El tipo de cambio ya existe.")))
            .switchIfEmpty(exchangeRateRepository.save(ExchangeRateMapper.INSTANCE.mapCreate(request)));
  }

  public Mono<ExchangeRate> update(ExchangeRateUpdateReq request) {
    return exchangeRateRepository
            .findById(request.getId())
            .flatMap(obj -> {
              obj.setExchangeRate(request.getExchangeRate());
              return exchangeRateRepository.save(obj);
            });
  }

  public Mono<Double> findByCurrency(String originCurrency, String fateCurrency) {
    return exchangeRateRepository.findByOriginCurrencyAndFateCurrency(originCurrency, fateCurrency)
            .map(ExchangeRate::getExchangeRate)
            .switchIfEmpty(Mono.just(0.0));
  }

  public Flux<ExchangeRate> findAll() {
    return exchangeRateRepository.findAll();
  }

}
