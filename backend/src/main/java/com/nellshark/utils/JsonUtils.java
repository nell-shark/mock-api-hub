package com.nellshark.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nellshark.exceptions.JsonDeserializationException;
import com.nellshark.exceptions.ResourceNotFoundException;
import java.io.File;
import java.io.IOException;
import java.util.List;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public final class JsonUtils {

  private final ObjectMapper objectMapper;
  private final ResourceLoader resourceLoader;

  private JsonUtils(ObjectMapper objectMapper, ResourceLoader resourceLoader) {
    this.objectMapper = objectMapper;
    this.resourceLoader = resourceLoader;
  }

  public <T> List<T> convertJsonFileToEntities(
      @NonNull File jsonFile,
      @NonNull Class<T> entityClass) {

    try {
      return objectMapper.readValue(
          jsonFile,
          objectMapper.getTypeFactory().constructCollectionType(List.class, entityClass)
      );
    } catch (IOException e) {
      throw new JsonDeserializationException(
          "Error converting JSON file to list: " + jsonFile.getPath(), e);
    }
  }

  public File getResourceJsonFile(String jsonFileName) {
    Resource resource = resourceLoader.getResource("classpath:json/" + jsonFileName);

    try {
      return resource.getFile();
    } catch (IOException e) {
      throw new ResourceNotFoundException("Error getting resource json file: " + jsonFileName, e);
    }
  }
}
