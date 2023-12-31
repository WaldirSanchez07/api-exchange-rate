package com.wcsp.msexchangerate.repository;

import com.wcsp.msexchangerate.model.ExchangeRate;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface ExchangeRateRepository extends R2dbcRepository<ExchangeRate, Integer> {

  Mono<ExchangeRate> findByOriginCurrencyAndFateCurrency(String originCurrency, String fateCurrency);

}
