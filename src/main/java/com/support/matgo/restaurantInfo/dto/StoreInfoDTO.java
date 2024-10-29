package com.support.matgo.restaurantInfo.dto;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("storeInfoDTO")
public class StoreInfoDTO {
  private int storeId;
  private String storeName;
  private String address;
  private String starRate;
  private String reviewCnt;
}
