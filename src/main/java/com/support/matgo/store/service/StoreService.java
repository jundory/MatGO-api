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

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
@Service
public class StoreService {
  //private final StoreMapper storeMapper;
  private final ImageRepository imageRepository;
  private final StoreRepository storeRepository;
  final int RADIUS = 10000; //검색 거리 범위 제한 (m)

  /* 메인 화면 조회 메소드 */
  public ListApiResponse mainFeedList(CoordinatesRequest location) {

      String sortType = location.getSortType();
      // List<StoreInfoResponse> storeList = storeMapper.mainStoreList(location, RADIUS); <--MyBatis
      List<StoreInfo> storeEntityList = storeRepository.findStoreByCoords(location, RADIUS);

      // Sorting & Entity to DTO
      List<StoreInfoResponse> storeList = sortStoreList(storeEntityList, sortType);

      if(storeList == null || storeList.isEmpty()){
        throw new CustomException(ErrorCode.STORE_NOT_FOUND);
      }

      ListApiResponse result = ListApiResponse.builder()
          .status(HttpStatus.OK.value())
          .message(HttpStatus.OK.getReasonPhrase())
          .result(storeList)
          .build();
      return result;
  }

  /* 검색 화면 조회 메소드 */
  public ListApiResponse findFeedList(SearchTypeRequest param){
      // List<StoreInfoResponse> storeList = storeMapper.findStoreList(param, RADIUS); <--MyBatis
      List<StoreInfo> storeEntityList = storeRepository.findStoreByCategory(param, RADIUS);
      String sortType = param.getSortType();
      // entity to DTO
      List<StoreInfoResponse> storeList = sortStoreList(storeEntityList, sortType);

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

  /* 가게 상세 정보 조회 메소드 */
  public DetailApiResponse getStoreInfo(String storeId){
      // 1. redis에 storeId 조회

      // 2. img 메타 데이터 조회 & imgUrl만 별도 리스트로 생성
      List<ImageInfo> imageInfoList = imageRepository.findByStoreId(storeId);

      // entity to DTO
      List<String> imgList = imageInfoList.stream() //람다와 병렬처리 방식을 통해 리스트, 컬렉션을 함수형으로 쉽게 처리
          .map(ImageInfo::getImgUrl)  //메서드 참조 람다식 "(x) -> DetailStore.getImgUrl(x)"
          .toList();
      // 3. 가게 상세 정보 조회
      StoreInfo storeDetailInfo = storeRepository.findByStoreId(storeId);
      StoreDetailInfoResponse storeData = StoreDetailInfoResponse.builder()
          .storeInfo(new StoreInfoResponse(storeDetailInfo))  //entity to dto
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

  /* Sorting & Entity to DTO */
  public List<StoreInfoResponse> sortStoreList(List<StoreInfo> entityList, String type){
    Comparator<StoreInfoResponse> comparator;
    if(type.equals("review")){
      comparator = Comparator.comparingInt(StoreInfoResponse::getReviewCnt).reversed(); //내림차순
    } else {
      comparator = Comparator.comparingInt(StoreInfoResponse::getFamousCnt).reversed();
    }
    return entityList.stream()
        .map(store -> new StoreInfoResponse(store))
        .sorted(comparator)
        .toList();
  }
}

