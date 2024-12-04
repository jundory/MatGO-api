package com.support.matgo.store.controller;

import com.support.matgo.store.dto.request.CoordinatesRequest;
import com.support.matgo.store.dto.request.SearchTypeRequest;
import com.support.matgo.store.dto.response.DetailApiResponse;
import com.support.matgo.store.dto.response.ListApiResponse;
import com.support.matgo.store.service.StoreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;


/**
 * 가게 관련 Controller
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/store")
public class StoreController {
  private final StoreService storeService;

  /**
   * 메인 화면 리스트
   * @param location 사용자의 현재 경도&위도, 나열 순서
   * @return result 사용자 주변 가게 리스트
   */
  @PostMapping("/getFeedList")
  public ResponseEntity<ListApiResponse> mainFeedList(@Valid @RequestBody CoordinatesRequest location){
      ListApiResponse result = storeService.mainFeedList(location);
    return ResponseEntity.ok(result);
  }

  /**
   * 검색 화면 리스트
   * @param type 사용자가 설정한 필터값, 현재 경도&위도
   * @return result 필터에 맞는 사용자 주변 가게 리스트
   */
  @PostMapping("/getSearchList")
  public ResponseEntity<ListApiResponse> searchStoreList(@Valid @RequestBody SearchTypeRequest type) {
    ListApiResponse result = storeService.findFeedList(type);
    return ResponseEntity.ok(result);
  }

  /**
   * 상세 화면 정보 및 이미지
   * @param storeId 가게 고유 ID
   * @return result 가게 상세 정보와 음식 이미지 리스트
   */
  @PostMapping("/getDetailInfo")
  public ResponseEntity<DetailApiResponse> detailStoreInfo(@RequestBody HashMap<String, String> storeId){
    DetailApiResponse result = storeService.getStoreInfo(storeId.get("storeId"));
    return ResponseEntity.ok(result);
  }
}
