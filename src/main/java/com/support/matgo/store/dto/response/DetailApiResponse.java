package com.support.matgo.store.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DetailApiResponse {
  private int status;
  private String message;
  private StoreDetailInfoResponse result;
}
