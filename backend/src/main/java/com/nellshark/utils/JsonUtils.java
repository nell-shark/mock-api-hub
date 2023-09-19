package com.nellshark.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nellshark.exceptions.JsonDeserializationException;
import com.nellshark.exceptions.ResourceNotFoundException;
import java.io.File;
import java.io.IOException;
import java.util.List;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.lang.NonNull;

public final class JsonUtils {

  private static final ObjectMapper objectMapper = new ObjectMapper();
  private static final ResourceLoader resourceLoader = new DefaultResourceLoader();

  private JsonUtils() {
  }

  public static <T> List<T> convertJsonFileToEntities(
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

  public static File getJsonFileFromResources(String jsonFileName) {
    Resource resource = resourceLoader.getResource("classpath:json/" + jsonFileName);

    try {
      return resource.getFile();
    } catch (IOException e) {
      throw new ResourceNotFoundException("Error getting resource json file: " + jsonFileName, e);
    }
  }
}
