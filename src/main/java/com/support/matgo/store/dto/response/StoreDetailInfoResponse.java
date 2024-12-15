package com.support.matgo.store.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;
@Builder
@Getter
public class StoreDetailInfoResponse {
  private StoreInfoResponse storeInfo;
  private List<String> imgList;
}
