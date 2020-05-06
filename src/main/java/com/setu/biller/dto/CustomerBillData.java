package com.setu.biller.dto;

public class CustomerBillData {

  private CustomerName customer;
  private  BillDetails billDetails;

  public CustomerName getCustomer() {
    return customer;
  }

  public void setCustomer(CustomerName customer) {
    this.customer = customer;
  }

  public BillDetails getBillDetails() {
    return billDetails;
  }

  public void setBillDetails(BillDetails billDetails) {
    this.billDetails = billDetails;
  }
}
