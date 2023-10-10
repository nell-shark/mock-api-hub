package com.nellshark.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.data.domain.Sort.Direction.ASC;

import com.nellshark.models.Message;
import com.nellshark.repositories.MessageRepository;
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
public class MessageServiceTest {

  @Mock
  private MessageRepository messageRepository;

  private MessageService underTest;

  @BeforeEach
  void setUp() {
    underTest = new MessageService(messageRepository);
  }

  @Test
  void testGetEntityById() {
    Message entity = new Message(
        1L,
        "Sender",
        "Reciver",
        LocalDateTime.now(),
        "Content"
    );

    when(messageRepository.findById(eq(entity.getId()))).thenReturn(Optional.of(entity));

    Message result = underTest.getEntityById(entity.getId());

    assertEquals(entity, result);
    verify(messageRepository).findById(eq(entity.getId()));
    verifyNoMoreInteractions(messageRepository);
  }

  @Test
  void testGetEntityByIdNotFound() {
    Message entity = new Message(
        1L,
        "Sender",
        "Reciver",
        LocalDateTime.now(),
        "Content"
    );

    when(messageRepository.findById(eq(entity.getId()))).thenReturn(Optional.empty());

    assertThrows(EntityNotFoundException.class, () -> underTest.getEntityById(entity.getId()));

    verify(messageRepository).findById(eq(entity.getId()));
    verifyNoMoreInteractions(messageRepository);
  }

  @Test
  void testGetEntitiesWithDefaultParams() {
    Map<String, String> filterParams = new HashMap<>();
    Sort sort = Sort.by(ASC, "id");
    Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE, sort);
    Message entity = new Message(
        1L,
        "Sender",
        "Reciver",
        LocalDateTime.now(),
        "Content"
    );

    List<Message> expectedList = List.of(entity);
    Page<Message> entityPage = new PageImpl<>(expectedList);

    Specification<Message> specification = ArgumentMatchers.any();
    when(messageRepository.findAll(specification, eq(pageable))).thenReturn(entityPage);

    List<Message> result = underTest.getEntities(filterParams);

    assertEquals(expectedList, result);
    verifyNoMoreInteractions(messageRepository);
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
    Message entity = new Message(
        1L,
        "Sender",
        "Reciver",
        LocalDateTime.now(),
        "Content"
    );

    List<Message> expectedList = List.of(entity);
    Page<Message> entityPage = new PageImpl<>(expectedList);

    Specification<Message> specification = ArgumentMatchers.any();
    when(messageRepository.findAll(specification, eq(pageable))).thenReturn(entityPage);

    List<Message> result = underTest.getEntities(filterParams);

    assertEquals(expectedList, result);
    verifyNoMoreInteractions(messageRepository);
  }

  @Test
  void testGetEntitiesWhenPageIsEmpty() {
    Map<String, String> filterParams = new HashMap<>();
    filterParams.put("direction", "ASC");
    filterParams.put("sort", "id");
    filterParams.put("size", "10");
    Sort sort = Sort.by(ASC, "id");
    Pageable pageable = PageRequest.of(0, 10, sort);
    Message entity = new Message(
        1L,
        "Sender",
        "Reciver",
        LocalDateTime.now(),
        "Content"
    );

    List<Message> expectedList = List.of(entity);
    Page<Message> entityPage = new PageImpl<>(expectedList);

    Specification<Message> specification = ArgumentMatchers.any();
    when(messageRepository.findAll(specification, eq(pageable))).thenReturn(entityPage);

    List<Message> result = underTest.getEntities(filterParams);

    assertEquals(expectedList, result);
    verifyNoMoreInteractions(messageRepository);
  }

  @Test
  void testGetEntitiesWhenSizeIsEmpty() {
    Map<String, String> filterParams = new HashMap<>();
    filterParams.put("direction", "ASC");
    filterParams.put("sort", "id");
    filterParams.put("page", "10");
    Sort sort = Sort.by(ASC, "id");
    Pageable pageable = PageRequest.of(9, 10, sort);
    Message entity = new Message(
        1L,
        "Sender",
        "Reciver",
        LocalDateTime.now(),
        "Content"
    );

    List<Message> expectedList = List.of(entity);
    Page<Message> entitPage = new PageImpl<>(expectedList);

    Specification<Message> specification = ArgumentMatchers.any();
    when(messageRepository.findAll(specification, eq(pageable))).thenReturn(entitPage);

    List<Message> result = underTest.getEntities(filterParams);

    assertEquals(expectedList, result);
    verifyNoMoreInteractions(messageRepository);
  }

  @Test
  void testGetEntitiesWithInvalidPage() {
    Map<String, String> filterParams = new HashMap<>();
    filterParams.put("direction", "ASC");
    filterParams.put("page", "-1");
    filterParams.put("size", "10");
    Sort sort = Sort.by(ASC, "id");
    Pageable pageable = PageRequest.of(0, 10, sort);
    Message entity = new Message(
        1L,
        "Sender",
        "Reciver",
        LocalDateTime.now(),
        "Content"
    );

    List<Message> expectedList = List.of(entity);
    Page<Message> entityPage = new PageImpl<>(expectedList);

    Specification<Message> specification = ArgumentMatchers.any();
    when(messageRepository.findAll(specification, eq(pageable))).thenReturn(entityPage);

    List<Message> result = underTest.getEntities(filterParams);

    assertEquals(expectedList, result);
    verifyNoMoreInteractions(messageRepository);
  }

  @Test
  void testGetEntitiesWithInvalidSize() {
    Map<String, String> filterParams = new HashMap<>();
    filterParams.put("direction", "ASC");
    filterParams.put("page", "1");
    filterParams.put("size", "-1");
    Sort sort = Sort.by(ASC, "id");
    Pageable pageable = PageRequest.of(0, 10, sort);
    Message entity = new Message(
        1L,
        "Sender",
        "Reciver",
        LocalDateTime.now(),
        "Content"
    );

    List<Message> expectedList = List.of(entity);
    Page<Message> entityPage = new PageImpl<>(expectedList);

    Specification<Message> specification = ArgumentMatchers.any();
    when(messageRepository.findAll(specification, eq(pageable))).thenReturn(entityPage);

    List<Message> result = underTest.getEntities(filterParams);

    assertEquals(expectedList, result);
    verifyNoMoreInteractions(messageRepository);
  }

  @Test
  void testGetEntitiesWithNotExistingField() {
    Map<String, String> filterParams = new HashMap<>();
    filterParams.put("notExistingField", "*");
    Sort sort = Sort.by(ASC, "id");
    Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE, sort);
    Message entity = new Message(
        1L,
        "Sender",
        "Reciver",
        LocalDateTime.now(),
        "Content"
    );

    List<Message> expectedList = List.of(entity);
    Page<Message> entityPage = new PageImpl<>(expectedList);

    Specification<Message> specification = ArgumentMatchers.any();
    when(messageRepository.findAll(specification, eq(pageable))).thenReturn(entityPage);

    List<Message> result = underTest.getEntities(filterParams);

    assertEquals(expectedList, result);
    verifyNoMoreInteractions(messageRepository);
  }
}
