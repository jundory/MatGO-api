package com.support.matgo.store.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class CoordinatesRequest {
  @NotNull
  private BigDecimal latitude;
  @NotNull
  private BigDecimal longitude;
}
