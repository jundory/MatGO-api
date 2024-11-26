package com.support.matgo.store.service;

import com.support.matgo.exception.CustomException;
import com.support.matgo.constants.ErrorCode;
import com.support.matgo.store.dto.request.CoordinatesRequest;
import com.support.matgo.store.dto.response.DetailApiResponse;
import com.support.matgo.store.dto.response.StoreDetailInfoResponse;
import com.support.matgo.store.dto.response.ListApiResponse;
import com.support.matgo.store.dto.response.StoreInfoResponse;
import com.support.matgo.store.dto.request.SearchTypeRequest;
import com.support.matgo.store.entity.StoreInfo;
import com.support.matgo.store.entity.ImageInfo;
import com.support.matgo.store.mapper.StoreMapper;
import com.support.matgo.store.repository.ImageRepository;
import com.support.matgo.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class StoreService {
  private final StoreMapper storeMapper;
  private final ImageRepository imageRepository;
  private final StoreRepository storeRepository;
  final int RADIUS = 10000; //검색 거리 범위 제한 (m)

  // 메인 피드 리스트
  public ListApiResponse mainFeedList(CoordinatesRequest location) {
      // 거리 검색 api
//      List<StoreInfoResponse> storeList = storeMapper.mainStoreList(location, RADIUS);
//      if(storeList == null || storeList.isEmpty()){
//        throw new CustomException(ErrorCode.STORE_NOT_FOUND);
//      }
      List<StoreInfo> storeList = storeRepository.findStoreByCoords(location, RADIUS);
      ListApiResponse result = ListApiResponse.builder()
          .status(HttpStatus.OK.value())
          .message(HttpStatus.OK.getReasonPhrase())
          .result(storeList)
          .build();
      return result;
  }

  // 검색 피드 리스트
  public ListApiResponse findFeedList(SearchTypeRequest param){
      List<StoreInfoResponse> storeList = storeMapper.findStoreList(param, RADIUS);
      if(storeList == null){
        throw new CustomException(ErrorCode.STORE_NOT_FOUND);
      }
      ListApiResponse result = ListApiResponse.builder()
          .status(HttpStatus.OK.value())
          .message(HttpStatus.OK.getReasonPhrase())
//          .result(storeList)
          .build();
      return result;
  }

  // 가게 상세 정보
  public DetailApiResponse getStoreInfo(String storeId){
      // 검색리스트 비즈니스 로직
      // 1. redis에 storeId 조회

      // 2. img 메타 데이터 조회 & imgUrl만 별도 리스트로 생성
      List<ImageInfo> findImgInfo = imageRepository.findByStoreId(storeId);
      List<String> imgList = findImgInfo.stream() //람다와 병렬처리 방식을 통해 리스트, 컬렉션을 함수형으로 쉽게 처리
          .map(ImageInfo::getImgUrl)  //메서드 참조 람다식 "(x) -> DetailStore.getImgUrl(x)" 와 동일
          .toList();
      // 3. 가게 상세 정보 조회
      StoreInfo findDetailInfo = storeRepository.findByStoreId(storeId); //Optional rapper로 감싸 nullEx 방지
      StoreDetailInfoResponse storeData = StoreDetailInfoResponse.builder()
          .storeInfo(findDetailInfo)
          .imgList(imgList)
          .build();
      // 4. redis에 저장

      // 5. return
      DetailApiResponse result = DetailApiResponse.builder()
          .status(HttpStatus.OK.value())
          .message(HttpStatus.OK.getReasonPhrase())
          .result(storeData)
          .build();
      return result;
  }
}

