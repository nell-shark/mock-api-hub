package com.nellshark.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.data.domain.Sort.Direction.ASC;

import com.nellshark.models.Item;
import com.nellshark.models.Order;
import com.nellshark.repositories.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
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
public class OrderServiceTest {

  @Mock
  private OrderRepository orderRepository;

  private OrderService underTest;

  @BeforeEach
  void setUp() {
    underTest = new OrderService(orderRepository);
  }

  @Test
  void testGetEntityById() {
    Item item = new Item(1L, 1L);
    Order entity = new Order(
        1L,
        LocalDateTime.now(),
        "Status",
        List.of(item)
    );

    when(orderRepository.findById(eq(entity.getId()))).thenReturn(Optional.of(entity));

    Order result = underTest.getEntityById(entity.getId());

    assertEquals(entity, result);
    verify(orderRepository).findById(eq(entity.getId()));
    verifyNoMoreInteractions(orderRepository);
  }

  @Test
  void testGetEntityByIdNotFound() {
    Item item = new Item(1L, 1L);
    Order entity = new Order(
        1L,
        LocalDateTime.now(),
        "Status",
        List.of(item)
    );

    when(orderRepository.findById(eq(entity.getId()))).thenReturn(Optional.empty());

    assertThrows(EntityNotFoundException.class, () -> underTest.getEntityById(entity.getId()));

    verify(orderRepository).findById(eq(entity.getId()));
    verifyNoMoreInteractions(orderRepository);
  }

  @Test
  void testGetEntitiesWithDefaultParams() {
    Map<String, String> filterParams = new HashMap<>();
    Sort sort = Sort.by(ASC, "id");
    Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE, sort);
    Item item = new Item(1L, 1L);
    Order entity = new Order(
        1L,
        LocalDateTime.now(),
        "Status",
        List.of(item)
    );

    List<Order> expectedList = List.of(entity);
    Page<Order> entityPage = new PageImpl<>(expectedList);

    Specification<Order> specification = ArgumentMatchers.any();
    when(orderRepository.findAll(specification, eq(pageable))).thenReturn(entityPage);

    List<Order> result = underTest.getEntities(filterParams);

    assertEquals(expectedList, result);
    verifyNoMoreInteractions(orderRepository);
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
    Item item = new Item(1L, 1L);
    Order entity = new Order(
        1L,
        LocalDateTime.now(),
        "Status",
        List.of(item)
    );

    List<Order> expectedList = List.of(entity);
    Page<Order> entityPage = new PageImpl<>(expectedList);

    Specification<Order> specification = ArgumentMatchers.any();
    when(orderRepository.findAll(specification, eq(pageable))).thenReturn(entityPage);

    List<Order> result = underTest.getEntities(filterParams);

    assertEquals(expectedList, result);
    verifyNoMoreInteractions(orderRepository);
  }

  @Test
  void testGetEntitiesWhenPageIsEmpty() {
    Map<String, String> filterParams = new HashMap<>();
    filterParams.put("direction", "ASC");
    filterParams.put("sort", "id");
    filterParams.put("size", "10");
    Sort sort = Sort.by(ASC, "id");
    Pageable pageable = PageRequest.of(0, 10, sort);
    Item item = new Item(1L, 1L);
    Order entity = new Order(
        1L,
        LocalDateTime.now(),
        "Status",
        List.of(item)
    );

    List<Order> expectedList = List.of(entity);
    Page<Order> entityPage = new PageImpl<>(expectedList);

    Specification<Order> specification = ArgumentMatchers.any();
    when(orderRepository.findAll(specification, eq(pageable))).thenReturn(entityPage);

    List<Order> result = underTest.getEntities(filterParams);

    assertEquals(expectedList, result);
    verifyNoMoreInteractions(orderRepository);
  }

  @Test
  void testGetEntitiesWhenSizeIsEmpty() {
    Map<String, String> filterParams = new HashMap<>();
    filterParams.put("direction", "ASC");
    filterParams.put("sort", "id");
    filterParams.put("page", "10");
    Sort sort = Sort.by(ASC, "id");
    Pageable pageable = PageRequest.of(9, 10, sort);
    Item item = new Item(1L, 1L);
    Order entity = new Order(
        1L,
        LocalDateTime.now(),
        "Status",
        List.of(item)
    );

    List<Order> expectedList = List.of(entity);
    Page<Order> entitPage = new PageImpl<>(expectedList);

    Specification<Order> specification = ArgumentMatchers.any();
    when(orderRepository.findAll(specification, eq(pageable))).thenReturn(entitPage);

    List<Order> result = underTest.getEntities(filterParams);

    assertEquals(expectedList, result);
    verifyNoMoreInteractions(orderRepository);
  }

  @Test
  void testGetEntitiesWithInvalidPage() {
    Map<String, String> filterParams = new HashMap<>();
    filterParams.put("direction", "ASC");
    filterParams.put("page", "-1");
    filterParams.put("size", "10");
    Sort sort = Sort.by(ASC, "id");
    Pageable pageable = PageRequest.of(0, 10, sort);
    Item item = new Item(1L, 1L);
    Order entity = new Order(
        1L,
        LocalDateTime.now(),
        "Status",
        List.of(item)
    );

    List<Order> expectedList = List.of(entity);
    Page<Order> entityPage = new PageImpl<>(expectedList);

    Specification<Order> specification = ArgumentMatchers.any();
    when(orderRepository.findAll(specification, eq(pageable))).thenReturn(entityPage);

    List<Order> result = underTest.getEntities(filterParams);

    assertEquals(expectedList, result);
    verifyNoMoreInteractions(orderRepository);
  }

  @Test
  void testGetEntitiesWithInvalidSize() {
    Map<String, String> filterParams = new HashMap<>();
    filterParams.put("direction", "ASC");
    filterParams.put("page", "1");
    filterParams.put("size", "-1");
    Sort sort = Sort.by(ASC, "id");
    Pageable pageable = PageRequest.of(0, 10, sort);
    Item item = new Item(1L, 1L);
    Order entity = new Order(
        1L,
        LocalDateTime.now(),
        "Status",
        List.of(item)
    );

    List<Order> expectedList = List.of(entity);
    Page<Order> entityPage = new PageImpl<>(expectedList);

    Specification<Order> specification = ArgumentMatchers.any();
    when(orderRepository.findAll(specification, eq(pageable))).thenReturn(entityPage);

    List<Order> result = underTest.getEntities(filterParams);

    assertEquals(expectedList, result);
    verifyNoMoreInteractions(orderRepository);
  }

  @Test
  void testGetEntitiesWithNotExistingField() {
    Map<String, String> filterParams = new HashMap<>();
    filterParams.put("notExistingField", "*");
    Sort sort = Sort.by(ASC, "id");
    Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE, sort);
    Item item = new Item(1L, 1L);
    Order entity = new Order(
        1L,
        LocalDateTime.now(),
        "Status",
        List.of(item)
    );

    List<Order> expectedList = List.of(entity);
    Page<Order> entityPage = new PageImpl<>(expectedList);

    Specification<Order> specification = ArgumentMatchers.any();
    when(orderRepository.findAll(specification, eq(pageable))).thenReturn(entityPage);

    List<Order> result = underTest.getEntities(filterParams);

    assertEquals(expectedList, result);
    verifyNoMoreInteractions(orderRepository);
  }

  @Test
  void testGetItemsByOrderId() {
    Item item = new Item(1L, 1L);
    List<Item> items = List.of(item);
    Order entity = new Order(
        1L,
        LocalDateTime.now(),
        "Status",
        items
    );

    when(orderRepository.findById(eq(entity.getId()))).thenReturn(Optional.of(entity));

    List<Item> result = underTest.getItemsByOrderId(entity.getId());

    assertEquals(items, result);
    verify(orderRepository).findById(eq(entity.getId()));
    verifyNoMoreInteractions(orderRepository);
  }
}
