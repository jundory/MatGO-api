package com.support.matgo.store.dto.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CoordinateRequest {
  private BigDecimal latitude;
  private BigDecimal longitude;
}
