package com.nellshark.configs;

import com.nellshark.models.Address;
import com.nellshark.models.Book;
import com.nellshark.models.Comment;
import com.nellshark.models.Company;
import com.nellshark.services.AddressService;
import com.nellshark.services.BookService;
import com.nellshark.services.CommentService;
import com.nellshark.services.CompanyService;
import com.nellshark.utils.JsonUtils;
import java.io.File;
import java.util.List;
import java.util.function.Consumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseInitializer implements CommandLineRunner {

  private static final Logger logger = LoggerFactory.getLogger(DatabaseInitializer.class);

  private final AddressService addressService;
  private final BookService bookService;
  private final CommentService commentService;
  private final CompanyService companyService;

  public DatabaseInitializer(
      AddressService addressService,
      BookService bookService,
      CommentService commentService, CompanyService companyService) {
    this.addressService = addressService;
    this.bookService = bookService;
    this.commentService = commentService;
    this.companyService = companyService;
  }

  @Override
  public void run(String... args) {
    logger.info("Starting initialization database");

    loadAndSaveJsonEntities(
        "addresses.json", Address.class, addressService::saveAddress);
    loadAndSaveJsonEntities(
        "books.json", Book.class, bookService::saveBook);
    loadAndSaveJsonEntities(
        "comments.json", Comment.class, commentService::saveComment);
    loadAndSaveJsonEntities(
        "companies.json", Company.class, companyService::saveCompany);
  }

  private <T> void loadAndSaveJsonEntities(
      String jsonFileName, Class<T> clazz, Consumer<T> saveEntity) {
    File jsonFile = JsonUtils.getJsonFileFromResources(jsonFileName);
    List<T> entities = JsonUtils.convertJsonFileToEntities(jsonFile, clazz);
    entities.forEach(saveEntity);
  }
}
