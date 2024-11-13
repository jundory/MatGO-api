package com.support.matgo.store.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class SearchTypeRequest extends CoordinateRequest{
  private String memberType;
  private String foodType;
}
