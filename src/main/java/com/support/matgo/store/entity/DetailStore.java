package com.support.matgo.store.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "metadata")
@Getter
@NoArgsConstructor  // NoSQL에서 entity 객체 인스턴스화할 때 기본 생성자 필요하기 때문
public class DetailStore {
  @Id
  private String id;
  @Field("img_url")
  private String imgUrl;
  @Field("detail_addr")
  private String detailAddr;
  @Field("store_id")
  private int storeId;
  @Field("current_status")
  private String currentStatus;
  @Field("strt_time")
  private String strtTime;
  @Field("end_time")
  private String endTime;
  @Field("naver_url")
  private String naverUrl;
  @Field("img_thumbs_url")
  private String imgThumbsUrl;
}
