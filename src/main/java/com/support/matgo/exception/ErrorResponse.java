package com.support.matgo.exception;

import lombok.Builder;

@Builder
public class ErrorResponse {
  private int status;
  private String message;
}
