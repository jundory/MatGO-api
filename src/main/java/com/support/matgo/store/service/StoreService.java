package com.support.matgo.store.service;

import com.support.matgo.exception.CustomException;
import com.support.matgo.constants.ErrorCode;
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

  // 메인 피드 리스트
  public ListApiResponse mainFeedList(CoordinateRequest param) {
      // 거리 검색 api
      List<SimpleInfoResponse> storeList = storeMapper.mainFeedStoreList(param, RADIUS);
//      if(storeList == null || storeList.isEmpty()){
//        throw new CustomException(ErrorCode.STORE_NOT_FOUND);
//      }
      ListApiResponse result = ListApiResponse.builder()
          .status(HttpStatus.OK.value())
          .message(HttpStatus.OK.getReasonPhrase())
          .result(storeList)
          .build();
      return result;
  }

  // 검색 피드 리스트
  public ListApiResponse findFeedList(SearchTypeRequest param){
      List<SimpleInfoResponse> storeList = storeMapper.findStoreList(param, RADIUS);
      if(storeList == null){
        throw new CustomException(ErrorCode.STORE_NOT_FOUND);
      }
      ListApiResponse result = ListApiResponse.builder()
          .status(HttpStatus.OK.value())
          .message(HttpStatus.OK.getReasonPhrase())
          .result(storeList)
          .build();
      return result;
  }

  // 가게 상세 정보
  public DetailApiResponse getStoreInfo(String id){
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
      return result;
  }
}

