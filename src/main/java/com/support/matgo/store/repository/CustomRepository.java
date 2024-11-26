package com.support.matgo.store.repository;

import com.support.matgo.store.dto.request.CoordinatesRequest;
import com.support.matgo.store.entity.StoreInfo;

import java.util.List;

/**
 * @description 단순 CRUD 아닌 기능이나 계산 등 커스텀이 필요한 쿼리의 경우 해당 인터페이스 상속 받아 구현
 */
public interface CustomRepository {
  List<StoreInfo> findStoreByCoords(CoordinatesRequest location, int radius);
}
