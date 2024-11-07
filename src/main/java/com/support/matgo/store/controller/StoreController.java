package com.support.matgo.store.controller;

import com.support.matgo.store.dto.request.CoordinateRequest;
import com.support.matgo.store.dto.request.SearchTypeRequest;
import com.support.matgo.store.dto.response.ListApiResponse;
import com.support.matgo.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/store")
public class StoreController {
  private final StoreService storeService;

  // 리스트 api 합칠 예정 type null로 받아와 분기 처리
  @PostMapping("/getFeedList")
  public ResponseEntity<?> mainFeedList(@RequestBody CoordinateRequest param){
    return storeService.mainFeedList(param);
  }

  @PostMapping("/getSearchList")
  public ResponseEntity<?> searchStoreList(@RequestBody SearchTypeRequest param) {
    return storeService.findFeedList(param);
  }

  @GetMapping("/getDetailInfo/{storeId}")  //POST으로?
  public ResponseEntity<?> detailStoreInfo(@PathVariable String storeId){
    return storeService.getStoreInfo(storeId);
  }
}
