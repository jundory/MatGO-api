package com.support.matgo.store.dto.response;

import com.support.matgo.store.entity.StoreInfo;
import lombok.Getter;

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
  private String imgUrl;
  private String naverUrl;
  private String simpleReview;


  // Entity to DTO 위한 생성자
  public StoreInfoResponse(StoreInfo storeInfo){
    this.storeId = storeInfo.getStoreId();
    this.storeNm = storeInfo.getStoreNm();
    this.address = storeInfo.getAddress();
    this.telNo = storeInfo.getTelNo();
    this.reviewCnt = storeInfo.getReviewCnt();
    this.starRate = storeInfo.getStarRate();
    this.location = storeInfo.getLocation();
    this.openInfo = storeInfo.getOpenInfo();
    this.imgUrl = storeInfo.getImgUrl();
    this.naverUrl = storeInfo.getNaverUrl();
    this.simpleReview =storeInfo.getSimpleReview();
  }
}
