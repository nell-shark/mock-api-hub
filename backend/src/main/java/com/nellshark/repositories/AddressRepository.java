package com.nellshark.repositories;

import com.nellshark.models.Address;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class AddressRepository extends AbstractGenericRepository<Address> {

  public AddressRepository() {
    super(List.of(new Address(1L, "12A", "New York", "USA", 12345)));
  }
}
