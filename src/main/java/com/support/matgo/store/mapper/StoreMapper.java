package com.support.matgo.store.mapper;

import com.support.matgo.store.dto.request.CoordinatesRequest;
import com.support.matgo.store.dto.request.SearchTypeRequest;
import com.support.matgo.store.dto.response.StoreInfoResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StoreMapper {
//  List<StoreInfoResponse> mainStoreList(@Param("coord") CoordinatesRequest coordination, @Param("radius") int radius);
  List<StoreInfoResponse> findStoreList(@Param("filter") SearchTypeRequest param, @Param("radius") int radius);
//  StoreInfoResponse findSimpleStoreInfo(int storeId);
}
