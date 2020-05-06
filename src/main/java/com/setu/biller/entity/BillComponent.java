package com.setu.biller.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

// NOT Needed. Just check and remove
@Entity
@Table(name = "BillComponent")
public class BillComponent implements Serializable {

  @Id
  @Column(name = "billComponentId")
  private int billComponentId;

  @ManyToOne
  @JoinColumn(name = "biller_BillID")
  private CustomerBill customerBill;

  private String billComponent;

  private double billComponentValue;

  public int getBillComponentId() {
    return billComponentId;
  }

  public void setBillComponentId(int billComponentId) {
    this.billComponentId = billComponentId;
  }

  public CustomerBill getCustomerBill() {
    return customerBill;
  }

  public void setCustomerBill(CustomerBill customerBill) {
    this.customerBill = customerBill;
  }

  public String getBillComponent() {
    return billComponent;
  }

  public void setBillComponent(String billComponent) {
    this.billComponent = billComponent;
  }

  public double getBillComponentValue() {
    return billComponentValue;
  }

  public void setBillComponentValue(double billComponentValue) {
    this.billComponentValue = billComponentValue;
  }
}
