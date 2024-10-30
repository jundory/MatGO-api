package com.support.matgo.restaurantInfo.dto.response;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("simpleInfoDTO")
public class SimpleInfoResponse {
  private String imgUrl;
  private int storeId;
  private String storeNm;
  private String address;
  private String starRate;
  private String reviewCnt;
}
