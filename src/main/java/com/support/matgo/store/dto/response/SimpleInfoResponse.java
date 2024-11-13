package com.support.matgo.store.dto.response;

import lombok.Data;
import lombok.Getter;
import org.apache.ibatis.type.Alias;

@Getter
public class SimpleInfoResponse {
  private String imgUrl;
  private int storeId;
  private String storeNm;
  private String address;
  private String starRate;
  private String reviewCnt;
}
