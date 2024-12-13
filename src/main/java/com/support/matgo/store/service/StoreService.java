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
    try {
      String sortType = location.getSortType();
      // List<StoreInfoResponse> storeList = storeMapper.mainStoreList(location, RADIUS); <--MyBatis
      List<StoreInfo> storeEntityList = storeRepository.findStoreByCoords(location, RADIUS);

      // Sorting & Entity to DTO
      List<StoreInfoResponse> storeList = sortStoreList(storeEntityList, sortType);

      ListApiResponse result = ListApiResponse.builder()
          .status(HttpStatus.OK.value())
          .message(HttpStatus.OK.getReasonPhrase())
          .result(storeList)
          .build();
      return result;
    } catch (CustomException e){
      throw e;
    } catch (Exception e){
      throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
    }
  }

  /* 검색 화면 조회 메소드 */
  public ListApiResponse findFeedList(SearchTypeRequest param){
    try {
      // List<StoreInfoResponse> storeList = storeMapper.findStoreList(param, RADIUS); <--MyBatis
      List<StoreInfo> storeEntityList = storeRepository.findStoreByCategory(param, RADIUS);
      String sortType = param.getSortType();
      // entity to DTO
      List<StoreInfoResponse> storeList = sortStoreList(storeEntityList, sortType);

      ListApiResponse result = ListApiResponse.builder()
          .status(HttpStatus.OK.value())
          .message(HttpStatus.OK.getReasonPhrase())
          .result(storeList)
          .build();
      return result;
    } catch (CustomException e){
      throw e;
    } catch (Exception e){
      throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
    }
  }

  /* 가게 상세 정보 조회 메소드 */
  public DetailApiResponse getStoreInfo(String storeId){
    try {
      // 1. img 메타 데이터 조회 & imgUrl만 별도 리스트로 생성
      List<ImageInfo> imageInfoList = imageRepository.findByStoreId(storeId);

      // entity to DTO
      List<String> imgList = imageInfoList.stream() //람다와 병렬처리 방식을 통해 리스트, 컬렉션을 함수형으로 쉽게 처리
          .map(ImageInfo::getImgUrl)  //메서드 참조 람다식 "(x) -> DetailStore.getImgUrl(x)"
          .toList();

      // 2. 가게 상세 정보 조회
      StoreInfo storeDetailInfo = storeRepository.findByStoreId(storeId);
      // null 체크
      if (storeDetailInfo == null) {
        throw new CustomException(ErrorCode.DETAIL_NO_CONTENT);
      }
      /**
       * - 상세정보가 없는 예외처리가 필요할까? ←리스트에서 클릭해서 들어왔는데, 상세정보가 없다고 나온다면 이건 분명한 오류로 봐야 함.(이미지가 없을 순 있어도 최소한의 정보는 필요함 ← 프로젝트의 존재의의)
       * - 즉, 리스트에 표출되는 가게는 무조건 상세정보가 있어야 함 → 크롤링 시점에서 두 컬렉션에 같은 storeId 키를 가진 가게가 존재해야 함
       * - 리스트와 상세 페이지 모두 storId를 키로 가지고 있을꺼고, 클릭해서 상세 화면으로 이동 시 잘못된 storeId가 전달될 일도 없음. (이미 리스트에 표출된 가게를 클릭해서 들어올 것이기 때문)
       * - 이미지는 없을 시 자동으로 null 처리되어 예외처리 필요x
       * 결론) 상세 화면의 storeDetailInfo에 대한 예외처리는 필요 없을 것으로 보임. (이미지는 없으면 없는 대로, 상세는 없으면 안되기 때문에)
       */

      StoreDetailInfoResponse storeData = StoreDetailInfoResponse.builder()
          .storeInfo(new StoreInfoResponse(storeDetailInfo))  //entity to dto
          .imgList(imgList)
          .build();

      DetailApiResponse result = DetailApiResponse.builder()
          .status(HttpStatus.OK.value())
          .message(HttpStatus.OK.getReasonPhrase())
          .result(storeData)
          .build();
      return result;
    } catch (CustomException e){
      throw e;
    } catch (Exception e){
      throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
    }
  }

  /* Sorting & Entity to DTO */
  public List<StoreInfoResponse> sortStoreList(List<StoreInfo> entityList, String type){
    // null 체크
    if(entityList == null || entityList.isEmpty()){
      throw new CustomException(ErrorCode.STORE_NOT_FOUND);
    } else {
      // 정렬 기준 정의
      Comparator<StoreInfoResponse> comparator = type.equals("review")
          ? Comparator.comparingInt(StoreInfoResponse::getReviewCnt).reversed()  //reversed = 내림차순
          : Comparator.comparingInt(StoreInfoResponse::getFamousCnt).reversed();

    return entityList.stream()
        .map(store -> new StoreInfoResponse(store))
        .sorted(comparator)
        .toList();
    }
  }
}
