package com.wcsp.msexchangeratelogs.mapper;

import com.wcsp.msexchangeratelogs.dto.ExchangeRateLogReq;
import com.wcsp.msexchangeratelogs.dto.UserLogResponse;
import com.wcsp.msexchangeratelogs.model.ExchangeRateLog;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;

@Mapper
public interface ExchangeRateLogMapper {

  ExchangeRateLogMapper INSTANCE = Mappers.getMapper(ExchangeRateLogMapper.class);

  default ExchangeRateLog map(ExchangeRateLogReq dto, UserLogResponse user) {
    return ExchangeRateLog.builder()
            .userId(user.getId())
            .userNames(user.getNames())
            .originCurrency(dto.getOriginCurrency())
            .fateCurrency(dto.getFateCurrency())
            .exchangeRate(dto.getExchangeRate())
            .originAmount(dto.getOriginAmount())
            .calculatedAmount(dto.getCalculatedAmount())
            .createdAt(LocalDateTime.now())
            .build();
  }

}
