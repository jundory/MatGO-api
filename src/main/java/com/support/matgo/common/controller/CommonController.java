package com.support.matgo.common.controller;

import com.support.matgo.common.dto.CodeTypeResponse;
import com.support.matgo.common.service.CommonService;
import com.support.matgo.constants.ErrorCode;
import com.support.matgo.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/comm")
public class CommonController {

  private final CommonService commonService;

  @PostMapping("/getCodeType")
  public ResponseEntity<?> commonCodeList(@RequestBody HashMap<String, String> type){
    String codeType = type.get("commCdType");
    if(codeType == null){
      throw new CustomException(ErrorCode.INVALID_PARAMETER);
    }
    List<CodeTypeResponse> result = commonService.findCodeTypeList(codeType);
    return ResponseEntity.ok(result);
  }

}
