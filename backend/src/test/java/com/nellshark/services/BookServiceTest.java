package com.nellshark.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.data.domain.Sort.Direction.ASC;

import com.nellshark.models.Book;
import com.nellshark.repositories.BookRepository;
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
public class BookServiceTest {

  @Mock
  private BookRepository bookRepository;

  private BookService underTest;

  @BeforeEach
  void setUp() {
    underTest = new BookService(bookRepository);
  }

  @Test
  void testGetEntityById() {
    Book entity = new Book(
        1L,
        "Title",
        1L,
        "Author",
        1932L,
        "Language",
        "Description"
    );

    when(bookRepository.findById(eq(entity.getId()))).thenReturn(Optional.of(entity));

    Book result = underTest.getEntityById(entity.getId());

    assertEquals(entity, result);
    verify(bookRepository).findById(eq(entity.getId()));
    verifyNoMoreInteractions(bookRepository);
  }

  @Test
  void testGetEntityByIdNotFound() {
    Book entity = new Book(
        1L,
        "Title",
        1L,
        "Author",
        1932L,
        "Language",
        "Description"
    );

    when(bookRepository.findById(eq(entity.getId()))).thenReturn(Optional.empty());

    assertThrows(EntityNotFoundException.class, () -> underTest.getEntityById(entity.getId()));

    verify(bookRepository).findById(eq(entity.getId()));
    verifyNoMoreInteractions(bookRepository);
  }

  @Test
  void testGetEntitiesWithDefaultParams() {
    Map<String, String> filterParams = new HashMap<>();
    Sort sort = Sort.by(ASC, "id");
    Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE, sort);
    Book entity = new Book(
        1L,
        "Title",
        1L,
        "Author",
        1932L,
        "Language",
        "Description"
    );

    List<Book> expectedList = List.of(entity);
    Page<Book> entityPage = new PageImpl<>(expectedList);

    Specification<Book> specification = ArgumentMatchers.any();
    when(bookRepository.findAll(specification, eq(pageable))).thenReturn(entityPage);

    List<Book> result = underTest.getEntities(filterParams);

    assertEquals(expectedList, result);
    verifyNoMoreInteractions(bookRepository);
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
    Book entity = new Book(
        1L,
        "Title",
        1L,
        "Author",
        1932L,
        "Language",
        "Description"
    );

    List<Book> expectedList = List.of(entity);
    Page<Book> entityPage = new PageImpl<>(expectedList);

    Specification<Book> specification = ArgumentMatchers.any();
    when(bookRepository.findAll(specification, eq(pageable))).thenReturn(entityPage);

    List<Book> result = underTest.getEntities(filterParams);

    assertEquals(expectedList, result);
    verifyNoMoreInteractions(bookRepository);
  }

  @Test
  void testGetEntitiesWhenPageIsEmpty() {
    Map<String, String> filterParams = new HashMap<>();
    filterParams.put("direction", "ASC");
    filterParams.put("sort", "id");
    filterParams.put("size", "10");
    Sort sort = Sort.by(ASC, "id");
    Pageable pageable = PageRequest.of(0, 10, sort);
    Book entity = new Book(
        1L,
        "Title",
        1L,
        "Author",
        1932L,
        "Language",
        "Description"
    );

    List<Book> expectedList = List.of(entity);
    Page<Book> entityPage = new PageImpl<>(expectedList);

    Specification<Book> specification = ArgumentMatchers.any();
    when(bookRepository.findAll(specification, eq(pageable))).thenReturn(entityPage);

    List<Book> result = underTest.getEntities(filterParams);

    assertEquals(expectedList, result);
    verifyNoMoreInteractions(bookRepository);
  }

  @Test
  void testGetEntitiesWhenSizeIsEmpty() {
    Map<String, String> filterParams = new HashMap<>();
    filterParams.put("direction", "ASC");
    filterParams.put("sort", "id");
    filterParams.put("page", "10");
    Sort sort = Sort.by(ASC, "id");
    Pageable pageable = PageRequest.of(9, 10, sort);
    Book entity = new Book(
        1L,
        "Title",
        1L,
        "Author",
        1932L,
        "Language",
        "Description"
    );

    List<Book> expectedList = List.of(entity);
    Page<Book> entitPage = new PageImpl<>(expectedList);

    Specification<Book> specification = ArgumentMatchers.any();
    when(bookRepository.findAll(specification, eq(pageable))).thenReturn(entitPage);

    List<Book> result = underTest.getEntities(filterParams);

    assertEquals(expectedList, result);
    verifyNoMoreInteractions(bookRepository);
  }

  @Test
  void testGetEntitiesWithInvalidPage() {
    Map<String, String> filterParams = new HashMap<>();
    filterParams.put("direction", "ASC");
    filterParams.put("page", "-1");
    filterParams.put("size", "10");
    Sort sort = Sort.by(ASC, "id");
    Pageable pageable = PageRequest.of(0, 10, sort);
    Book entity = new Book(
        1L,
        "Title",
        1L,
        "Author",
        1932L,
        "Language",
        "Description"
    );

    List<Book> expectedList = List.of(entity);
    Page<Book> entityPage = new PageImpl<>(expectedList);

    Specification<Book> specification = ArgumentMatchers.any();
    when(bookRepository.findAll(specification, eq(pageable))).thenReturn(entityPage);

    List<Book> result = underTest.getEntities(filterParams);

    assertEquals(expectedList, result);
    verifyNoMoreInteractions(bookRepository);
  }

  @Test
  void testGetEntitiesWithInvalidSize() {
    Map<String, String> filterParams = new HashMap<>();
    filterParams.put("direction", "ASC");
    filterParams.put("page", "1");
    filterParams.put("size", "-1");
    Sort sort = Sort.by(ASC, "id");
    Pageable pageable = PageRequest.of(0, 10, sort);
    Book entity = new Book(
        1L,
        "Title",
        1L,
        "Author",
        1932L,
        "Language",
        "Description"
    );

    List<Book> expectedList = List.of(entity);
    Page<Book> entityPage = new PageImpl<>(expectedList);

    Specification<Book> specification = ArgumentMatchers.any();
    when(bookRepository.findAll(specification, eq(pageable))).thenReturn(entityPage);

    List<Book> result = underTest.getEntities(filterParams);

    assertEquals(expectedList, result);
    verifyNoMoreInteractions(bookRepository);
  }

  @Test
  void testGetEntitiesWithNotExistingField() {
    Map<String, String> filterParams = new HashMap<>();
    filterParams.put("notExistingField", "*");
    Sort sort = Sort.by(ASC, "id");
    Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE, sort);
    Book entity = new Book(
        1L,
        "Title",
        1L,
        "Author",
        1932L,
        "Language",
        "Description"
    );

    List<Book> expectedList = List.of(entity);
    Page<Book> entityPage = new PageImpl<>(expectedList);

    Specification<Book> specification = ArgumentMatchers.any();
    when(bookRepository.findAll(specification, eq(pageable))).thenReturn(entityPage);

    List<Book> result = underTest.getEntities(filterParams);

    assertEquals(expectedList, result);
    verifyNoMoreInteractions(bookRepository);
  }
}
