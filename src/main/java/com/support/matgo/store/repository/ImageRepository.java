package com.support.matgo.store.repository;

import com.support.matgo.store.entity.ImageInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends MongoRepository<ImageInfo, String>, CustomRepository { // MongoRepository에서 단순 CRUD 기능 제공
  List<ImageInfo> findByStoreId(String storeId);
}
