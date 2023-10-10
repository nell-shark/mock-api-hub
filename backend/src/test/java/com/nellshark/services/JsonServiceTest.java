package com.nellshark.services;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.nellshark.exceptions.JsonDeserializationException;
import com.nellshark.exceptions.ResourceNotFoundException;
import com.nellshark.models.Address;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.FileCopyUtils;

@ExtendWith(MockitoExtension.class)
class JsonServiceTest {

  @Mock
  private ObjectMapper objectMapper;

  @Mock
  private ResourceLoader resourceLoader;

  private JsonService underTest;

  @BeforeEach
  void setUp() {
    underTest = new JsonService(objectMapper, resourceLoader);
  }


  @Test
  void testConvertJsonBytesToEntities() throws IOException {
    byte[] jsonBytes = """
        {
            "id": 1,
            "street": "123 Main Street",
            "city": "Exampleville",
            "countryCode": "US",
            "country": "United States",
            "postCode": "12345"
        }
        """.getBytes();
    Address entity = new Address(
        1L,
        "123 Main Street",
        "Exampleville",
        "US",
        "United States",
        "12345"
    );
    Class<?> entityClass = Address.class;
    List<Address> expectedEntities = List.of(entity);

    TypeFactory typeFactory = mock(TypeFactory.class);
    CollectionType collectionType = mock(CollectionType.class);
    when(objectMapper.getTypeFactory())
        .thenReturn(typeFactory);
    when(typeFactory.constructCollectionType(eq(List.class), eq(entityClass)))
        .thenReturn(collectionType);
    when(objectMapper.readValue(eq(jsonBytes), any(JavaType.class)))
        .thenReturn(expectedEntities);

    List<Address> result = underTest.convertJsonBytesToEntities(jsonBytes, Address.class);

    assertEquals(expectedEntities, result);
  }

  @Test
  void testConvertJsonBytesToEntitiesWithIOException() throws IOException {
    byte[] jsonBytes = """
        {
            "id": 1,
            "street": "123 Main Street",
            "city": "Exampleville",
            "countryCode": "US",
            "country": "United States",
            "postCode": "12345"
        }
        """.getBytes();
    Address entity = new Address(
        1L,
        "123 Main Street",
        "Exampleville",
        "US",
        "United States",
        "12345"
    );
    Class<?> entityClass = Address.class;

    TypeFactory typeFactory = mock(TypeFactory.class);
    CollectionType collectionType = mock(CollectionType.class);
    when(objectMapper.getTypeFactory())
        .thenReturn(typeFactory);
    when(typeFactory.constructCollectionType(eq(List.class), eq(entityClass)))
        .thenReturn(collectionType);
    when(objectMapper.readValue(eq(jsonBytes), any(JavaType.class)))
        .thenThrow(new IOException("Test exception"));

    assertThrows(JsonDeserializationException.class,
        () -> underTest.convertJsonBytesToEntities(jsonBytes, Address.class));
  }

  @Test
  void testGetJsonFileBytesFromResource() throws IOException {
    String jsonFileName = "test.json";
    byte[] expectedBytes = "Test JSON".getBytes();

    InputStream inputStreamMock = mock(InputStream.class);
    Resource resourceMock = mock(Resource.class);
    when(resourceLoader.getResource("classpath:json/" + jsonFileName))
        .thenReturn(resourceMock);
    when(resourceMock.getInputStream())
        .thenReturn(inputStreamMock);

    try (MockedStatic<FileCopyUtils> fileCopyUtilsMockedStatic = mockStatic(FileCopyUtils.class)) {
      fileCopyUtilsMockedStatic
          .when(() -> FileCopyUtils.copyToByteArray(inputStreamMock))
          .thenReturn(expectedBytes);

      byte[] actualBytes = underTest.getJsonFileBytesFromResource(jsonFileName);

      assertArrayEquals(expectedBytes, actualBytes);
    }
  }

  @Test
  void testGetJsonFileBytesFromResourceWithIOException() throws IOException {
    String jsonFileName = "test.json";

    Resource resourceMock = mock(Resource.class);
    when(resourceLoader.getResource("classpath:json/" + jsonFileName))
        .thenReturn(resourceMock);
    when(resourceMock.getInputStream())
        .thenThrow(new IOException("Test exception"));

    assertThrows(ResourceNotFoundException.class,
        () -> underTest.getJsonFileBytesFromResource(jsonFileName));
  }
}
