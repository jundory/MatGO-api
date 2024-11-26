package com.support.matgo.store.repository;

import com.support.matgo.store.entity.StoreInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @description
 * MongoRepository, JpaRepository 등 상속 받으면 스프링부트가 자동으로 repository 감지 후 빈 등록
 * 가독성과 명확한 계층 구조 표시를 위한 @Repository 어노테이션 설정
 */
@Repository
public interface StoreRepository extends MongoRepository<StoreInfo, String>, CustomRepository{
  StoreInfo findByStoreId(String storeId);
}
