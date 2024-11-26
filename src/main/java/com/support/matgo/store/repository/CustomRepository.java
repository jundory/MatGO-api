package com.support.matgo.store.repository;

import com.support.matgo.store.dto.request.CoordinatesRequest;
import com.support.matgo.store.entity.StoreInfo;

import java.util.List;

public interface CustomRepository {
  // 단순 CRUD 아닌 기능이나 계산 필요한 로직은 해당 interface 상속 받아 구현
  List<StoreInfo> findStoreByCoords(CoordinatesRequest location, int radius);
}
