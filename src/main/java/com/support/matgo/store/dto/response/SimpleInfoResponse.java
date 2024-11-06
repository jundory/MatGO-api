package com.support.matgo.store.dto.response;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
public class SimpleInfoResponse {
  private String imgUrl;
  private int storeId;
  private String storeNm;
  private String address;
  private String starRate;
  private String reviewCnt;
}
