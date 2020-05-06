package com.setu.biller.repo;

import com.setu.biller.entity.CustomerParameters;
import com.setu.biller.entity.IdentifierPk;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerParametersRepository 
  extends CrudRepository<CustomerParameters, IdentifierPk> {

  @Query("SELECT cust FROM CustomerParameters cust "
      + "WHERE cust.attributeName = :attributename "
      + "AND cust.attributeValue = :attributeValue")
  Optional<CustomerParameters> findCustomerByPhone (
      @Param("attributename") String  attributename, 
      @Param("attributeValue") String attributeValue
      );
}
