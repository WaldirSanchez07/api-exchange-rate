package com.wcsp.msexchangerate.dto;

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
public class ExchangeRateCreateReq {

  private String originCurrency;
  private String fateCurrency;
  private Double exchangeRate;

}
