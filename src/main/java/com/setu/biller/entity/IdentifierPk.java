package com.setu.biller.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * This clas is for constructing composite primary key
 * for CustomerParameters table.
 *
 * The primary key is made of pk from
 * customer table and attributeName from CustomerParameters table.
 */
public class IdentifierPk implements Serializable {

  private int customer;
  private String attributeName;

  public IdentifierPk(int customerId, String attributeName) {
    this.customer = customerId;
    this.attributeName = attributeName;
  }

  public IdentifierPk() {
  }

  public int getCustomer() {
    return customer;
  }

  public void setCustomer(int customer) {
    this.customer = customer;
  }

  public String getAttributeName() {
    return attributeName;
  }

  public void setAttributeName(String attributeName) {
    this.attributeName = attributeName;
  }

  @Override
  public boolean equals(Object o) {
    if ( this == o ) {
      return true;
    }
    if ( o == null || getClass() != o.getClass() ) {
      return false;
    }
    IdentifierPk pk = (IdentifierPk) o;
    return Objects.equals( customer, pk.customer ) &&
        Objects.equals( attributeName, pk.customer );
  }

  @Override
  public int hashCode() {
    return Objects.hash( customer, attributeName );
  }
}
