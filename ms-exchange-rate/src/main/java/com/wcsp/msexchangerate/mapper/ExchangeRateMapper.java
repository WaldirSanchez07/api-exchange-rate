package com.wcsp.msexchangerate.mapper;

import com.wcsp.msexchangerate.dto.ExchangeRateCreateReq;
import com.wcsp.msexchangerate.model.ExchangeRate;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;

@Mapper
public interface ExchangeRateMapper {

  ExchangeRateMapper INSTANCE = Mappers.getMapper(ExchangeRateMapper.class);

  default ExchangeRate mapCreate(ExchangeRateCreateReq request) {
    return ExchangeRate.builder()
            .originCurrency(request.getOriginCurrency())
            .fateCurrency(request.getFateCurrency())
            .exchangeRate(request.getExchangeRate())
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
            .build();
  }

}
