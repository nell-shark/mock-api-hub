package com.nellshark.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.data.domain.Sort.Direction.ASC;

import com.nellshark.models.Event;
import com.nellshark.models.Speaker;
import com.nellshark.repositories.EventRepository;
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
public class EventServiceTest {

  @Mock
  private EventRepository eventRepository;

  private EventService underTest;

  @BeforeEach
  void setUp() {
    underTest = new EventService(eventRepository);
  }

  @Test
  void testGetEntityById() {
    Speaker speaker = new Speaker("Name", "Topic", "bio");
    Event entity = new Event(
        1L,
        "Title",
        LocalDate.now(),
        "Location",
        "Description",
        List.of(speaker)
    );

    when(eventRepository.findById(eq(entity.getId()))).thenReturn(Optional.of(entity));

    Event result = underTest.getEntityById(entity.getId());

    assertEquals(entity, result);
    verify(eventRepository).findById(eq(entity.getId()));
    verifyNoMoreInteractions(eventRepository);
  }

  @Test
  void testGetEntityByIdNotFound() {
    Speaker speaker = new Speaker("Name", "Topic", "bio");
    Event entity = new Event(
        1L,
        "Title",
        LocalDate.now(),
        "Location",
        "Description",
        List.of(speaker)
    );

    when(eventRepository.findById(eq(entity.getId()))).thenReturn(Optional.empty());

    assertThrows(EntityNotFoundException.class, () -> underTest.getEntityById(entity.getId()));

    verify(eventRepository).findById(eq(entity.getId()));
    verifyNoMoreInteractions(eventRepository);
  }

  @Test
  void testGetEntitiesWithDefaultParams() {
    Map<String, String> filterParams = new HashMap<>();
    Sort sort = Sort.by(ASC, "id");
    Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE, sort);
    Speaker speaker = new Speaker("Name", "Topic", "bio");
    Event entity = new Event(
        1L,
        "Title",
        LocalDate.now(),
        "Location",
        "Description",
        List.of(speaker)
    );

    List<Event> expectedList = List.of(entity);
    Page<Event> entityPage = new PageImpl<>(expectedList);

    Specification<Event> specification = ArgumentMatchers.any();
    when(eventRepository.findAll(specification, eq(pageable))).thenReturn(entityPage);

    List<Event> result = underTest.getEntities(filterParams);

    assertEquals(expectedList, result);
    verifyNoMoreInteractions(eventRepository);
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
    Speaker speaker = new Speaker("Name", "Topic", "bio");
    Event entity = new Event(
        1L,
        "Title",
        LocalDate.now(),
        "Location",
        "Description",
        List.of(speaker)
    );

    List<Event> expectedList = List.of(entity);
    Page<Event> entityPage = new PageImpl<>(expectedList);

    Specification<Event> specification = ArgumentMatchers.any();
    when(eventRepository.findAll(specification, eq(pageable))).thenReturn(entityPage);

    List<Event> result = underTest.getEntities(filterParams);

    assertEquals(expectedList, result);
    verifyNoMoreInteractions(eventRepository);
  }

  @Test
  void testGetEntitiesWhenPageIsEmpty() {
    Map<String, String> filterParams = new HashMap<>();
    filterParams.put("direction", "ASC");
    filterParams.put("sort", "id");
    filterParams.put("size", "10");
    Sort sort = Sort.by(ASC, "id");
    Pageable pageable = PageRequest.of(0, 10, sort);
    Speaker speaker = new Speaker("Name", "Topic", "bio");
    Event entity = new Event(
        1L,
        "Title",
        LocalDate.now(),
        "Location",
        "Description",
        List.of(speaker)
    );

    List<Event> expectedList = List.of(entity);
    Page<Event> entityPage = new PageImpl<>(expectedList);

    Specification<Event> specification = ArgumentMatchers.any();
    when(eventRepository.findAll(specification, eq(pageable))).thenReturn(entityPage);

    List<Event> result = underTest.getEntities(filterParams);

    assertEquals(expectedList, result);
    verifyNoMoreInteractions(eventRepository);
  }

  @Test
  void testGetEntitiesWhenSizeIsEmpty() {
    Map<String, String> filterParams = new HashMap<>();
    filterParams.put("direction", "ASC");
    filterParams.put("sort", "id");
    filterParams.put("page", "10");
    Sort sort = Sort.by(ASC, "id");
    Pageable pageable = PageRequest.of(9, 10, sort);
    Speaker speaker = new Speaker("Name", "Topic", "bio");
    Event entity = new Event(
        1L,
        "Title",
        LocalDate.now(),
        "Location",
        "Description",
        List.of(speaker)
    );

    List<Event> expectedList = List.of(entity);
    Page<Event> entitPage = new PageImpl<>(expectedList);

    Specification<Event> specification = ArgumentMatchers.any();
    when(eventRepository.findAll(specification, eq(pageable))).thenReturn(entitPage);

    List<Event> result = underTest.getEntities(filterParams);

    assertEquals(expectedList, result);
    verifyNoMoreInteractions(eventRepository);
  }

  @Test
  void testGetEntitiesWithInvalidPage() {
    Map<String, String> filterParams = new HashMap<>();
    filterParams.put("direction", "ASC");
    filterParams.put("page", "-1");
    filterParams.put("size", "10");
    Sort sort = Sort.by(ASC, "id");
    Pageable pageable = PageRequest.of(0, 10, sort);
    Speaker speaker = new Speaker("Name", "Topic", "bio");
    Event entity = new Event(
        1L,
        "Title",
        LocalDate.now(),
        "Location",
        "Description",
        List.of(speaker)
    );

    List<Event> expectedList = List.of(entity);
    Page<Event> entityPage = new PageImpl<>(expectedList);

    Specification<Event> specification = ArgumentMatchers.any();
    when(eventRepository.findAll(specification, eq(pageable))).thenReturn(entityPage);

    List<Event> result = underTest.getEntities(filterParams);

    assertEquals(expectedList, result);
    verifyNoMoreInteractions(eventRepository);
  }

  @Test
  void testGetEntitiesWithInvalidSize() {
    Map<String, String> filterParams = new HashMap<>();
    filterParams.put("direction", "ASC");
    filterParams.put("page", "1");
    filterParams.put("size", "-1");
    Sort sort = Sort.by(ASC, "id");
    Pageable pageable = PageRequest.of(0, 10, sort);
    Speaker speaker = new Speaker("Name", "Topic", "bio");
    Event entity = new Event(
        1L,
        "Title",
        LocalDate.now(),
        "Location",
        "Description",
        List.of(speaker)
    );

    List<Event> expectedList = List.of(entity);
    Page<Event> entityPage = new PageImpl<>(expectedList);

    Specification<Event> specification = ArgumentMatchers.any();
    when(eventRepository.findAll(specification, eq(pageable))).thenReturn(entityPage);

    List<Event> result = underTest.getEntities(filterParams);

    assertEquals(expectedList, result);
    verifyNoMoreInteractions(eventRepository);
  }

  @Test
  void testGetEntitiesWithNotExistingField() {
    Map<String, String> filterParams = new HashMap<>();
    filterParams.put("notExistingField", "*");
    Sort sort = Sort.by(ASC, "id");
    Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE, sort);
    Speaker speaker = new Speaker("Name", "Topic", "bio");
    Event entity = new Event(
        1L,
        "Title",
        LocalDate.now(),
        "Location",
        "Description",
        List.of(speaker)
    );

    List<Event> expectedList = List.of(entity);
    Page<Event> entityPage = new PageImpl<>(expectedList);

    Specification<Event> specification = ArgumentMatchers.any();
    when(eventRepository.findAll(specification, eq(pageable))).thenReturn(entityPage);

    List<Event> result = underTest.getEntities(filterParams);

    assertEquals(expectedList, result);
    verifyNoMoreInteractions(eventRepository);
  }

  @Test
  void testGetSpeakersByEventId() {
    Speaker speaker = new Speaker("Name", "Topic", "bio");
    List<Speaker> speakers = List.of(speaker);
    Event entity = new Event(
        1L,
        "Title",
        LocalDate.now(),
        "Location",
        "Description",
        speakers
    );

    when(eventRepository.findById(eq(entity.getId()))).thenReturn(Optional.of(entity));

    List<Speaker> result = underTest.getSpeakersByEventId(entity.getId());

    assertEquals(speakers, result);
    verify(eventRepository).findById(eq(entity.getId()));
    verifyNoMoreInteractions(eventRepository);
  }
}
