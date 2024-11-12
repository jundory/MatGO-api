package com.support.matgo.exception;

import com.support.matgo.constants.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class CustomException extends RuntimeException{
  private final ErrorCode errorCode;
//  private ErrorCode errorCode;
//   public CustomException(ErrorCode e){
//     this.errorCode = e;
//   }
}
