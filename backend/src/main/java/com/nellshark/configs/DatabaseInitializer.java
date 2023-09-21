package com.nellshark.configs;

import com.nellshark.models.Address;
import com.nellshark.models.Book;
import com.nellshark.models.Comment;
import com.nellshark.models.Company;
import com.nellshark.models.Course;
import com.nellshark.models.Employee;
import com.nellshark.models.Event;
import com.nellshark.repositories.AddressRepository;
import com.nellshark.repositories.BookRepository;
import com.nellshark.repositories.CommentRepository;
import com.nellshark.repositories.CompanyRepository;
import com.nellshark.repositories.CourseRepository;
import com.nellshark.repositories.EmployeeRepository;
import com.nellshark.repositories.EventRepository;
import com.nellshark.services.JsonService;
import java.io.File;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;

@Configuration
public class DatabaseInitializer implements CommandLineRunner {

  private static final Logger logger = LoggerFactory.getLogger(DatabaseInitializer.class);

  private final JsonService jsonService;
  private final AddressRepository addressRepository;
  private final BookRepository bookRepository;
  private final CommentRepository commentRepository;
  private final CompanyRepository companyRepository;
  private final CourseRepository courseRepository;
  private final EmployeeRepository employeeRepository;
  private final EventRepository eventRepository;

  public DatabaseInitializer(
      JsonService jsonService,
      AddressRepository addressRepository,
      BookRepository bookRepository,
      CommentRepository commentRepository,
      CompanyRepository companyRepository,
      CourseRepository courseRepository,
      EmployeeRepository employeeRepository,
      EventRepository eventRepository) {
    this.jsonService = jsonService;
    this.addressRepository = addressRepository;
    this.bookRepository = bookRepository;
    this.commentRepository = commentRepository;
    this.companyRepository = companyRepository;
    this.courseRepository = courseRepository;
    this.employeeRepository = employeeRepository;
    this.eventRepository = eventRepository;
  }

  @Override
  public void run(String... args) {
    logger.info("Starting initialization database");

    loadAndSaveJsonEntities(
        "addresses.json",
        Address.class,
        addressRepository
    );

    loadAndSaveJsonEntities(
        "books.json",
        Book.class,
        bookRepository
    );

    loadAndSaveJsonEntities(
        "comments.json",
        Comment.class,
        commentRepository
    );

    loadAndSaveJsonEntities(
        "companies.json",
        Company.class,
        companyRepository
    );

    loadAndSaveJsonEntities(
        "courses.json",
        Course.class,
        courseRepository
    );

    loadAndSaveJsonEntities(
        "employees.json",
        Employee.class,
        employeeRepository
    );

    loadAndSaveJsonEntities(
        "events.json",
        Event.class,
        eventRepository
    );
  }

  private <T> void loadAndSaveJsonEntities(
      String jsonFileName, Class<T> clazz, JpaRepository<T, ?> repository) {
    logger.info("Deserialize json file '{}' to entities : {}", jsonFileName, clazz);

    File jsonFile = jsonService.getJsonFileFromResources(jsonFileName);
    List<T> entities = jsonService.convertJsonFileToEntities(jsonFile, clazz);
    repository.saveAll(entities);
  }
}
