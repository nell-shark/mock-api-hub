package com.nellshark.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.data.domain.Sort.Direction.ASC;

import com.nellshark.models.Comment;
import com.nellshark.models.Post;
import com.nellshark.repositories.PostRepository;
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
public class PostServiceTest {

  @Mock
  private PostRepository postRepository;

  @Mock
  private CommentService commentService;

  private PostService underTest;

  @BeforeEach
  void setUp() {
    underTest = new PostService(postRepository, commentService);
  }

  @Test
  void testGetEntityById() {
    Post entity = new Post(
        1L,
        "Title",
        "Content",
        LocalDateTime.now(),
        1L
    );

    when(postRepository.findById(eq(entity.getId()))).thenReturn(Optional.of(entity));

    Post result = underTest.getEntityById(entity.getId());

    assertEquals(entity, result);
    verify(postRepository).findById(eq(entity.getId()));
    verifyNoMoreInteractions(commentService);
  }

  @Test
  void testGetEntityByIdNotFound() {
    Post entity = new Post(
        1L,
        "Title",
        "Content",
        LocalDateTime.now(),
        1L
    );

    when(postRepository.findById(eq(entity.getId()))).thenReturn(Optional.empty());

    assertThrows(EntityNotFoundException.class, () -> underTest.getEntityById(entity.getId()));

    verify(postRepository).findById(eq(entity.getId()));
    verifyNoMoreInteractions(commentService);
  }

  @Test
  void testGetEntitiesWithDefaultParams() {
    Map<String, String> filterParams = new HashMap<>();
    Sort sort = Sort.by(ASC, "id");
    Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE, sort);
    Post entity = new Post(
        1L,
        "Title",
        "Content",
        LocalDateTime.now(),
        1L
    );

    List<Post> expectedList = List.of(entity);
    Page<Post> entityPage = new PageImpl<>(expectedList);

    Specification<Post> specification = ArgumentMatchers.any();
    when(postRepository.findAll(specification, eq(pageable))).thenReturn(entityPage);

    List<Post> result = underTest.getEntities(filterParams);

    assertEquals(expectedList, result);
    verifyNoMoreInteractions(commentService);
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
    Post entity = new Post(
        1L,
        "Title",
        "Content",
        LocalDateTime.now(),
        1L
    );

    List<Post> expectedList = List.of(entity);
    Page<Post> entityPage = new PageImpl<>(expectedList);

    Specification<Post> specification = ArgumentMatchers.any();
    when(postRepository.findAll(specification, eq(pageable))).thenReturn(entityPage);

    List<Post> result = underTest.getEntities(filterParams);

    assertEquals(expectedList, result);
    verifyNoMoreInteractions(commentService);
  }

  @Test
  void testGetEntitiesWhenPageIsEmpty() {
    Map<String, String> filterParams = new HashMap<>();
    filterParams.put("direction", "ASC");
    filterParams.put("sort", "id");
    filterParams.put("size", "10");
    Sort sort = Sort.by(ASC, "id");
    Pageable pageable = PageRequest.of(0, 10, sort);
    Post entity = new Post(
        1L,
        "Title",
        "Content",
        LocalDateTime.now(),
        1L
    );

    List<Post> expectedList = List.of(entity);
    Page<Post> entityPage = new PageImpl<>(expectedList);

    Specification<Post> specification = ArgumentMatchers.any();
    when(postRepository.findAll(specification, eq(pageable))).thenReturn(entityPage);

    List<Post> result = underTest.getEntities(filterParams);

    assertEquals(expectedList, result);
    verifyNoMoreInteractions(commentService);
  }

  @Test
  void testGetEntitiesWhenSizeIsEmpty() {
    Map<String, String> filterParams = new HashMap<>();
    filterParams.put("direction", "ASC");
    filterParams.put("sort", "id");
    filterParams.put("page", "10");
    Sort sort = Sort.by(ASC, "id");
    Pageable pageable = PageRequest.of(9, 10, sort);
    Post entity = new Post(
        1L,
        "Title",
        "Content",
        LocalDateTime.now(),
        1L
    );

    List<Post> expectedList = List.of(entity);
    Page<Post> entitPage = new PageImpl<>(expectedList);

    Specification<Post> specification = ArgumentMatchers.any();
    when(postRepository.findAll(specification, eq(pageable))).thenReturn(entitPage);

    List<Post> result = underTest.getEntities(filterParams);

    assertEquals(expectedList, result);
    verifyNoMoreInteractions(commentService);
  }

  @Test
  void testGetEntitiesWithInvalidPage() {
    Map<String, String> filterParams = new HashMap<>();
    filterParams.put("direction", "ASC");
    filterParams.put("page", "-1");
    filterParams.put("size", "10");
    Sort sort = Sort.by(ASC, "id");
    Pageable pageable = PageRequest.of(0, 10, sort);
    Post entity = new Post(
        1L,
        "Title",
        "Content",
        LocalDateTime.now(),
        1L
    );

    List<Post> expectedList = List.of(entity);
    Page<Post> entityPage = new PageImpl<>(expectedList);

    Specification<Post> specification = ArgumentMatchers.any();
    when(postRepository.findAll(specification, eq(pageable))).thenReturn(entityPage);

    List<Post> result = underTest.getEntities(filterParams);

    assertEquals(expectedList, result);
    verifyNoMoreInteractions(commentService);
  }

  @Test
  void testGetEntitiesWithInvalidSize() {
    Map<String, String> filterParams = new HashMap<>();
    filterParams.put("direction", "ASC");
    filterParams.put("page", "1");
    filterParams.put("size", "-1");
    Sort sort = Sort.by(ASC, "id");
    Pageable pageable = PageRequest.of(0, 10, sort);
    Post entity = new Post(
        1L,
        "Title",
        "Content",
        LocalDateTime.now(),
        1L
    );

    List<Post> expectedList = List.of(entity);
    Page<Post> entityPage = new PageImpl<>(expectedList);

    Specification<Post> specification = ArgumentMatchers.any();
    when(postRepository.findAll(specification, eq(pageable))).thenReturn(entityPage);

    List<Post> result = underTest.getEntities(filterParams);

    assertEquals(expectedList, result);
    verifyNoMoreInteractions(commentService);
  }

  @Test
  void testGetEntitiesWithNotExistingField() {
    Map<String, String> filterParams = new HashMap<>();
    filterParams.put("notExistingField", "*");
    Sort sort = Sort.by(ASC, "id");
    Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE, sort);
    Post entity = new Post(
        1L,
        "Title",
        "Content",
        LocalDateTime.now(),
        1L
    );

    List<Post> expectedList = List.of(entity);
    Page<Post> entityPage = new PageImpl<>(expectedList);

    Specification<Post> specification = ArgumentMatchers.any();
    when(postRepository.findAll(specification, eq(pageable))).thenReturn(entityPage);

    List<Post> result = underTest.getEntities(filterParams);

    assertEquals(expectedList, result);
    verifyNoMoreInteractions(commentService);
  }

  @Test
  void testGetCommentsByPostId() {
    Post entity = new Post(
        1L,
        "Title",
        "Content",
        LocalDateTime.now(),
        1L
    );
    Comment comment = new Comment(1L, "Text", 1L, 1L);
    List<Comment> comments = List.of(comment);
    Map<String, String> map = new HashMap<>();

    when(commentService.getEntities(eq(map))).thenReturn(comments);

    List<Comment> result = underTest.getCommentsByPostId(entity.getId(), map);

    assertEquals(comments, result);
    verify(commentService).getEntities(eq(map));
    verifyNoMoreInteractions(commentService);
  }
}
