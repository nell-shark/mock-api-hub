package com.nellshark.controllers;

import com.nellshark.models.Address;
import com.nellshark.services.AddressService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/addresses")
public class AddressController extends AbstractGenericController<Address, Long> {

  public AddressController(AddressService addressService) {
    super(addressService);
  }
}
