package com.support.matgo.store.mapper;

import com.support.matgo.store.dto.request.CoordinateRequest;
import com.support.matgo.store.dto.request.SearchTypeRequest;
import com.support.matgo.store.dto.response.StoreSimpleInfoResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StoreMapper {
  List<StoreSimpleInfoResponse> mainFeedStoreList(@Param("coord") CoordinateRequest coordination, @Param("radius") int radius);
  List<StoreSimpleInfoResponse> findStoreList(@Param("filter") SearchTypeRequest param, @Param("radius") int radius);
  StoreSimpleInfoResponse findSimpleStoreInfo(int storeId);
}
