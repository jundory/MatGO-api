package com.support.matgo.store.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CoordinateRequest {
  @NotNull
  private BigDecimal latitude;
  @NotNull
  private BigDecimal longitude;
}
