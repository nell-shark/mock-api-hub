package com.nellshark.controllers;

import com.nellshark.models.Address;
import com.nellshark.services.AddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/addresses")
public class AddressController {

  private final AddressService addressService;

  public AddressController(AddressService addressService) {
    this.addressService = addressService;
  }

  @GetMapping("/{id}")
  public ResponseEntity<Address> getAddressById(@PathVariable("id") Long id) {
    Address address = addressService.getAddressById(id);
    return ResponseEntity.ok(address);
  }
}
