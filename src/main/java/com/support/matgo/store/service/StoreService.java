package com.support.matgo.store.service;

import com.support.matgo.store.dto.request.CoordinateRequest;
import com.support.matgo.store.dto.response.DetailApiResponse;
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
  final int RADIUS = 10000; //검색 거리 범위 제한 (m)

  //메인 피드 리스트
  public ResponseEntity<?> mainFeedList(CoordinateRequest param){
    try {
      // 거리 검색 api
      List<SimpleInfoResponse> storeList = storeMapper.mainFeedStoreList(param, RADIUS);
      ListApiResponse result = ListApiResponse.builder()
          .status(HttpStatus.OK.value())
          .message(HttpStatus.OK.getReasonPhrase())
          .result(storeList)
          .build();
      return ResponseEntity.ok(result);
    }
    catch (Exception e){
      ListApiResponse result = ListApiResponse.builder()
          .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
          .message(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
          .build();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
    }
  }

  // 검색 피드 리스트
  public ResponseEntity<ListApiResponse> findFeedList(SearchTypeRequest param){
    try {
      List<SimpleInfoResponse> storeList = storeMapper.findStoreList(param, RADIUS);
      ListApiResponse result = ListApiResponse.builder()
          .status(HttpStatus.OK.value())
          .message(HttpStatus.OK.getReasonPhrase())
          .result(storeList)
          .build();
//      return ResponseEntity.ok(result);
      return ResponseEntity.ok(result);
    }
    catch(Exception e){
        ListApiResponse result = ListApiResponse.builder()
            .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
            .message(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
            .build();
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
    }
  }

  public ResponseEntity<?> getStoreInfo(String id){
    try {
      // 검색리스트 비즈니스 로직
      // 1. redis에 storeId 조회

      // 2. mongoDB img 메타 데이터 조회
      int storeId = Integer.parseInt(id); //추후 python에서 int로 수정 필요
      List<DetailStore> findStoreInfo = detailStoreRepository.findDetailInfoByStoreId(id);
      List<String> imgList = findStoreInfo.stream() //람다와 병렬처리 방식을 통해 리스트, 컬렉션을 함수형으로 쉽게 처리
          .map(DetailStore::getImgUrl)  //메서드 참조 람다식 "(x) -> DetailStore.getImgUrl(x)" 와 동일
          .toList();
      // 3. RDB 간단 정보 조회
      SimpleInfoResponse simpleStoreData = storeMapper.findSimpleStoreInfo(storeId);
      DetailInfoResponse responseData = DetailInfoResponse.builder()
          .simpleInfo(simpleStoreData)
          .imgList(imgList)
          .build();
      // 4. redis에 저장

      DetailApiResponse result = DetailApiResponse.builder()
          .status(HttpStatus.OK.value())
          .message(HttpStatus.OK.getReasonPhrase())
          .detailData(responseData)
          .build();
      // 5. return
      return ResponseEntity.ok(result);
    }
    catch (Exception e){
      DetailApiResponse result = DetailApiResponse.builder()
          .status(HttpStatus.OK.value())
          .message(HttpStatus.OK.getReasonPhrase())
          .build();
      return ResponseEntity.ok(result);
    }
  }
}

