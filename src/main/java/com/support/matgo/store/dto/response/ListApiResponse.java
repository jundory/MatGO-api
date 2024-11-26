package com.support.matgo.store.dto.response;

import com.support.matgo.store.entity.StoreInfo;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class ListApiResponse {
  private int status;
  private String message;
  private List<StoreInfo> result;
}
