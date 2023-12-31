package com.wcsp.msexchangerate.model;

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
@Table(name = "exchange_rate")
public class ExchangeRate {

  @Id
  private Integer id;
  private String originCurrency;
  private String fateCurrency;
  private Double exchangeRate;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

}
