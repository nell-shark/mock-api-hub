package com.nellshark.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nellshark.exceptions.JsonDeserializationException;
import com.nellshark.exceptions.ResourceNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

@Service
public class JsonService {

  private static final Logger logger = LoggerFactory.getLogger(JsonService.class);

  private final ObjectMapper objectMapper;
  private final ResourceLoader resourceLoader;

  public JsonService(ObjectMapper objectMapper, ResourceLoader resourceLoader) {
    this.objectMapper = objectMapper;
    this.resourceLoader = resourceLoader;
  }

  public <T> List<T> convertJsonBytesToEntities(
      @NonNull byte[] jsonBytes,
      @NonNull Class<T> entityClass) {
    logger.info("Convert bytes to List of Entities: {}", entityClass);

    try {
      return objectMapper.readValue(
          jsonBytes,
          objectMapper.getTypeFactory().constructCollectionType(List.class, entityClass)
      );
    } catch (IOException e) {
      throw new JsonDeserializationException(
          "Error converting bytes to list of entities: ", e);
    }
  }

  public byte[] getJsonFileBytesFromResource(@NonNull String jsonFileName) {
    logger.info("Getting resource json file: {}", jsonFileName);
    Resource resource = resourceLoader.getResource("classpath:json/" + jsonFileName);

    try (InputStream inputStream = resource.getInputStream()) {
      return FileCopyUtils.copyToByteArray(inputStream);
    } catch (IOException e) {
      throw new ResourceNotFoundException("Error reading json resource file: " + jsonFileName, e);
    }
  }
}
