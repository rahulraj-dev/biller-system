package com.setu.biller.service;

import com.setu.biller.dto.BillRequest;
import com.setu.biller.dto.ResponseDto;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

public interface BillPaymentService {

    ResponseDto fetchCustomerBills(BillRequest billRequest);

    ResponseDto fetchBillReceipt(JSONObject jsonObject) throws JSONException;
}
