package com.setu.biller.dto;

import com.fasterxml.jackson.annotation.JsonRawValue;
import org.apache.commons.lang3.StringEscapeUtils;

public class ReceiptResponse extends ResponseDto {

  public ReceiptResponse() {}

  @JsonRawValue
  private  String data;

  public String getData() {
    return data;
  }

  public void setData(String data) {
    this.data = StringEscapeUtils.unescapeJava(data);
  }
}
