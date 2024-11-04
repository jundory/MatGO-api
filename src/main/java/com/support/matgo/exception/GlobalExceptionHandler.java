package com.support.matgo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice //  @ControllerAdvice + @ResponseBody
public class GlobalExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class) // 유효성 검사에서 발생. 주로 데이터 유효하지 않을 때
  public ResponseEntity<?> BaseException(MethodArgumentNotValidException e){
    String errorMsg = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
    ErrorResponse result = ErrorResponse.builder()
        .status(HttpStatus.BAD_REQUEST.value())
        .message(errorMsg)
        .build();
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
  }
}
