package com.support.matgo.restaurantInfo.controller;

import com.support.matgo.restaurantInfo.dto.request.CoordinateRequest;
import com.support.matgo.restaurantInfo.dto.request.SearchTypeRequest;
import com.support.matgo.restaurantInfo.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class StoreController {
  private final StoreService storeService;

  // 리스트 api 합칠 예정 type null로 받아와 분기 처리
  @PostMapping("/getMainList")
  public ResponseEntity<?> mainFeedList(@RequestBody CoordinateRequest param){
    if(param.getLatitude() == null || param.getLongitude() == null) {
      return ResponseEntity.badRequest().body("Parameter does not exist");
    }
    return storeService.mainFeedList(param);
  }

  @PostMapping("/getSearchList")
  public ResponseEntity<?> searchStoreList(@RequestBody SearchTypeRequest param) {
    return storeService.findRestaurantList(param);
  }
}
