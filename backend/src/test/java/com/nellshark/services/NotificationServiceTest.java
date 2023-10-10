package com.nellshark.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.data.domain.Sort.Direction.ASC;

import com.nellshark.models.Notification;
import com.nellshark.repositories.NotificationRepository;
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
public class NotificationServiceTest {

  @Mock
  private NotificationRepository notificationRepository;

  private NotificationService underTest;

  @BeforeEach
  void setUp() {
    underTest = new NotificationService(notificationRepository);
  }

  @Test
  void testGetEntityById() {
    Notification entity = new Notification(
        1L,
        "Title",
        "Message",
        LocalDateTime.now(),
        true
    );

    when(notificationRepository.findById(eq(entity.getId()))).thenReturn(Optional.of(entity));

    Notification result = underTest.getEntityById(entity.getId());

    assertEquals(entity, result);
    verify(notificationRepository).findById(eq(entity.getId()));
    verifyNoMoreInteractions(notificationRepository);
  }

  @Test
  void testGetEntityByIdNotFound() {
    Notification entity = new Notification(
        1L,
        "Title",
        "Message",
        LocalDateTime.now(),
        true
    );

    when(notificationRepository.findById(eq(entity.getId()))).thenReturn(Optional.empty());

    assertThrows(EntityNotFoundException.class, () -> underTest.getEntityById(entity.getId()));

    verify(notificationRepository).findById(eq(entity.getId()));
    verifyNoMoreInteractions(notificationRepository);
  }

  @Test
  void testGetEntitiesWithDefaultParams() {
    Map<String, String> filterParams = new HashMap<>();
    Sort sort = Sort.by(ASC, "id");
    Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE, sort);
    Notification entity = new Notification(
        1L,
        "Title",
        "Message",
        LocalDateTime.now(),
        true
    );

    List<Notification> expectedList = List.of(entity);
    Page<Notification> entityPage = new PageImpl<>(expectedList);

    Specification<Notification> specification = ArgumentMatchers.any();
    when(notificationRepository.findAll(specification, eq(pageable))).thenReturn(entityPage);

    List<Notification> result = underTest.getEntities(filterParams);

    assertEquals(expectedList, result);
    verifyNoMoreInteractions(notificationRepository);
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
    Notification entity = new Notification(
        1L,
        "Title",
        "Message",
        LocalDateTime.now(),
        true
    );

    List<Notification> expectedList = List.of(entity);
    Page<Notification> entityPage = new PageImpl<>(expectedList);

    Specification<Notification> specification = ArgumentMatchers.any();
    when(notificationRepository.findAll(specification, eq(pageable))).thenReturn(entityPage);

    List<Notification> result = underTest.getEntities(filterParams);

    assertEquals(expectedList, result);
    verifyNoMoreInteractions(notificationRepository);
  }

  @Test
  void testGetEntitiesWhenPageIsEmpty() {
    Map<String, String> filterParams = new HashMap<>();
    filterParams.put("direction", "ASC");
    filterParams.put("sort", "id");
    filterParams.put("size", "10");
    Sort sort = Sort.by(ASC, "id");
    Pageable pageable = PageRequest.of(0, 10, sort);
    Notification entity = new Notification(
        1L,
        "Title",
        "Message",
        LocalDateTime.now(),
        true
    );

    List<Notification> expectedList = List.of(entity);
    Page<Notification> entityPage = new PageImpl<>(expectedList);

    Specification<Notification> specification = ArgumentMatchers.any();
    when(notificationRepository.findAll(specification, eq(pageable))).thenReturn(entityPage);

    List<Notification> result = underTest.getEntities(filterParams);

    assertEquals(expectedList, result);
    verifyNoMoreInteractions(notificationRepository);
  }

  @Test
  void testGetEntitiesWhenSizeIsEmpty() {
    Map<String, String> filterParams = new HashMap<>();
    filterParams.put("direction", "ASC");
    filterParams.put("sort", "id");
    filterParams.put("page", "10");
    Sort sort = Sort.by(ASC, "id");
    Pageable pageable = PageRequest.of(9, 10, sort);
    Notification entity = new Notification(
        1L,
        "Title",
        "Message",
        LocalDateTime.now(),
        true
    );

    List<Notification> expectedList = List.of(entity);
    Page<Notification> entitPage = new PageImpl<>(expectedList);

    Specification<Notification> specification = ArgumentMatchers.any();
    when(notificationRepository.findAll(specification, eq(pageable))).thenReturn(entitPage);

    List<Notification> result = underTest.getEntities(filterParams);

    assertEquals(expectedList, result);
    verifyNoMoreInteractions(notificationRepository);
  }

  @Test
  void testGetEntitiesWithInvalidPage() {
    Map<String, String> filterParams = new HashMap<>();
    filterParams.put("direction", "ASC");
    filterParams.put("page", "-1");
    filterParams.put("size", "10");
    Sort sort = Sort.by(ASC, "id");
    Pageable pageable = PageRequest.of(0, 10, sort);
    Notification entity = new Notification(
        1L,
        "Title",
        "Message",
        LocalDateTime.now(),
        true
    );

    List<Notification> expectedList = List.of(entity);
    Page<Notification> entityPage = new PageImpl<>(expectedList);

    Specification<Notification> specification = ArgumentMatchers.any();
    when(notificationRepository.findAll(specification, eq(pageable))).thenReturn(entityPage);

    List<Notification> result = underTest.getEntities(filterParams);

    assertEquals(expectedList, result);
    verifyNoMoreInteractions(notificationRepository);
  }

  @Test
  void testGetEntitiesWithInvalidSize() {
    Map<String, String> filterParams = new HashMap<>();
    filterParams.put("direction", "ASC");
    filterParams.put("page", "1");
    filterParams.put("size", "-1");
    Sort sort = Sort.by(ASC, "id");
    Pageable pageable = PageRequest.of(0, 10, sort);
    Notification entity = new Notification(
        1L,
        "Title",
        "Message",
        LocalDateTime.now(),
        true
    );

    List<Notification> expectedList = List.of(entity);
    Page<Notification> entityPage = new PageImpl<>(expectedList);

    Specification<Notification> specification = ArgumentMatchers.any();
    when(notificationRepository.findAll(specification, eq(pageable))).thenReturn(entityPage);

    List<Notification> result = underTest.getEntities(filterParams);

    assertEquals(expectedList, result);
    verifyNoMoreInteractions(notificationRepository);
  }

  @Test
  void testGetEntitiesWithNotExistingField() {
    Map<String, String> filterParams = new HashMap<>();
    filterParams.put("notExistingField", "*");
    Sort sort = Sort.by(ASC, "id");
    Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE, sort);
    Notification entity = new Notification(
        1L,
        "Title",
        "Message",
        LocalDateTime.now(),
        true
    );

    List<Notification> expectedList = List.of(entity);
    Page<Notification> entityPage = new PageImpl<>(expectedList);

    Specification<Notification> specification = ArgumentMatchers.any();
    when(notificationRepository.findAll(specification, eq(pageable))).thenReturn(entityPage);

    List<Notification> result = underTest.getEntities(filterParams);

    assertEquals(expectedList, result);
    verifyNoMoreInteractions(notificationRepository);
  }
}
