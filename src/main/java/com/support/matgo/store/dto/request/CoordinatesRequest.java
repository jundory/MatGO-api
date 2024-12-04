package com.support.matgo.store.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class CoordinatesRequest {
  /**
   * 소수점 오차 범위
   * 컴퓨터는 부동소수점 방식때문에 15자리부터 오차 발생
   * 위/경도에서 5자리까지 약 1m, 6자리는 약 10cm, 7자리는 약 1cm의 차이
   */
  @NotNull
  private double latitude;
  @NotNull
  private double longitude;

  private String sortType;
}
