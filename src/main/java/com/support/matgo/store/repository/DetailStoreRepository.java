package com.support.matgo.store.repository;

import com.support.matgo.store.entity.DetailStore;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailStoreRepository extends MongoRepository<DetailStore, String> {
  List<DetailStore> findDetailInfoByStoreId(String storeId);
}
