package com.support.matgo.restaurantInfo.dto.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SearchTypeRequest {
  private BigDecimal latitude;
  private BigDecimal longitude;
  private String memberType;
  private String foodType;
}
