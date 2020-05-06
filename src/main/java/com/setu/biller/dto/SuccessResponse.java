package com.setu.biller.dto;

public class SuccessResponse extends ResponseDto {

  private CustomerBillData data;

  public CustomerBillData getData() {
    return data;
  }

  public void setData(CustomerBillData data) {
    this.data = data;
  }
}
