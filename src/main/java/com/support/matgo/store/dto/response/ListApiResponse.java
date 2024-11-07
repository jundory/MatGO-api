package com.support.matgo.store.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class ListApiResponse {
  private int status;
  private String message;
  private List<SimpleInfoResponse> result;
}
