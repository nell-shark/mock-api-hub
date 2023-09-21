package com.nellshark.configs;

import com.nellshark.exceptions.RepositoryNotFoundException;
import com.nellshark.models.Address;
import com.nellshark.models.Book;
import com.nellshark.models.Comment;
import com.nellshark.models.Company;
import com.nellshark.models.Course;
import com.nellshark.models.Employee;
import com.nellshark.models.Event;
import com.nellshark.models.Message;
import com.nellshark.models.Notification;
import com.nellshark.services.JsonService;
import java.io.File;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.support.Repositories;
import org.springframework.web.context.WebApplicationContext;

@Configuration
public class DatabaseInitializer implements CommandLineRunner {

  private static final Logger logger = LoggerFactory.getLogger(DatabaseInitializer.class);

  private final JsonService jsonService;
  private final WebApplicationContext appContext;

  public DatabaseInitializer(JsonService jsonService, WebApplicationContext appContext) {
    this.jsonService = jsonService;
    this.appContext = appContext;
  }

  @Override
  public void run(String... args) {
    logger.info("Starting initialization database");

    Map<String, Class<?>> map = Map.of(
        "addresses.json", Address.class,
        "books.json", Book.class,
        "comments.json", Comment.class,
        "companies.json", Company.class,
        "courses.json", Course.class,
        "employees.json", Employee.class,
        "events.json", Event.class,
        "messages.json", Message.class,
        "notifications.json", Notification.class
    );

    map.forEach(this::deserializeJsonResourceFile);
  }

  private <T> void deserializeJsonResourceFile(String jsonFileName, Class<T> entityClass) {
    logger.info("Deserialize json file '{}' to entities: {}", jsonFileName, entityClass);

    JpaRepository<T, ?> entityRepository = getRepositoryByEntity(entityClass);
    File jsonFile = jsonService.getJsonFileFromResources(jsonFileName);
    List<T> entities = jsonService.convertJsonFileToEntities(jsonFile, entityClass);
    entityRepository.saveAll(entities);
  }

  private <T> JpaRepository<T, ?> getRepositoryByEntity(Class<T> entityClass) {
    Repositories repositories = new Repositories(appContext);

    // The cast is correct because we use the 'getRepositoryFor' method
    // with the 'entityClass' parameter
    @SuppressWarnings("unchecked")
    JpaRepository<T, ?> repository = (JpaRepository<T, ?>) repositories
        .getRepositoryFor(entityClass)
        .orElseThrow(() -> new RepositoryNotFoundException(
            "Can't find repository for entity of type: " + entityClass
        ));
    return repository;
  }
}
