package com.setu.biller.dto;

import java.io.Serializable;

/**
 * A DTO class for API response object
 */
public class ResponseDto implements Serializable {

  private String status;
  private String success;

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getSuccess() {
    return success;
  }

  public void setSuccess(String success) {
    this.success = success;
  }

}
