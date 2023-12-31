package com.wcsp.msexchangeratelogs.service;

import com.wcsp.msexchangeratelogs.dto.ExchangeRateLogReq;
import com.wcsp.msexchangeratelogs.mapper.ExchangeRateLogMapper;
import com.wcsp.msexchangeratelogs.model.ExchangeRateLog;
import com.wcsp.msexchangeratelogs.repository.ExchangeRateLogRepository;
import com.wcsp.msexchangeratelogs.wenclient.UserClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ExchangeRateLogService {

  private final ExchangeRateLogRepository exchangeRateLogRepository;
  private final UserClient userClient;

  public Mono<Void> saveLog(ExchangeRateLogReq exchangeRateLogReq, String token) {
    return userClient.getUserByToken(token)
            .flatMap(user -> exchangeRateLogRepository
                    .save(ExchangeRateLogMapper.INSTANCE.map(exchangeRateLogReq, user))
                    .then());
  }

  public Flux<ExchangeRateLog> findAll() {
    return exchangeRateLogRepository.findAll();
  }

}
