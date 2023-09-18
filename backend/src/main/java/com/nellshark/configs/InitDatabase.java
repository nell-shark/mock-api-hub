package com.nellshark.configs;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nellshark.models.Address;
import com.nellshark.services.AddressService;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;

@Configuration
public class InitDatabase implements CommandLineRunner {

  public static Logger logger = Logger.getLogger(InitDatabase.class.getName());
  private final ResourceLoader resourceLoader;
  private final AddressService addressService;

  public InitDatabase(ResourceLoader resourceLoader, AddressService addressService) {
    this.resourceLoader = resourceLoader;
    this.addressService = addressService;
  }

  @Override
  public void run(String... args) throws IOException {
    logger.info("Starting initialization database");

    ObjectMapper mapper = new ObjectMapper();

    File file = resourceLoader.getResource("classpath:json/addresses.json").getFile();
    List<Address> addresses = mapper.readValue(file, new TypeReference<>() {
    });
    addresses.forEach(addressService::saveAddress);
  }
}
