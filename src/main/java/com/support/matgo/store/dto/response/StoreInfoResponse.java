package com.support.matgo.store.dto.response;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
public class StoreInfoResponse {
  private String storeId;
  private String storeNm;
  private String address;
  private String telNo;
  private int reviewCnt;
  private String starRate;
  private Object location;
  private Object openInfo;
  private String category;
  private String imgUrl;
  private String naverUrl;
}
