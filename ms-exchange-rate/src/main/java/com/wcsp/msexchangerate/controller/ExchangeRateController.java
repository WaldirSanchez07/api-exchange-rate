package com.wcsp.msexchangerate.controller;

import com.wcsp.msexchangerate.dto.ExchangeRateCreateReq;
import com.wcsp.msexchangerate.dto.ExchangeRateUpdateReq;
import com.wcsp.msexchangerate.dto.ResponseDto;
import com.wcsp.msexchangerate.model.ExchangeRate;
import com.wcsp.msexchangerate.service.ExchangeRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/exchange-rate")
public class ExchangeRateController {

  private final ExchangeRateService exchangeRateService;

  @PostMapping("/create")
  public Mono<ResponseEntity<ResponseDto>> create(@RequestBody ExchangeRateCreateReq request) {
    return exchangeRateService.create(request)
            .map(exchangeRate -> ResponseEntity.ok(ResponseDto.builder()
                    .data(exchangeRate)
                    .statusCode(HttpStatus.OK.value())
                    .build()))
            .onErrorResume(e -> Mono.just(ResponseEntity.badRequest().body(ResponseDto.builder()
                    .statusCode(400)
                    .message(e.getMessage())
                    .build())));
  }

  @PutMapping("/update")
  public Mono<ExchangeRate> update(@RequestBody ExchangeRateUpdateReq request) {
    return exchangeRateService.update(request);
  }

  @GetMapping("/find-by-currency")
  public Mono<Double> findByCurrency(
          @RequestParam(name = "origin") String originCurrency,
          @RequestParam(name = "fate") String fateCurrency) {
    return exchangeRateService.findByCurrency(originCurrency, fateCurrency);
  }

  @GetMapping("/list")
  public Flux<ExchangeRate> findAll() {
    return exchangeRateService.findAll();
  }

}
