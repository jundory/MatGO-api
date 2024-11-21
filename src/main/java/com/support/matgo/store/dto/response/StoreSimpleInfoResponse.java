package com.support.matgo.store.dto.response;

import lombok.Getter;

@Getter
public class StoreSimpleInfoResponse {
  private String imgUrl;
  private int storeId;
  private String storeNm;
  private String address;
  private String starRate;
  private String reviewCnt;
}
