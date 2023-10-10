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
import com.nellshark.models.Order;
import com.nellshark.models.Post;
import com.nellshark.models.Product;
import com.nellshark.models.Project;
import com.nellshark.models.Recipe;
import com.nellshark.models.Review;
import com.nellshark.models.Todo;
import com.nellshark.models.User;
import com.nellshark.services.JsonService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.support.Repositories;

@Configuration
public class DatabaseInitializer implements CommandLineRunner {

  private static final Logger logger = LoggerFactory.getLogger(DatabaseInitializer.class);
  private static final Map<String, Class<?>> ENTITY_MAP = new HashMap<>();

  static {
    ENTITY_MAP.put("addresses.json", Address.class);
    ENTITY_MAP.put("books.json", Book.class);
    ENTITY_MAP.put("comments.json", Comment.class);
    ENTITY_MAP.put("companies.json", Company.class);
    ENTITY_MAP.put("courses.json", Course.class);
    ENTITY_MAP.put("employees.json", Employee.class);
    ENTITY_MAP.put("events.json", Event.class);
    ENTITY_MAP.put("messages.json", Message.class);
    ENTITY_MAP.put("notifications.json", Notification.class);
    ENTITY_MAP.put("orders.json", Order.class);
    ENTITY_MAP.put("posts.json", Post.class);
    ENTITY_MAP.put("products.json", Product.class);
    ENTITY_MAP.put("projects.json", Project.class);
    ENTITY_MAP.put("recipes.json", Recipe.class);
    ENTITY_MAP.put("reviews.json", Review.class);
    ENTITY_MAP.put("todos.json", Todo.class);
    ENTITY_MAP.put("users.json", User.class);
  }

  private final JsonService jsonService;
  private final Repositories repositories;


  public DatabaseInitializer(JsonService jsonService, ApplicationContext applicationContext) {
    this.jsonService = jsonService;
    this.repositories = new Repositories(applicationContext);
  }

  @Override
  public void run(String... args) {
    logger.info("Starting initialization database");
    ENTITY_MAP.forEach(this::deserializeJsonResourceFile);
  }

  private <T> void deserializeJsonResourceFile(String jsonFileName, Class<T> entityClass) {
    logger.info("Deserialize json file '{}' to entities: {}", jsonFileName, entityClass);

    JpaRepository<T, ?> entityRepository = getRepositoryByEntity(entityClass);
    byte[] jsonBytes = jsonService.getJsonFileBytesFromResource(jsonFileName);
    List<T> entities = jsonService.convertJsonBytesToEntities(jsonBytes, entityClass);
    entityRepository.saveAll(entities);
  }

  private <T> JpaRepository<T, ?> getRepositoryByEntity(Class<T> entityClass) {
    // The cast is correct because we use the 'getRepositoryFor' method
    // with the 'entityClass' parameter
    @SuppressWarnings("unchecked")
    JpaRepository<T, ?> entityRepository = (JpaRepository<T, ?>) repositories
        .getRepositoryFor(entityClass)
        .orElseThrow(() -> new RepositoryNotFoundException(
            "Repository not found for entity class: " + entityClass
        ));
    return entityRepository;
  }
}
