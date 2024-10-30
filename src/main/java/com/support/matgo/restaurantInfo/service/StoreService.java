package com.support.matgo.restaurantInfo.service;

import com.support.matgo.restaurantInfo.dto.request.CoordinateRequest;
import com.support.matgo.restaurantInfo.dto.response.ListApiResponse;
import com.support.matgo.restaurantInfo.dto.response.SimpleInfoResponse;
import com.support.matgo.restaurantInfo.dto.request.SearchTypeRequest;
import com.support.matgo.restaurantInfo.mapper.StoreMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RequiredArgsConstructor
@Service
public class StoreService {
  private final StoreMapper storeMapper;
  final int RADIUS = 5000; //검색 거리 범위 제한 (m)

  //메인 피드 리스트
  public ResponseEntity<ListApiResponse> mainFeedList(CoordinateRequest param){
    try {
      // 거리 검색 api
      List<SimpleInfoResponse> mainFeedList = storeMapper.randomStoreList(param, RADIUS);
      ListApiResponse result = ListApiResponse.builder()
          .message("success")
          .listData(mainFeedList)
          .build();
//      if(mainFeedList.size() == 0){
//        result.setMessage("");
//      }
      return ResponseEntity.ok(result);
    }
    catch (Exception e){
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  // 검색 피드 리스트
  public ResponseEntity<ListApiResponse> findRestaurantList(SearchTypeRequest param){
    try {
      List<SimpleInfoResponse> findStoreList = storeMapper.findStoreList(param, RADIUS);
      ListApiResponse result = ListApiResponse.builder()
          .message("success")
          .listData(findStoreList)
          .build();
      return ResponseEntity.ok(result);
    }
    catch(Exception e){
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

//  public ResponseEntity<List<SimpleInfoResponse>> detailRestaurantList(SearchTypeRequest param){
//    // 검색리스트 비즈니스 로직
//    // 1. redis data check
//
//    // 2. RDB 정보 조회
//    List<SimpleInfoResponse> findStoreList = storeMapper.findStoreList(param.getStoreId());
//
//    // 3. mongoDB에서 타입에 따른 메타 데이터 조회
//
//    // 4. redis에 저장
//
//    // 5. return
//    return ResponseEntity.ok(findStoreList);
//  }
}

