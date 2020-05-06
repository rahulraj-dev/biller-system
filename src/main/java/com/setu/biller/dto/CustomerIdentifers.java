package com.setu.biller.dto;

import java.util.List;

/**
 * A DTO Class for CustomerIdentifers.
 */
public class CustomerIdentifers {

  private List<BillRequest> customerIdentifiers;

  public List<BillRequest> getCustomerIdentifiers() {
    return customerIdentifiers;
  }

  public void setCustomerIdentifiers(List<BillRequest> customerIdentifiers) {
    this.customerIdentifiers = customerIdentifiers;
  }
}
