package com.nellshark.services;

import com.nellshark.configs.InitDatabase;
import com.nellshark.models.Address;
import com.nellshark.repositories.AddressRepository;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

  public static Logger logger = Logger.getLogger(InitDatabase.class.getName());

  private final AddressRepository addressRepository;

  public AddressService(AddressRepository addressRepository) {
    this.addressRepository = addressRepository;
  }

  public void saveAddress(Address address) {
    logger.info("Save address: " + address);
    addressRepository.save(address);
  }
}
