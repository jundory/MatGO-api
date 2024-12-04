package com.support.matgo.store.entity;


import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "store_information")
@Getter
public class StoreInfo {
  @Field("store_id")
  private String storeId;
  @Field("store_nm")
  private String storeNm;
  @Field("address")
  private String address;
  @Field("tel_no")
  private String telNo;
  @Field("review_cnt")
  private int reviewCnt;
  @Field("star_rate")
  private String starRate;
  @Field("open_info")
  private Object openInfo;
    @Field("location")
  private Object location;
  @Field("category")
  private List<String> category;
  @Field("img_url")
  private String imgUrl;
  @Field("naver_url")
  private String naverUrl;
  @Field("simple_review")
  private String simpleReview;
  @Field("famous_cnt")
  private int famousCnt;
}
