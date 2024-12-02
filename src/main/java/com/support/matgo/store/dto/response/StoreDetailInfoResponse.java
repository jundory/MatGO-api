package com.support.matgo.store.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class StoreDetailInfoResponse {
  private StoreInfoResponse storeInfo;
  private List<String> imgList;
}
