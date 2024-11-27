package com.support.matgo.store.repository;

import com.support.matgo.store.dto.request.CoordinatesRequest;
import com.support.matgo.store.dto.request.SearchTypeRequest;
import com.support.matgo.store.entity.StoreInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class CustomRepositoryImpl implements CustomRepository {

  private final MongoTemplate mongoTemplate;

  /* 지리적 인덱스를 기반으로 사용자 중심 거리 계산 메소드 */
  @Override
  public List<StoreInfo> findStoreByCoords(CoordinatesRequest location, int radius) {
    // GeoJSON Point 생성
    Point point = new Point(location.getLongitude(), location.getLatitude());

    // 쿼리 생성
    Query query = new Query();
    query.addCriteria(Criteria.where("location")  //Criteria : MongoDB 쿼리를 구성하는 데 사용되는 조건을 정의 (Where절)
        .nearSphere(point)  //nearSphere : 지리적 쿼리에서 특정 위치를 기준으로 거리 계산을 하는 데 사용
        .maxDistance(radius / 6378137.0) // 반경을 미터 단위로 변환
    );
    return mongoTemplate.find(query, StoreInfo.class, "store_information");
  }

  /* 사용자의 위치 & 타입 조건 메소드 */
  public List<StoreInfo> findStoreByCategory(SearchTypeRequest type, int radius) {
    /**
     * @description 조회 쿼리 조건
     * 조건1) member / time 모두 null. --> 전체 조회
     * 조건2) member / time 중 하나만 null.  --> null 타입만 전체 조회
     * 조건3) member / time 모두 null은 아니면서 하나만 불일치.  --> 조회X
     * 조건4) member / time 모두 불일치.  --> 조회X
     */

    Point point = new Point(type.getLongitude(), type.getLatitude());
    Criteria criteria = new Criteria();
    Query query = new Query();

    // 사용자 위치 기반 거리 조건 추가
    criteria.and("location")
        .nearSphere(point)
        .maxDistance(radius / 6378137.0);

    // 조건1) member / time 모두 null.
    if (type.getMember() == null && type.getFood() == null) {
      query.addCriteria(criteria); // 위치 기준으로 전체 조회
      return mongoTemplate.find(query, StoreInfo.class, "store_information");
    }

    // 조건2) member / time 중 하나만 null.
    if (type.getMember() == null || type.getFood() == null) {
      if (type.getMember() == null) {
        criteria.and("category").in(type.getFood()); // food == null
      } else {
        criteria.and("category").in(type.getMember()); // member == null
      }
    }

    // 조건3) member / time 모두 null X.
    if (type.getMember() != null && type.getFood() != null && !type.getMember().isEmpty() && !type.getFood().isEmpty()) {
      /**
       * where() : 단일 조건 첫 정의, 조건의 시작점
       * and() : 추가 조건 결합, 양 조건 모두 충족
       * 아래의 경우 동일한 "category" 필드 내 조건이므로 andOperator() 사용  *** or()의 경우 orOperator()
       */
      criteria.andOperator(
          Criteria.where("category").in(type.getMember()),
          Criteria.where("category").in(type.getFood())
      );
    }
    // 최종 쿼리에 조건 추가
    query.addCriteria(criteria);
    List<StoreInfo> result = mongoTemplate.find(query, StoreInfo.class, "store_information");

    return result;
  }
}