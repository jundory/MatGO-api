package com.support.matgo.store.repository;

import com.support.matgo.store.dto.request.CoordinatesRequest;
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

  /* 지리적 인덱스를 기반으로 한 거리 계산 메소드 */
  @Override
  public List<StoreInfo> findStoreByCoords(CoordinatesRequest location, int radius){
    // BigDecimal을 double로 변환
    double longitude = location.getLongitude().doubleValue();
    double latitude = location.getLatitude().doubleValue();

    // GeoJSON Point 생성
    Point point = new Point(longitude, latitude);

    // 쿼리 생성
    Query query = new Query();
    query.addCriteria(Criteria.where("location")  //Criteria : MongoDB 쿼리를 구성하는 데 사용되는 조건을 정의
        .nearSphere(point)  //nearSphere : 지리적 쿼리에서 특정 위치를 기준으로 거리 계산을 하는 데 사용
        .maxDistance(radius / 6378137.0) // 반경을 미터 단위로 변환
    );
    return mongoTemplate.find(query, StoreInfo.class, "store_information");
  }
}
