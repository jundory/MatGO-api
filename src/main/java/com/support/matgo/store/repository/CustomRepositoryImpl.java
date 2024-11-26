package com.support.matgo.store.repository;

import com.support.matgo.store.dto.request.CoordinateRequest;
import com.support.matgo.store.entity.ImageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class CustomRepositoryImpl implements CustomRepository {

  private final MongoTemplate mongoTemplate;
  @Override
  public List<ImageInfo> findStoreListByCoord(String storeId, CoordinateRequest coordination){
    //거리계산
    Query query = new Query();
//    query.addCriteria(Criteria.where("location")
//        .nearSphere(new Point(coordination.getLongitude(), coordination.getLatitude()))
//        .maxDistance(radius / 6378137)
//    )
//    return mongoTemplate.find(query, Store.class, "store_information");
    return null;
  }
}
