package com.support.matgo.store.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
public class CoordinateRequest {
  @NotNull
  private BigDecimal latitude;
  @NotNull
  private BigDecimal longitude;
}
