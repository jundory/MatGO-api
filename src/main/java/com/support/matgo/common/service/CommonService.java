package com.support.matgo.common.service;

import com.support.matgo.common.dto.CodeTypeResponse;
import com.support.matgo.common.mapper.CommonMapper;
import com.support.matgo.constants.ErrorCode;
import com.support.matgo.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CommonService {
  private final CommonMapper commonMapper;
  public List<CodeTypeResponse> findCodeTypeList(String type){
    if (!"member".equals(type) && !"time".equals(type)) {
      throw new IllegalArgumentException();
    }
    try {
      List<CodeTypeResponse> codeList = commonMapper.findCodeTypeList(type);
      return codeList;
    } catch (Exception e) {
      throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
    }
  }
}
