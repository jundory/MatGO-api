package com.support.matgo.store.repository;

//import com.mongodb.client.model.geojson.Point;
import com.mongodb.client.model.geojson.Position;
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
  @Override
  public List<StoreInfo> findStoreByCoords(CoordinatesRequest location, int radius){
    // BigDecimal을 double로 변환
    double longitude = location.getLongitude().doubleValue();
    double latitude = location.getLatitude().doubleValue();

    // GeoJSON Point 생성
    // Point point = new Point(new Position(longitude, latitude)); // MongoDB의 nearSphere()는 MongoDB -GeoJSON 포맷 요구
    Point point = new Point(longitude, latitude); // Spring Data의 Criteria는 다양한 지리적 타입을 지원

    Query query = new Query();
    query.addCriteria(Criteria.where("location")  //Criteria : MongoDB 쿼리를 구성하는 데 사용되는 조건을 정의
        .nearSphere(point)  //nearSphere : 지리적 쿼리에서 특정 위치를 기준으로 거리 계산을 하는 데 사용
        .maxDistance(radius / 6378137.0) // 반경을 미터 단위로 변환
    );
    return mongoTemplate.find(query, StoreInfo.class, "store_information");
  }
}
