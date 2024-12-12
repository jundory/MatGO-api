package com.support.matgo.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {
  //204 No Content 콘텐츠 없음 --> ex) 상세 화면 가게는 있지만, 상세 정보(콘텐츠)는 없음.
  DETAIL_NO_CONTENT(HttpStatus.NO_CONTENT, "해당 가게에 등록된 상세 정보가 없습니다."),
  //400 BAD_REQUEST 잘못된 요청
  INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "파라미터 값을 확인해주세요."), // ENUM 상수에 값 지정(생성자 필수)
  //404 NOT_FOUND 잘못된 리소스 접근
  STORE_NOT_FOUND(HttpStatus.NOT_FOUND, "주위에 조회된 가게가 없습니다."),
  //409 CONFLICT 중복된 리소스
  //ALREADY_SELECT_TYPE(409, "이미 선택한 타입입니다."),
  //500 INTERNAL SERVER ERROR
  INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 에러입니다.");

  // 상수 값 지정을 위한 필수 생성자
  private final HttpStatus status;
  private final String message;
}
