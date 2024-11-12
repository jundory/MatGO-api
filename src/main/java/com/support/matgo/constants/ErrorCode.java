package com.support.matgo.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {

  //400 BAD_REQUEST 잘못된 요청
  INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "파라미터 값을 확인해주세요."),
  //404 NOT_FOUND 잘못된 리소스 접근
  STORE_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 가게 ID 입니다."),
  //409 CONFLICT 중복된 리소스
  //ALREADY_SELECT_TYPE(409, "이미 선택한 타입입니다."),
  //500 INTERNAL SERVER ERROR
  INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 에러입니다.");

  private final HttpStatus status;
  private final String message;
}
