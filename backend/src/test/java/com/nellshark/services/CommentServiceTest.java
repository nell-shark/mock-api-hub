package com.nellshark.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.data.domain.Sort.Direction.ASC;

import com.nellshark.models.Comment;
import com.nellshark.repositories.CommentRepository;
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
public class CommentServiceTest {

  @Mock
  private CommentRepository commentRepository;

  private CommentService underTest;

  @BeforeEach
  void setUp() {
    underTest = new CommentService(commentRepository);
  }

  @Test
  void testGetEntityById() {
    Comment entity = new Comment(
        1L,
        "Text",
        1L,
        1L
    );

    when(commentRepository.findById(eq(entity.getId()))).thenReturn(Optional.of(entity));

    Comment result = underTest.getEntityById(entity.getId());

    assertEquals(entity, result);
    verify(commentRepository).findById(eq(entity.getId()));
    verifyNoMoreInteractions(commentRepository);
  }

  @Test
  void testGetEntityByIdNotFound() {
    Comment entity = new Comment(
        1L,
        "Text",
        1L,
        1L
    );

    when(commentRepository.findById(eq(entity.getId()))).thenReturn(Optional.empty());

    assertThrows(EntityNotFoundException.class, () -> underTest.getEntityById(entity.getId()));

    verify(commentRepository).findById(eq(entity.getId()));
    verifyNoMoreInteractions(commentRepository);
  }

  @Test
  void testGetEntitiesWithDefaultParams() {
    Map<String, String> filterParams = new HashMap<>();
    Sort sort = Sort.by(ASC, "id");
    Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE, sort);
    Comment entity = new Comment(
        1L,
        "Text",
        1L,
        1L
    );

    List<Comment> expectedList = List.of(entity);
    Page<Comment> entityPage = new PageImpl<>(expectedList);

    Specification<Comment> specification = ArgumentMatchers.any();
    when(commentRepository.findAll(specification, eq(pageable))).thenReturn(entityPage);

    List<Comment> result = underTest.getEntities(filterParams);

    assertEquals(expectedList, result);
    verifyNoMoreInteractions(commentRepository);
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
    Comment entity = new Comment(
        1L,
        "Text",
        1L,
        1L
    );

    List<Comment> expectedList = List.of(entity);
    Page<Comment> entityPage = new PageImpl<>(expectedList);

    Specification<Comment> specification = ArgumentMatchers.any();
    when(commentRepository.findAll(specification, eq(pageable))).thenReturn(entityPage);

    List<Comment> result = underTest.getEntities(filterParams);

    assertEquals(expectedList, result);
    verifyNoMoreInteractions(commentRepository);
  }

  @Test
  void testGetEntitiesWhenPageIsEmpty() {
    Map<String, String> filterParams = new HashMap<>();
    filterParams.put("direction", "ASC");
    filterParams.put("sort", "id");
    filterParams.put("size", "10");
    Sort sort = Sort.by(ASC, "id");
    Pageable pageable = PageRequest.of(0, 10, sort);
    Comment entity = new Comment(
        1L,
        "Text",
        1L,
        1L
    );

    List<Comment> expectedList = List.of(entity);
    Page<Comment> entityPage = new PageImpl<>(expectedList);

    Specification<Comment> specification = ArgumentMatchers.any();
    when(commentRepository.findAll(specification, eq(pageable))).thenReturn(entityPage);

    List<Comment> result = underTest.getEntities(filterParams);

    assertEquals(expectedList, result);
    verifyNoMoreInteractions(commentRepository);
  }

  @Test
  void testGetEntitiesWhenSizeIsEmpty() {
    Map<String, String> filterParams = new HashMap<>();
    filterParams.put("direction", "ASC");
    filterParams.put("sort", "id");
    filterParams.put("page", "10");
    Sort sort = Sort.by(ASC, "id");
    Pageable pageable = PageRequest.of(9, 10, sort);
    Comment entity = new Comment(
        1L,
        "Text",
        1L,
        1L
    );

    List<Comment> expectedList = List.of(entity);
    Page<Comment> entitPage = new PageImpl<>(expectedList);

    Specification<Comment> specification = ArgumentMatchers.any();
    when(commentRepository.findAll(specification, eq(pageable))).thenReturn(entitPage);

    List<Comment> result = underTest.getEntities(filterParams);

    assertEquals(expectedList, result);
    verifyNoMoreInteractions(commentRepository);
  }

  @Test
  void testGetEntitiesWithInvalidPage() {
    Map<String, String> filterParams = new HashMap<>();
    filterParams.put("direction", "ASC");
    filterParams.put("page", "-1");
    filterParams.put("size", "10");
    Sort sort = Sort.by(ASC, "id");
    Pageable pageable = PageRequest.of(0, 10, sort);
    Comment entity = new Comment(
        1L,
        "Text",
        1L,
        1L
    );

    List<Comment> expectedList = List.of(entity);
    Page<Comment> entityPage = new PageImpl<>(expectedList);

    Specification<Comment> specification = ArgumentMatchers.any();
    when(commentRepository.findAll(specification, eq(pageable))).thenReturn(entityPage);

    List<Comment> result = underTest.getEntities(filterParams);

    assertEquals(expectedList, result);
    verifyNoMoreInteractions(commentRepository);
  }

  @Test
  void testGetEntitiesWithInvalidSize() {
    Map<String, String> filterParams = new HashMap<>();
    filterParams.put("direction", "ASC");
    filterParams.put("page", "1");
    filterParams.put("size", "-1");
    Sort sort = Sort.by(ASC, "id");
    Pageable pageable = PageRequest.of(0, 10, sort);
    Comment entity = new Comment(
        1L,
        "Text",
        1L,
        1L
    );

    List<Comment> expectedList = List.of(entity);
    Page<Comment> entityPage = new PageImpl<>(expectedList);

    Specification<Comment> specification = ArgumentMatchers.any();
    when(commentRepository.findAll(specification, eq(pageable))).thenReturn(entityPage);

    List<Comment> result = underTest.getEntities(filterParams);

    assertEquals(expectedList, result);
    verifyNoMoreInteractions(commentRepository);
  }

  @Test
  void testGetEntitiesWithNotExistingField() {
    Map<String, String> filterParams = new HashMap<>();
    filterParams.put("notExistingField", "*");
    Sort sort = Sort.by(ASC, "id");
    Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE, sort);
    Comment entity = new Comment(
        1L,
        "Text",
        1L,
        1L
    );

    List<Comment> expectedList = List.of(entity);
    Page<Comment> entityPage = new PageImpl<>(expectedList);

    Specification<Comment> specification = ArgumentMatchers.any();
    when(commentRepository.findAll(specification, eq(pageable))).thenReturn(entityPage);

    List<Comment> result = underTest.getEntities(filterParams);

    assertEquals(expectedList, result);
    verifyNoMoreInteractions(commentRepository);
  }
}
