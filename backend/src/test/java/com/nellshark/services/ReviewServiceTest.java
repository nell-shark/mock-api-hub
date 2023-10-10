package com.nellshark.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.data.domain.Sort.Direction.ASC;

import com.nellshark.models.Review;
import com.nellshark.repositories.ReviewRepository;
import jakarta.persistence.EntityNotFoundException;
import java.time.LocalDate;
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
public class ReviewServiceTest {

  @Mock
  private ReviewRepository reviewRepository;

  private ReviewService underTest;

  @BeforeEach
  void setUp() {
    underTest = new ReviewService(reviewRepository);
  }

  @Test
  void testGetEntityById() {
    Review entity = new Review(1L, 1L, 1L, 1L, LocalDate.now(), "Body", 1L);

    when(reviewRepository.findById(eq(entity.getId()))).thenReturn(Optional.of(entity));

    Review result = underTest.getEntityById(entity.getId());

    assertEquals(entity, result);
    verify(reviewRepository).findById(eq(entity.getId()));
    verifyNoMoreInteractions(reviewRepository);
  }

  @Test
  void testGetEntityByIdNotFound() {
    Review entity = new Review(1L, 1L, 1L, 1L, LocalDate.now(), "Body", 1L);

    when(reviewRepository.findById(eq(entity.getId()))).thenReturn(Optional.empty());

    assertThrows(EntityNotFoundException.class, () -> underTest.getEntityById(entity.getId()));

    verify(reviewRepository).findById(eq(entity.getId()));
    verifyNoMoreInteractions(reviewRepository);
  }

  @Test
  void testGetEntitiesWithDefaultParams() {
    Map<String, String> filterParams = new HashMap<>();
    Sort sort = Sort.by(ASC, "id");
    Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE, sort);
    Review entity = new Review(1L, 1L, 1L, 1L, LocalDate.now(), "Body", 1L);

    List<Review> expectedList = List.of(entity);
    Page<Review> entityPage = new PageImpl<>(expectedList);

    Specification<Review> specification = ArgumentMatchers.any();
    when(reviewRepository.findAll(specification, eq(pageable))).thenReturn(entityPage);

    List<Review> result = underTest.getEntities(filterParams);

    assertEquals(expectedList, result);
    verifyNoMoreInteractions(reviewRepository);
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
    Review entity = new Review(1L, 1L, 1L, 1L, LocalDate.now(), "Body", 1L);

    List<Review> expectedList = List.of(entity);
    Page<Review> entityPage = new PageImpl<>(expectedList);

    Specification<Review> specification = ArgumentMatchers.any();
    when(reviewRepository.findAll(specification, eq(pageable))).thenReturn(entityPage);

    List<Review> result = underTest.getEntities(filterParams);

    assertEquals(expectedList, result);
    verifyNoMoreInteractions(reviewRepository);
  }

  @Test
  void testGetEntitiesWhenPageIsEmpty() {
    Map<String, String> filterParams = new HashMap<>();
    filterParams.put("direction", "ASC");
    filterParams.put("sort", "id");
    filterParams.put("size", "10");
    Sort sort = Sort.by(ASC, "id");
    Pageable pageable = PageRequest.of(0, 10, sort);
    Review entity = new Review(1L, 1L, 1L, 1L, LocalDate.now(), "Body", 1L);

    List<Review> expectedList = List.of(entity);
    Page<Review> entityPage = new PageImpl<>(expectedList);

    Specification<Review> specification = ArgumentMatchers.any();
    when(reviewRepository.findAll(specification, eq(pageable))).thenReturn(entityPage);

    List<Review> result = underTest.getEntities(filterParams);

    assertEquals(expectedList, result);
    verifyNoMoreInteractions(reviewRepository);
  }

  @Test
  void testGetEntitiesWhenSizeIsEmpty() {
    Map<String, String> filterParams = new HashMap<>();
    filterParams.put("direction", "ASC");
    filterParams.put("sort", "id");
    filterParams.put("page", "10");
    Sort sort = Sort.by(ASC, "id");
    Pageable pageable = PageRequest.of(9, 10, sort);
    Review entity = new Review(1L, 1L, 1L, 1L, LocalDate.now(), "Body", 1L);

    List<Review> expectedList = List.of(entity);
    Page<Review> entitPage = new PageImpl<>(expectedList);

    Specification<Review> specification = ArgumentMatchers.any();
    when(reviewRepository.findAll(specification, eq(pageable))).thenReturn(entitPage);

    List<Review> result = underTest.getEntities(filterParams);

    assertEquals(expectedList, result);
    verifyNoMoreInteractions(reviewRepository);
  }

  @Test
  void testGetEntitiesWithInvalidPage() {
    Map<String, String> filterParams = new HashMap<>();
    filterParams.put("direction", "ASC");
    filterParams.put("page", "-1");
    filterParams.put("size", "10");
    Sort sort = Sort.by(ASC, "id");
    Pageable pageable = PageRequest.of(0, 10, sort);
    Review entity = new Review(1L, 1L, 1L, 1L, LocalDate.now(), "Body", 1L);

    List<Review> expectedList = List.of(entity);
    Page<Review> entityPage = new PageImpl<>(expectedList);

    Specification<Review> specification = ArgumentMatchers.any();
    when(reviewRepository.findAll(specification, eq(pageable))).thenReturn(entityPage);

    List<Review> result = underTest.getEntities(filterParams);

    assertEquals(expectedList, result);
    verifyNoMoreInteractions(reviewRepository);
  }

  @Test
  void testGetEntitiesWithInvalidSize() {
    Map<String, String> filterParams = new HashMap<>();
    filterParams.put("direction", "ASC");
    filterParams.put("page", "1");
    filterParams.put("size", "-1");
    Sort sort = Sort.by(ASC, "id");
    Pageable pageable = PageRequest.of(0, 10, sort);
    Review entity = new Review(1L, 1L, 1L, 1L, LocalDate.now(), "Body", 1L);

    List<Review> expectedList = List.of(entity);
    Page<Review> entityPage = new PageImpl<>(expectedList);

    Specification<Review> specification = ArgumentMatchers.any();
    when(reviewRepository.findAll(specification, eq(pageable))).thenReturn(entityPage);

    List<Review> result = underTest.getEntities(filterParams);

    assertEquals(expectedList, result);
    verifyNoMoreInteractions(reviewRepository);
  }

  @Test
  void testGetEntitiesWithNotExistingField() {
    Map<String, String> filterParams = new HashMap<>();
    filterParams.put("notExistingField", "*");
    Sort sort = Sort.by(ASC, "id");
    Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE, sort);
    Review entity = new Review(1L, 1L, 1L, 1L, LocalDate.now(), "Body", 1L);

    List<Review> expectedList = List.of(entity);
    Page<Review> entityPage = new PageImpl<>(expectedList);

    Specification<Review> specification = ArgumentMatchers.any();
    when(reviewRepository.findAll(specification, eq(pageable))).thenReturn(entityPage);

    List<Review> result = underTest.getEntities(filterParams);

    assertEquals(expectedList, result);
    verifyNoMoreInteractions(reviewRepository);
  }
}
