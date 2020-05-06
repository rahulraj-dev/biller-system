package com.setu.biller.dto;

/**
 * A DTO object for Bill
 */
public class BillDto {

  private String billerBillID;
  private String generatedOn;
  private String recurrence;
  private String amountExactness;
  private CustomerAccount customerAccount;
  private Aggregates aggregates = new Aggregates();

  public String getBillerBillID() {
    return billerBillID;
  }

  public void setBillerBillID(String billerBillID) {
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

  public CustomerAccount getCustomerAccount() {
    return customerAccount;
  }

  public void setCustomerAccount(CustomerAccount customerAccount) {
    this.customerAccount = customerAccount;
  }

  public Aggregates getAggregates() {
    return aggregates;
  }

  public void setAggregates(Aggregates aggregates) {
    this.aggregates = aggregates;
  }

  public class Aggregates {

    private Total total = new Total();

    public Total getTotal() {
      return total;
    }

    public void setTotal(Total total) {
      this.total = total;
    }

    public class Total {
      String displayName;
      Amount amount = new Amount();

      public String getDisplayName() {
        return displayName;
      }

      public void setDisplayName(String displayName) {
        this.displayName = displayName;
      }

      public Amount getAmount() {
        return amount;
      }

      public void setAmount(Amount amount) {
        this.amount = amount;
      }

      public class Amount {
        double value;

        public double getValue() {
          return value;
        }

        public void setValue(double value) {
          this.value = value;
        }
      }
    }

  }
}