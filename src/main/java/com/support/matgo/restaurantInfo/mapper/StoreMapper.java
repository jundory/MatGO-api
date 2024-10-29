package com.support.matgo.restaurantInfo.mapper;

import com.support.matgo.restaurantInfo.dto.StoreInfoDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StoreMapper {
  List<StoreInfoDTO> findStoreList(String storeName);
}
