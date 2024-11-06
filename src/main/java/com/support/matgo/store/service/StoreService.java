package com.support.matgo.store.service;

import com.support.matgo.store.dto.request.CoordinateRequest;
import com.support.matgo.store.dto.response.DetailInfoResponse;
import com.support.matgo.store.dto.response.ListApiResponse;
import com.support.matgo.store.dto.response.SimpleInfoResponse;
import com.support.matgo.store.dto.request.SearchTypeRequest;
import com.support.matgo.store.entity.DetailStore;
import com.support.matgo.store.mapper.StoreMapper;
import com.support.matgo.store.repository.DetailStoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RequiredArgsConstructor
@Service
public class StoreService {
  private final StoreMapper storeMapper;
  private final DetailStoreRepository detailStoreRepository;
  final int RADIUS = 5000; //검색 거리 범위 제한 (m)

  //메인 피드 리스트
  public ResponseEntity<ListApiResponse> mainFeedList(CoordinateRequest param){
    try {
      // 거리 검색 api
      List<SimpleInfoResponse> mainFeedList = storeMapper.randomStoreList(param, RADIUS);
      ListApiResponse result = ListApiResponse.builder()
          .status(HttpStatus.OK.value())
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
  public ResponseEntity<ListApiResponse> findFeedList(SearchTypeRequest param){
    try {
      List<SimpleInfoResponse> findStoreList = storeMapper.findStoreList(param, RADIUS);
      ListApiResponse result = ListApiResponse.builder()
          .status(HttpStatus.OK.value())
          .message("success")
          .listData(findStoreList)
          .build();
      return ResponseEntity.ok(result);
    }
    catch(Exception e){
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  public ResponseEntity<?> getStoreInfo(String storeId){
    // 검색리스트 비즈니스 로직
    // 1. redis data check

    // 2. RDB 정보 조회

    // 3. mongoDB에서 타입에 따른 메타 데이터 조회
    List<DetailStore> findStoreInfo = detailStoreRepository.findDetailInfoByStoreId(storeId);
    List<String> imgList = findStoreInfo.stream() //람다와 병렬처리 방식을 통해 리스트, 컬렉션을 함수형으로 쉽게 처리
        .map(DetailStore::getImgUrl)  //메서드 참조 람다식 "(x) -> DetailStore.getImgUrl(x)" 와 동일
        .toList();

    DetailInfoResponse result = DetailInfoResponse.builder()
        .imgList(imgList)
        .build();
    // 4. redis에 저장

    // 5. return
    return ResponseEntity.ok(result);
  }
}

