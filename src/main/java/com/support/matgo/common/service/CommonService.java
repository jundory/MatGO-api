package com.support.matgo.common.service;

import com.support.matgo.common.dto.CodeTypeResponse;
import com.support.matgo.common.mapper.CommonMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CommonService {
  private final CommonMapper commonMapper;
  public List<CodeTypeResponse> findCodeTypeList(String type){
    List<CodeTypeResponse> codeList = commonMapper.findCodeTypeList(type);
    return codeList;
  }
}
