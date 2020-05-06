package com.setu.biller.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.setu.biller.dto.BillRequest;
import com.setu.biller.dto.CustomerIdentifers;
import com.setu.biller.dto.ResponseDto;
import com.setu.biller.service.BillPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest Controller class that contains API definitions.
 */
@RestController
@RequestMapping("/bills")
public class BillPaymentController {

  @Autowired
  private BillPaymentService billPaymentService;

  /**
   * Fetch customer bill API
   * Url to call this API is "http://<base-api-url>/bills/fetch"
   * @param customerIdentifers
   * @return
   */
  @PostMapping(value = "/fetch")
  public ResponseDto fetchCustomerBills(@RequestBody CustomerIdentifers customerIdentifers) {
    ResponseDto responseDto = null;
    if (customerIdentifers != null) {
      for (BillRequest request : customerIdentifers.getCustomerIdentifiers()) {
        responseDto = billPaymentService.fetchCustomerBills(request);
      }
    }
    return responseDto;
  }

  /**
   * Url to call this API is "http://<base-api-url>/bills/fetchReceipt"
   * @param fetchReceiptRequest
   * @return
   * @throws JSONException
   * @throws JsonProcessingException
   */
  @PostMapping(value = "/fetchReceipt")
  public ResponseDto fetchBillReceipt(@RequestBody String fetchReceiptRequest) throws JSONException, JsonProcessingException {

    ResponseDto responseDto = null;
    if (fetchReceiptRequest != null) {
      JSONObject jsonObject = new JSONObject(fetchReceiptRequest);
      responseDto = billPaymentService.fetchBillReceipt(jsonObject);
    }
    return responseDto;
  }
}
