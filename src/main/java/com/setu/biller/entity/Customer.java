package com.setu.biller.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

/**
 * This is customer entity class, which will hold customer info
 * and parameter.
 */
@Entity
@Table(name="Customer")
public class Customer implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "customerId")
  private int customerId;

  @Column(name = "firstName")
  private String firstName;

  @Column(name = "lastName")
  private String lastName;

  @OneToMany(cascade= CascadeType.ALL)
  private List<CustomerParameters> customerParameters;

  public int getCustomerId() {
    return customerId;
  }

  public void setCustomerId(int customerId) {
    this.customerId = customerId;
  }

  public List<CustomerParameters> getCustomerParameters() {
    return customerParameters;
  }

  public void setCustomerParameters(List<CustomerParameters> customerParameters) {
    this.customerParameters = customerParameters;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
}
