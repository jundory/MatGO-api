package com.support.matgo.common.controller;

import com.support.matgo.common.service.CommonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/comm")
public class CommonController {

  private final CommonService commonService;

  @PostMapping("/getCodeType")
  public ResponseEntity<?> commonCodeList(@RequestBody HashMap<String, String> type){
    String codeType = type.get("commCdType");
    if(codeType != "member" || codeType != "time"){
      // return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
      return ResponseEntity.badRequest().build();
    }
    return commonService.findCodeTypeList(codeType);
  }

}
