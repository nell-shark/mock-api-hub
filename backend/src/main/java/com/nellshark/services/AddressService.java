package com.nellshark.services;

import com.nellshark.repositories.AddressRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

  private static final Logger logger = LoggerFactory.getLogger(AddressService.class);

  private final AddressRepository addressRepository;

  public AddressService(AddressRepository addressRepository) {
    this.addressRepository = addressRepository;
  }
}
