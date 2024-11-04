package com.support.matgo.store.mapper;

import com.support.matgo.store.dto.request.CoordinateRequest;
import com.support.matgo.store.dto.request.SearchTypeRequest;
import com.support.matgo.store.dto.response.SimpleInfoResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StoreMapper {
  List<SimpleInfoResponse> randomStoreList(@Param("coord") CoordinateRequest coordination, @Param("radius") int radius);
  List<SimpleInfoResponse> findStoreList(@Param("filter") SearchTypeRequest param, @Param("radius") int radius);
//  List<SimpleInfoResponse> findStoreList(int storeId);
}
