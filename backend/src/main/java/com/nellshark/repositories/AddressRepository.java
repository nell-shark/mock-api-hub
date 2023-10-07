package com.nellshark.repositories;

import com.nellshark.models.Address;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends CustomGenericRepository<Address, Long> {

}
