package com.wcsp.msexchangeratecalculate.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeRateLogReq {

  private Integer userId;
  private String originCurrency;
  private String fateCurrency;
  private Double exchangeRate;
  private Double originAmount;
  private Double calculatedAmount;

}
