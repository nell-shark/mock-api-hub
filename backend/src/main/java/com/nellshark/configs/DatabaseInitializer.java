package com.nellshark.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nellshark.exceptions.ResourceLoadException;
import com.nellshark.exceptions.ResourceNotFoundException;
import com.nellshark.models.Address;
import com.nellshark.models.Book;
import com.nellshark.services.AddressService;
import com.nellshark.services.BookService;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

@Configuration
public class DatabaseInitializer implements CommandLineRunner {

  private static final Logger logger = LoggerFactory.getLogger(DatabaseInitializer.class);

  private final ResourceLoader resourceLoader;
  private final ObjectMapper objectMapper;
  private final AddressService addressService;
  private final BookService bookService;

  public DatabaseInitializer(
      ResourceLoader resourceLoader,
      ObjectMapper objectMapper,
      AddressService addressService,
      BookService bookService) {
    this.resourceLoader = resourceLoader;
    this.objectMapper = objectMapper;
    this.addressService = addressService;
    this.bookService = bookService;
  }

  @Override
  public void run(String... args) {
    logger.info("Starting initialization database");

    processJsonResource(
        "classpath:json/addresses.json",
        Address.class,
        addressService::saveAddress
    );
    processJsonResource(
        "classpath:json/books.json",
        Book.class,
        bookService::saveBook
    );
  }

  private <T> void processJsonResource(
      String resourceFilePath,
      Class<T> clazz,
      Consumer<T> saveEntity) {
    File file = getResourceFile(resourceFilePath);
    List<T> list = convertJsonFileToGenericList(file, clazz);
    list.forEach(saveEntity);
  }

  private File getResourceFile(String resourceFilePath) {
    Resource resource = resourceLoader.getResource(resourceFilePath);

    try {
      return resource.getFile();
    } catch (IOException e) {
      throw new ResourceNotFoundException("Error getting resource file: " + resourceFilePath, e);
    }
  }

  private <T> List<T> convertJsonFileToGenericList(File jsonFile, Class<T> clazz) {
    try (FileInputStream fileInputStream = new FileInputStream(jsonFile)) {
      return objectMapper.readValue(
          fileInputStream,
          objectMapper.getTypeFactory().constructCollectionType(List.class, clazz)
      );
    } catch (IOException e) {
      throw new ResourceLoadException(
          "Error converting JSON file to list: " + jsonFile.getPath(), e);
    }
  }
}
