package com.support.matgo.exception;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ErrorResponse {
  private int status;
  private String message;
}
