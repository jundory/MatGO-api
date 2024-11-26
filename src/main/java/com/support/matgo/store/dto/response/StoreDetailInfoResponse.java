package com.support.matgo.store.dto.response;

import com.support.matgo.store.entity.StoreInfo;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class StoreDetailInfoResponse {
  private StoreInfo storeInfo;
  private List<String> imgList;
}
