package com.nellshark.services;

import com.nellshark.repositories.AddressRepository;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

  private final AddressRepository addressRepository;

  public AddressService(AddressRepository addressRepository) {
    this.addressRepository = addressRepository;
  }
}
