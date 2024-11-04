package com.support.matgo.common.mapper;

import com.support.matgo.common.dto.CodeTypeResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommonMapper {
  List<CodeTypeResponse> findCodeTypeList(String type);
}
