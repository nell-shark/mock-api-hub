package com.nellshark.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.data.domain.Sort.Direction.ASC;

import com.nellshark.models.Comment;
import com.nellshark.models.Review;
import com.nellshark.models.User;
import com.nellshark.repositories.UserRepository;
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
public class UserServiceTest {

  @Mock
  private UserRepository userRepository;

  @Mock
  private CommentService commentService;

  @Mock
  private ReviewService reviewService;

  private UserService underTest;

  @BeforeEach
  void setUp() {
    underTest = new UserService(userRepository, commentService, reviewService);
  }

  @Test
  void testGetEntityById() {
    User entity = new User(
        1L,
        "Username",
        "email@google.com",
        "password",
        18L,
        "Location"
    );
    when(userRepository.findById(eq(entity.getId()))).thenReturn(Optional.of(entity));

    User result = underTest.getEntityById(entity.getId());

    assertEquals(entity, result);
    verify(userRepository).findById(eq(entity.getId()));
    verifyNoMoreInteractions(userRepository);
  }

  @Test
  void testGetEntityByIdNotFound() {
    User entity = new User(
        1L,
        "Username",
        "email@google.com",
        "password",
        18L,
        "Location"
    );
    when(userRepository.findById(eq(entity.getId()))).thenReturn(Optional.empty());

    assertThrows(EntityNotFoundException.class, () -> underTest.getEntityById(entity.getId()));

    verify(userRepository).findById(eq(entity.getId()));
    verifyNoMoreInteractions(userRepository);
  }

  @Test
  void testGetEntitiesWithDefaultParams() {
    Map<String, String> filterParams = new HashMap<>();
    Sort sort = Sort.by(ASC, "id");
    Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE, sort);
    User entity = new User(
        1L,
        "Username",
        "email@google.com",
        "password",
        18L,
        "Location"
    );
    List<User> expectedList = List.of(entity);
    Page<User> entityPage = new PageImpl<>(expectedList);

    Specification<User> specification = ArgumentMatchers.any();
    when(userRepository.findAll(specification, eq(pageable))).thenReturn(entityPage);

    List<User> result = underTest.getEntities(filterParams);

    assertEquals(expectedList, result);
    verifyNoMoreInteractions(userRepository);
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
    User entity = new User(
        1L,
        "Username",
        "email@google.com",
        "password",
        18L,
        "Location"
    );
    List<User> expectedList = List.of(entity);
    Page<User> entityPage = new PageImpl<>(expectedList);

    Specification<User> specification = ArgumentMatchers.any();
    when(userRepository.findAll(specification, eq(pageable))).thenReturn(entityPage);

    List<User> result = underTest.getEntities(filterParams);

    assertEquals(expectedList, result);
    verifyNoMoreInteractions(userRepository);
  }

  @Test
  void testGetEntitiesWhenPageIsEmpty() {
    Map<String, String> filterParams = new HashMap<>();
    filterParams.put("direction", "ASC");
    filterParams.put("sort", "id");
    filterParams.put("size", "10");
    Sort sort = Sort.by(ASC, "id");
    Pageable pageable = PageRequest.of(0, 10, sort);
    User entity = new User(
        1L,
        "Username",
        "email@google.com",
        "password",
        18L,
        "Location"
    );
    List<User> expectedList = List.of(entity);
    Page<User> entityPage = new PageImpl<>(expectedList);

    Specification<User> specification = ArgumentMatchers.any();
    when(userRepository.findAll(specification, eq(pageable))).thenReturn(entityPage);

    List<User> result = underTest.getEntities(filterParams);

    assertEquals(expectedList, result);
    verifyNoMoreInteractions(userRepository);
  }

  @Test
  void testGetEntitiesWhenSizeIsEmpty() {
    Map<String, String> filterParams = new HashMap<>();
    filterParams.put("direction", "ASC");
    filterParams.put("sort", "id");
    filterParams.put("page", "10");
    Sort sort = Sort.by(ASC, "id");
    Pageable pageable = PageRequest.of(9, 10, sort);
    User entity = new User(
        1L,
        "Username",
        "email@google.com",
        "password",
        18L,
        "Location"
    );
    List<User> expectedList = List.of(entity);
    Page<User> entitPage = new PageImpl<>(expectedList);

    Specification<User> specification = ArgumentMatchers.any();
    when(userRepository.findAll(specification, eq(pageable))).thenReturn(entitPage);

    List<User> result = underTest.getEntities(filterParams);

    assertEquals(expectedList, result);
    verifyNoMoreInteractions(userRepository);
  }

  @Test
  void testGetEntitiesWithInvalidPage() {
    Map<String, String> filterParams = new HashMap<>();
    filterParams.put("direction", "ASC");
    filterParams.put("page", "-1");
    filterParams.put("size", "10");
    Sort sort = Sort.by(ASC, "id");
    Pageable pageable = PageRequest.of(0, 10, sort);
    User entity = new User(
        1L,
        "Username",
        "email@google.com",
        "password",
        18L,
        "Location"
    );
    List<User> expectedList = List.of(entity);
    Page<User> entityPage = new PageImpl<>(expectedList);

    Specification<User> specification = ArgumentMatchers.any();
    when(userRepository.findAll(specification, eq(pageable))).thenReturn(entityPage);

    List<User> result = underTest.getEntities(filterParams);

    assertEquals(expectedList, result);
    verifyNoMoreInteractions(userRepository);
  }

  @Test
  void testGetEntitiesWithInvalidSize() {
    Map<String, String> filterParams = new HashMap<>();
    filterParams.put("direction", "ASC");
    filterParams.put("page", "1");
    filterParams.put("size", "-1");
    Sort sort = Sort.by(ASC, "id");
    Pageable pageable = PageRequest.of(0, 10, sort);
    User entity = new User(
        1L,
        "Username",
        "email@google.com",
        "password",
        18L,
        "Location"
    );
    List<User> expectedList = List.of(entity);
    Page<User> entityPage = new PageImpl<>(expectedList);

    Specification<User> specification = ArgumentMatchers.any();
    when(userRepository.findAll(specification, eq(pageable))).thenReturn(entityPage);

    List<User> result = underTest.getEntities(filterParams);

    assertEquals(expectedList, result);
    verifyNoMoreInteractions(userRepository);
  }

  @Test
  void testGetEntitiesWithNotExistingField() {
    Map<String, String> filterParams = new HashMap<>();
    filterParams.put("notExistingField", "*");
    Sort sort = Sort.by(ASC, "id");
    Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE, sort);
    User entity = new User(
        1L,
        "Username",
        "email@google.com",
        "password",
        18L,
        "Location"
    );
    List<User> expectedList = List.of(entity);
    Page<User> entityPage = new PageImpl<>(expectedList);

    Specification<User> specification = ArgumentMatchers.any();
    when(userRepository.findAll(specification, eq(pageable))).thenReturn(entityPage);

    List<User> result = underTest.getEntities(filterParams);

    assertEquals(expectedList, result);
    verifyNoMoreInteractions(userRepository);
  }

  @Test
  void testGetCommentsByUserId() {
    Comment comment = new Comment(1L, "Text", 1L, 1L);
    List<Comment> comments = List.of(comment);
    User entity = new User(
        1L,
        "Username",
        "email@google.com",
        "password",
        18L,
        "Location"
    );
    Map<String, String> map = new HashMap<>();
    when(commentService.getEntities(eq(map))).thenReturn(comments);

    List<Comment> result = underTest.getCommentsByUserId(entity.getId(), map);

    assertEquals(comments, result);
    verify(commentService).getEntities(eq(map));
    verifyNoMoreInteractions(userRepository);
  }

  @Test
  void testGetReviewsByUserId() {
    Review review = new Review(1L, 1L, 1L, 1L, LocalDate.now(), "Body", 1L);
    List<Review> reviews = List.of(review);
    User entity = new User(
        1L,
        "Username",
        "email@google.com",
        "password",
        18L,
        "Location"
    );
    Map<String, String> map = new HashMap<>();
    when(reviewService.getEntities(eq(map))).thenReturn(reviews);

    List<Review> result = underTest.getReviewsByUserId(entity.getId(), map);

    assertEquals(reviews, result);
    verify(reviewService).getEntities(eq(map));
    verifyNoMoreInteractions(userRepository);
  }
}
