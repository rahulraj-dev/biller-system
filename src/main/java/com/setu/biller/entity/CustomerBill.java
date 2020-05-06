package com.setu.biller.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * This entity class will hold info related to customer bill.
 */
@Entity
@Table(name = "CustomerBill")
public class CustomerBill implements Serializable {

  @Id
  @Column(name = "biller_BillID")
  private int billerBillID;

  private String generatedOn;

  private String recurrence;

  private String amountExactness;

  @ManyToOne
  @JoinColumn(name = "customerId", insertable = false, updatable = false)
  private Customer customer;

  @Enumerated(EnumType.STRING)
  private BillStatus billStatus;

  private double billAmount;

  public int getBillerBillID() {
    return billerBillID;
  }

  public void setBillerBillID(int billerBillID) {
    this.billerBillID = billerBillID;
  }

  public String getGeneratedOn() {
    return generatedOn;
  }

  public void setGeneratedOn(String generatedOn) {
    this.generatedOn = generatedOn;
  }

  public String getRecurrence() {
    return recurrence;
  }

  public void setRecurrence(String recurrence) {
    this.recurrence = recurrence;
  }

  public String getAmountExactness() {
    return amountExactness;
  }

  public void setAmountExactness(String amountExactness) {
    this.amountExactness = amountExactness;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public BillStatus getBillStatus() {
    return billStatus;
  }

  public void setBillStatus(BillStatus billStatus) {
    this.billStatus = billStatus;
  }

  public double getBillAmount() {
    return billAmount;
  }

  public void setBillAmount(double billAmount) {
    this.billAmount = billAmount;
  }

}
