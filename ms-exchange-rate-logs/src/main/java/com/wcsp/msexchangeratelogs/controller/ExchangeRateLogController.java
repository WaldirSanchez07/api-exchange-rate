package com.wcsp.msexchangeratelogs.controller;

import com.wcsp.msexchangeratelogs.dto.ExchangeRateLogReq;
import com.wcsp.msexchangeratelogs.model.ExchangeRateLog;
import com.wcsp.msexchangeratelogs.service.ExchangeRateLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/exchange-rate-logs")
public class ExchangeRateLogController {

  private final ExchangeRateLogService exchangeRateLogService;

  @PostMapping("/create")
  public Mono<Void> saveLog(
          @RequestBody ExchangeRateLogReq exchangeRateLogReq,
          @RequestHeader(HttpHeaders.AUTHORIZATION) String authorization
  ) {
    return exchangeRateLogService.saveLog(exchangeRateLogReq, authorization);
  }

  @GetMapping("/list")
  public Flux<ExchangeRateLog> findAll() {
    return exchangeRateLogService.findAll();
  }

}
