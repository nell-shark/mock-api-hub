package com.nellshark.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nellshark.exceptions.JsonDeserializationException;
import com.nellshark.exceptions.ResourceNotFoundException;
import java.io.File;
import java.io.IOException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
public class JsonService {

  private static final Logger logger = LoggerFactory.getLogger(JsonService.class);

  private final ObjectMapper objectMapper;
  private final ResourceLoader resourceLoader;

  public JsonService(ObjectMapper objectMapper, ResourceLoader resourceLoader) {
    this.objectMapper = objectMapper;
    this.resourceLoader = resourceLoader;
  }

  public <T> List<T> convertJsonFileToEntities(
      @NonNull File jsonFile,
      @NonNull Class<T> entityClass) {
    logger.info("Convert json file '{}' to List of Entities: {}", jsonFile, entityClass);

    try {
      return objectMapper.readValue(
          jsonFile,
          objectMapper.getTypeFactory().constructCollectionType(List.class, entityClass)
      );
    } catch (IOException e) {
      throw new JsonDeserializationException(
          "Error converting JSON file to list of entities: " + jsonFile.getPath(), e);
    }
  }

  public File getJsonFileFromResources(@NonNull String jsonFileName) {
    logger.info("Getting resource json file: {}", jsonFileName);
    Resource resource = resourceLoader.getResource("classpath:json/" + jsonFileName);

    try {
      return resource.getFile();
    } catch (IOException e) {
      throw new ResourceNotFoundException("Error getting resource json file: " + jsonFileName, e);
    }
  }
}
