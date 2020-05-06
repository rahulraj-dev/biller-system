package com.setu.biller.service;

import com.setu.biller.dto.BillDetails;
import com.setu.biller.dto.BillDto;
import com.setu.biller.dto.BillRequest;
import com.setu.biller.dto.CustomerAccount;
import com.setu.biller.dto.CustomerBillData;
import com.setu.biller.dto.CustomerName;
import com.setu.biller.dto.ErrorResponse;
import com.setu.biller.dto.ReceiptResponse;
import com.setu.biller.dto.ResponseDto;
import com.setu.biller.dto.SuccessResponse;
import com.setu.biller.entity.BillStatus;
import com.setu.biller.entity.Customer;
import com.setu.biller.entity.CustomerBill;
import com.setu.biller.entity.CustomerParameters;
import com.setu.biller.error.ErrorObject;
import com.setu.biller.repo.BillRepository;
import com.setu.biller.repo.CustomerParametersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementation of BillPaymentService interface.
 */
@Service
public class BillPaymentPaymentServiceImpl implements BillPaymentService {

  @Autowired
  private CustomerParametersRepository customerParamsRepo;

  @Autowired
  private BillRepository billRepo;

  @Override
  public ResponseDto fetchCustomerBills(BillRequest billRequest) {

    Optional<CustomerParameters> customerParameters = customerParamsRepo
        .findCustomerByPhone(billRequest.getAttributeName(), billRequest.getAttributeValue());
    /*
     *  If customerParam value does not exits for given attribute 
     *  then return error response. Customer not found.
     */
    if (!customerParameters.isPresent()) {
      return generateErrorResponse();
    }

    Customer customer = customerParameters.get().getCustomer();
    return generateSuccessResponse(customer);
  }

  /**
   * Bill payment can also be partial not always exact amount.
   * 
   * Partial Bill payment logic is not added currently, because this doc mentions only exact bill payment.
   * 
   * Ref - https://docs.setu.co/biller-quickstart
   * 
   * There can be various other use cases around this and those can be handled easily.
   */
  @Override
  public ResponseDto fetchBillReceipt(JSONObject jsonObject) throws JSONException {
    String billerBillID = jsonObject.getString("billerBillID");
    String platformBillID = jsonObject.getString("platformBillID");
    String platformTransactionRefID = jsonObject
        .getJSONObject("paymentDetails").getString("platformTransactionRefID");

    double amountPaid = jsonObject.getJSONObject("paymentDetails")
        .getJSONObject("amountPaid").getDouble("value");

    CustomerBill customerBill = billRepo.findById(Integer.parseInt(billerBillID)).get();
    if (customerBill != null) {
      if (customerBill.getAmountExactness().equalsIgnoreCase("EXACT") 
          && amountPaid!= customerBill.getBillAmount()) {

        // throw error. that amount is not paid in exact amount.
        ReceiptResponse receiptResponse = new ReceiptResponse();
        receiptResponse.setData("Bill amount paid is not exact.");
        receiptResponse.setStatus(String.valueOf(HttpStatus.NOT_ACCEPTABLE.value()));
        receiptResponse.setSuccess(String.valueOf(Boolean.FALSE));
        return receiptResponse;
      }
      customerBill.setBillStatus(BillStatus.PAID);
      billRepo.save(customerBill);
    } 

    JSONObject data = generateBillReceipt(billerBillID,platformBillID,platformTransactionRefID);
    ReceiptResponse receiptResponse = new ReceiptResponse();
    receiptResponse.setData(data.toString());
    receiptResponse.setStatus(String.valueOf(HttpStatus.OK.value()));
    receiptResponse.setSuccess(String.valueOf(Boolean.TRUE));
    return receiptResponse;
  }

  private SuccessResponse generateSuccessResponse(@NotNull Customer customer) {
    CustomerAccount customerAccount = new CustomerAccount();
    customerAccount.setId(customer.getCustomerId());
    List<CustomerBill> customerBills = billRepo.findByCustomerId(customer);

    List<CustomerBill> unPaidbills = customerBills.stream()
        .filter(customerBill -> 
        (!customerBill.getBillStatus().equals(BillStatus.PAID)))
        .collect(Collectors.toList());

    List<BillDto> billDtoList = new ArrayList<>();
    for(CustomerBill customerBill : unPaidbills) {
      BillDto billDto = new BillDto();
      billDto.setBillerBillID(String.valueOf(customerBill.getBillerBillID()));
      billDto.setAmountExactness(customerBill.getAmountExactness());
      billDto.setGeneratedOn(customerBill.getGeneratedOn());
      billDto.setRecurrence(customerBill.getRecurrence());
      billDto.setCustomerAccount(customerAccount);
      BillDto.Aggregates aggregates = billDto.getAggregates();
      BillDto.Aggregates.Total total = aggregates.getTotal();
      BillDto.Aggregates.Total.Amount amount = total.getAmount();
      amount.setValue(customerBill.getBillAmount());
      total.setAmount(amount);
      total.setDisplayName("Total Outstanding");
      aggregates.setTotal(total);
      billDto.setAggregates(aggregates);
      billDtoList.add(billDto);
    }

    CustomerName customerName = new CustomerName();
    String CustomerName = customer.getFirstName() + " " + customer.getLastName();
    customerName.setName(CustomerName);
    BillDetails billDetails = new BillDetails();
    if(CollectionUtils.isEmpty(unPaidbills)) {
      billDetails.setBillFetchStatus(BillStatus.NO_OUTSTANDING);
    } else {
      billDetails.setBillFetchStatus(BillStatus.AVAILABLE);
    }
    billDetails.setBills(billDtoList);
    CustomerBillData customerBillData = new CustomerBillData();
    customerBillData.setBillDetails(billDetails);
    customerBillData.setCustomer(customerName);
    SuccessResponse successResponse = new SuccessResponse();
    successResponse.setData(customerBillData);
    successResponse.setStatus(String.valueOf(HttpStatus.OK.value()));
    successResponse.setSuccess(String.valueOf(Boolean.TRUE));
    return successResponse;
  }

  private ErrorResponse generateErrorResponse() {
    ErrorResponse errorResponse = new ErrorResponse();
    ErrorObject errorObject = new ErrorObject();
    errorObject.setCode("CUSTOMER_NOT_FOUND");
    errorObject.setTitle("Customer not found");
    errorObject.setDescription("The requested customer was not found in the biller system.");
    errorResponse.setStatus(String.valueOf(HttpStatus.NOT_FOUND.value()));
    errorResponse.setSuccess(String.valueOf(Boolean.FALSE));
    errorResponse.setError(errorObject);
    return errorResponse;
  }

  private JSONObject generateBillReceipt(String billerBillID,
      String platformBillID, String platformTransactionRefID) throws JSONException {
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("id","R12289171221");
    jsonObject.put("date","2019-08-02T07:12:10Z");

    JSONObject data = new JSONObject();
    data.put("billerBillID",billerBillID);
    data.put("platformBillID",platformBillID);
    data.put("platformTransactionRefID",platformTransactionRefID);
    data.put("receipt", jsonObject);
    return data;
  }
}

