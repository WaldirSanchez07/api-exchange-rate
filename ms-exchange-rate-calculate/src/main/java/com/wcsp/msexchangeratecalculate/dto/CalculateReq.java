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
public class CalculateReq {

  private Integer userId;
  private String originCurrency;
  private String fateCurrency;
  private Double originAmount;

}
