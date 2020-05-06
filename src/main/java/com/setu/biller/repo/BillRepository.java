package com.setu.biller.repo;

import com.setu.biller.entity.Customer;
import com.setu.biller.entity.CustomerBill;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BillRepository extends CrudRepository<CustomerBill, Integer> {

  @Query("SELECT bill FROM CustomerBill bill WHERE bill.customer = :customer")
  List<CustomerBill> findByCustomerId(
      @Param("customer") Customer customer
      );
}
