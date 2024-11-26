package com.support.matgo.store.repository;

import com.support.matgo.store.entity.StoreInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StoreRepository extends MongoRepository<StoreInfo, String> {
  StoreInfo findByStoreId(String storeId);
}
