package com.support.matgo.store.repository;

import com.support.matgo.store.dto.request.CoordinateRequest;
import com.support.matgo.store.entity.ImageInfo;

import java.util.List;

public interface CustomRepository {
  // 단순 CRUD 아닌 기능이나 계산 필요한 로직은 해당 interface 상속 받아 구현
  List<ImageInfo> findStoreListByCoord(String storeId, CoordinateRequest coordination);
}
