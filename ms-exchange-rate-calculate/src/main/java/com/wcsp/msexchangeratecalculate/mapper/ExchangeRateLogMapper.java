package com.wcsp.msexchangeratecalculate.mapper;

import com.wcsp.msexchangeratecalculate.dto.CalculateReq;
import com.wcsp.msexchangeratecalculate.dto.ExchangeRateLogReq;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ExchangeRateLogMapper {

  ExchangeRateLogMapper INSTANCE = Mappers.getMapper(ExchangeRateLogMapper.class);

  default ExchangeRateLogReq map(CalculateReq dto, Double exchangeRate, Double calculatedAmount) {
    return ExchangeRateLogReq.builder()
            .userId(dto.getUserId())
            .originCurrency(dto.getOriginCurrency())
            .fateCurrency(dto.getFateCurrency())
            .originAmount(dto.getOriginAmount())
            .exchangeRate(exchangeRate)
            .calculatedAmount(calculatedAmount)
            .build();
  }

}
