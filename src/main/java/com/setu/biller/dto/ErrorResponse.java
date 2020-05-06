package com.setu.biller.dto;

import com.setu.biller.error.ErrorObject;

public class ErrorResponse extends ResponseDto {

  private ErrorObject error;

  public ErrorObject getError() {
    return error;
  }

  public void setError(ErrorObject error) {
    this.error = error;
  }
}
