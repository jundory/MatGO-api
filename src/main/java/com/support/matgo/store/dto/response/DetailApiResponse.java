package com.support.matgo.store.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class DetailApiResponse {
  private int status;
  private String message;
  private Object detailData;
}
