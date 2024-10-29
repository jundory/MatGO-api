package com.support.matgo.restaurantInfo.service;

import com.support.matgo.restaurantInfo.dto.StoreInfoDTO;
import com.support.matgo.restaurantInfo.dto.searchTypeDTO;
import com.support.matgo.restaurantInfo.mapper.StoreMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StoreService {
  private final StoreMapper storeMapper;
  public ResponseEntity<?> findRestaurantList(searchTypeDTO param){
    // 필요 비즈니스 로직
    // 1. redis data check

    // 2. RDB 정보 조회
    List<StoreInfoDTO> findStoreList = storeMapper.findStoreList(param.getStoreName());

    // 3.

    return ResponseEntity.ok(findStoreList);
  }
}

