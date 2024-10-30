package com.support.matgo.restaurantInfo.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class ListApiResponse {
  private String message;
  private List<SimpleInfoResponse> listData;
//  private Boolean status
}
