package com.nellshark.services;

import com.nellshark.models.Address;
import com.nellshark.repositories.AddressRepository;
import org.springframework.stereotype.Service;

@Service
public class AddressService extends AbstractGenericService<Address, Long> {

  public AddressService(AddressRepository addressRepository) {
    super(addressRepository);
  }
}
