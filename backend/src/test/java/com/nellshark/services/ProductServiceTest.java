package com.nellshark.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.data.domain.Sort.Direction.ASC;

import com.nellshark.models.Dimension;
import com.nellshark.models.Product;
import com.nellshark.repositories.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

  @Mock
  private ProductRepository productRepository;

  private ProductService underTest;

  @BeforeEach
  void setUp() {
    underTest = new ProductService(productRepository);
  }

  @Test
  void testGetEntityById() {
    Dimension dimension = new Dimension(1.0, 2.0, 3.0);
    Product entity = new Product(
        1L,
        "Name",
        "Description",
        99.99,
        "Currency",
        "Manufacturer",
        "Category",
        true,
        dimension
    );

    when(productRepository.findById(eq(entity.getId()))).thenReturn(Optional.of(entity));

    Product result = underTest.getEntityById(entity.getId());

    assertEquals(entity, result);
    verify(productRepository).findById(eq(entity.getId()));
    verifyNoMoreInteractions(productRepository);
  }

  @Test
  void testGetEntityByIdNotFound() {
    Dimension dimension = new Dimension(1.0, 2.0, 3.0);
    Product entity = new Product(
        1L,
        "Name",
        "Description",
        99.99,
        "Currency",
        "Manufacturer",
        "Category",
        true,
        dimension
    );

    when(productRepository.findById(eq(entity.getId()))).thenReturn(Optional.empty());

    assertThrows(EntityNotFoundException.class, () -> underTest.getEntityById(entity.getId()));

    verify(productRepository).findById(eq(entity.getId()));
    verifyNoMoreInteractions(productRepository);
  }

  @Test
  void testGetEntitiesWithDefaultParams() {
    Map<String, String> filterParams = new HashMap<>();
    Sort sort = Sort.by(ASC, "id");
    Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE, sort);
    Dimension dimension = new Dimension(1.0, 2.0, 3.0);
    Product entity = new Product(
        1L,
        "Name",
        "Description",
        99.99,
        "Currency",
        "Manufacturer",
        "Category",
        true,
        dimension
    );

    List<Product> expectedList = List.of(entity);
    Page<Product> entityPage = new PageImpl<>(expectedList);

    Specification<Product> specification = ArgumentMatchers.any();
    when(productRepository.findAll(specification, eq(pageable))).thenReturn(entityPage);

    List<Product> result = underTest.getEntities(filterParams);

    assertEquals(expectedList, result);
    verifyNoMoreInteractions(productRepository);
  }

  @Test
  void testGetEntitiesWithFilterParams() {
    Map<String, String> filterParams = new HashMap<>();
    filterParams.put("direction", "ASC");
    filterParams.put("sort", "name");
    filterParams.put("page", "1");
    filterParams.put("size", "10");
    Sort sort = Sort.by(ASC, "name");
    Pageable pageable = PageRequest.of(0, 10, sort);
    Dimension dimension = new Dimension(1.0, 2.0, 3.0);
    Product entity = new Product(
        1L,
        "Name",
        "Description",
        99.99,
        "Currency",
        "Manufacturer",
        "Category",
        true,
        dimension
    );

    List<Product> expectedList = List.of(entity);
    Page<Product> entityPage = new PageImpl<>(expectedList);

    Specification<Product> specification = ArgumentMatchers.any();
    when(productRepository.findAll(specification, eq(pageable))).thenReturn(entityPage);

    List<Product> result = underTest.getEntities(filterParams);

    assertEquals(expectedList, result);
    verifyNoMoreInteractions(productRepository);
  }

  @Test
  void testGetEntitiesWhenPageIsEmpty() {
    Map<String, String> filterParams = new HashMap<>();
    filterParams.put("direction", "ASC");
    filterParams.put("sort", "id");
    filterParams.put("size", "10");
    Sort sort = Sort.by(ASC, "id");
    Pageable pageable = PageRequest.of(0, 10, sort);
    Dimension dimension = new Dimension(1.0, 2.0, 3.0);
    Product entity = new Product(
        1L,
        "Name",
        "Description",
        99.99,
        "Currency",
        "Manufacturer",
        "Category",
        true,
        dimension
    );

    List<Product> expectedList = List.of(entity);
    Page<Product> entityPage = new PageImpl<>(expectedList);

    Specification<Product> specification = ArgumentMatchers.any();
    when(productRepository.findAll(specification, eq(pageable))).thenReturn(entityPage);

    List<Product> result = underTest.getEntities(filterParams);

    assertEquals(expectedList, result);
    verifyNoMoreInteractions(productRepository);
  }

  @Test
  void testGetEntitiesWhenSizeIsEmpty() {
    Map<String, String> filterParams = new HashMap<>();
    filterParams.put("direction", "ASC");
    filterParams.put("sort", "id");
    filterParams.put("page", "10");
    Sort sort = Sort.by(ASC, "id");
    Pageable pageable = PageRequest.of(9, 10, sort);
    Dimension dimension = new Dimension(1.0, 2.0, 3.0);
    Product entity = new Product(
        1L,
        "Name",
        "Description",
        99.99,
        "Currency",
        "Manufacturer",
        "Category",
        true,
        dimension
    );

    List<Product> expectedList = List.of(entity);
    Page<Product> entitPage = new PageImpl<>(expectedList);

    Specification<Product> specification = ArgumentMatchers.any();
    when(productRepository.findAll(specification, eq(pageable))).thenReturn(entitPage);

    List<Product> result = underTest.getEntities(filterParams);

    assertEquals(expectedList, result);
    verifyNoMoreInteractions(productRepository);
  }

  @Test
  void testGetEntitiesWithInvalidPage() {
    Map<String, String> filterParams = new HashMap<>();
    filterParams.put("direction", "ASC");
    filterParams.put("page", "-1");
    filterParams.put("size", "10");
    Sort sort = Sort.by(ASC, "id");
    Pageable pageable = PageRequest.of(0, 10, sort);
    Dimension dimension = new Dimension(1.0, 2.0, 3.0);
    Product entity = new Product(
        1L,
        "Name",
        "Description",
        99.99,
        "Currency",
        "Manufacturer",
        "Category",
        true,
        dimension
    );

    List<Product> expectedList = List.of(entity);
    Page<Product> entityPage = new PageImpl<>(expectedList);

    Specification<Product> specification = ArgumentMatchers.any();
    when(productRepository.findAll(specification, eq(pageable))).thenReturn(entityPage);

    List<Product> result = underTest.getEntities(filterParams);

    assertEquals(expectedList, result);
    verifyNoMoreInteractions(productRepository);
  }

  @Test
  void testGetEntitiesWithInvalidSize() {
    Map<String, String> filterParams = new HashMap<>();
    filterParams.put("direction", "ASC");
    filterParams.put("page", "1");
    filterParams.put("size", "-1");
    Sort sort = Sort.by(ASC, "id");
    Pageable pageable = PageRequest.of(0, 10, sort);
    Dimension dimension = new Dimension(1.0, 2.0, 3.0);
    Product entity = new Product(
        1L,
        "Name",
        "Description",
        99.99,
        "Currency",
        "Manufacturer",
        "Category",
        true,
        dimension
    );

    List<Product> expectedList = List.of(entity);
    Page<Product> entityPage = new PageImpl<>(expectedList);

    Specification<Product> specification = ArgumentMatchers.any();
    when(productRepository.findAll(specification, eq(pageable))).thenReturn(entityPage);

    List<Product> result = underTest.getEntities(filterParams);

    assertEquals(expectedList, result);
    verifyNoMoreInteractions(productRepository);
  }

  @Test
  void testGetEntitiesWithNotExistingField() {
    Map<String, String> filterParams = new HashMap<>();
    filterParams.put("notExistingField", "*");
    Sort sort = Sort.by(ASC, "id");
    Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE, sort);
    Dimension dimension = new Dimension(1.0, 2.0, 3.0);
    Product entity = new Product(
        1L,
        "Name",
        "Description",
        99.99,
        "Currency",
        "Manufacturer",
        "Category",
        true,
        dimension
    );

    List<Product> expectedList = List.of(entity);
    Page<Product> entityPage = new PageImpl<>(expectedList);

    Specification<Product> specification = ArgumentMatchers.any();
    when(productRepository.findAll(specification, eq(pageable))).thenReturn(entityPage);

    List<Product> result = underTest.getEntities(filterParams);

    assertEquals(expectedList, result);
    verifyNoMoreInteractions(productRepository);
  }

  @Test
  void testGetDimensionsByProductId() {
    Dimension dimensions = new Dimension(1.0, 2.0, 3.0);
    Product entity = new Product(
        1L,
        "Name",
        "Description",
        99.99,
        "Currency",
        "Manufacturer",
        "Category",
        true,
        dimensions
    );

    when(productRepository.findById(eq(entity.getId()))).thenReturn(Optional.of(entity));

    Dimension result = underTest.getDimensionsByProductId(entity.getId());

    assertEquals(dimensions, result);
    verifyNoMoreInteractions(productRepository);
  }
}
