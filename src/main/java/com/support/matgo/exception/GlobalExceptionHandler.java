package com.support.matgo.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.support.matgo.constants.ErrorCode.INVALID_PARAMETER;

@RestControllerAdvice //  @ControllerAdvice + @ResponseBody
public class GlobalExceptionHandler {

  // DTO Validation Argument Check
  @ExceptionHandler(MethodArgumentNotValidException.class) // 요청 본문 파라미터 누락
  private ResponseEntity<ErrorResponse> handleNoArgumentException(MethodArgumentNotValidException ex){
  // private으로 exception 직접 호출 방지
    ErrorResponse result = ErrorResponse.builder()
        .status(INVALID_PARAMETER.getStatus().value())
        .message(INVALID_PARAMETER.getMessage())
        .build();
    return ResponseEntity.ok(result);
  }

  // Custom Exception global handling
  @ExceptionHandler(CustomException.class)
  private ResponseEntity<ErrorResponse> handleCustomException(CustomException ex){
    ErrorResponse result = ErrorResponse.builder()
        .status(ex.getErrorCode().getStatus().value())
        .message(ex.getErrorCode().getMessage())
        .build();
    return ResponseEntity.ok(result);
  }
// 500 에러
//    ListApiResponse result = ListApiResponse.builder()
//        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
//        .message(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
//        .build();
}
