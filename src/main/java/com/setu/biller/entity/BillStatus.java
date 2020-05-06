package com.setu.biller.entity;

/**
 * An enum to represent bill status.
 */
public enum  BillStatus {
  AVAILABLE,
  BILL_OUTSTANDING,
  NO_OUTSTANDING, // All bills are paid.
  PAID;
}
