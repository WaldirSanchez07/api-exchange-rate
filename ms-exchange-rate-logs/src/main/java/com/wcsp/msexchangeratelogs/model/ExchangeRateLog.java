package com.wcsp.msexchangeratelogs.model;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "exchange_rate_logs")
public class ExchangeRateLog {

  @Id
  private Integer id;
  private Integer userId;
  private String userNames;
  private String originCurrency;
  private String fateCurrency;
  private Double exchangeRate;
  private Double originAmount;
  private Double calculatedAmount;
  private LocalDateTime createdAt;

}
