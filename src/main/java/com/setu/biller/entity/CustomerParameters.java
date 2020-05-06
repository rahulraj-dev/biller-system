package com.setu.biller.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * This is CustomerParameters entity class with hold customer's attributes.
 */
@Entity
@Table(name = "CustomerParameters")
@IdClass(IdentifierPk.class)
public class CustomerParameters implements Serializable {

  @Id
  @ManyToOne
  @JoinColumn (name = "customerId")
  private Customer customer;

  @Id
  @Column(name = "attributeName", length = 20)
  private String attributeName;

  @Column(name = "attributeValue", length = 10)
  private String attributeValue;

  @Column(name = "displayName", length = 15)
  private String displayName;

  @Column(name = "isMandatory")
  private boolean isMandatory;

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public String getAttributeName() {
    return attributeName;
  }

  public void setAttributeName(String attributeName) {
    this.attributeName = attributeName;
  }

  public String getAttributeValue() {
    return attributeValue;
  }

  public void setAttributeValue(String attributeValue) {
    this.attributeValue = attributeValue;
  }

  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  public boolean isMandatory() {
    return isMandatory;
  }

  public void setMandatory(boolean mandatory) {
    isMandatory = mandatory;
  }
}
