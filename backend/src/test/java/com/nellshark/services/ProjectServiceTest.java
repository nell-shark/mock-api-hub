package com.nellshark.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.data.domain.Sort.Direction.ASC;

import com.nellshark.models.Project;
import com.nellshark.models.Team;
import com.nellshark.repositories.ProjectRepository;
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
public class ProjectServiceTest {

  @Mock
  private ProjectRepository projectRepository;

  private ProjectService underTest;

  @BeforeEach
  void setUp() {
    underTest = new ProjectService(projectRepository);
  }

  @Test
  void testGetEntityById() {
    Team team = new Team("Name", "Role");
    Project entity = new Project(
        1L,
        "Name",
        "Description",
        "Status",
        LocalDate.now(),
        LocalDate.now(),
        List.of(team)
    );

    when(projectRepository.findById(eq(entity.getId()))).thenReturn(Optional.of(entity));

    Project result = underTest.getEntityById(entity.getId());

    assertEquals(entity, result);
    verify(projectRepository).findById(eq(entity.getId()));
    verifyNoMoreInteractions(projectRepository);
  }

  @Test
  void testGetEntityByIdNotFound() {
    Team team = new Team("Name", "Role");
    Project entity = new Project(
        1L,
        "Name",
        "Description",
        "Status",
        LocalDate.now(),
        LocalDate.now(),
        List.of(team)
    );

    when(projectRepository.findById(eq(entity.getId()))).thenReturn(Optional.empty());

    assertThrows(EntityNotFoundException.class, () -> underTest.getEntityById(entity.getId()));

    verify(projectRepository).findById(eq(entity.getId()));
    verifyNoMoreInteractions(projectRepository);
  }

  @Test
  void testGetEntitiesWithDefaultParams() {
    Map<String, String> filterParams = new HashMap<>();
    Sort sort = Sort.by(ASC, "id");
    Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE, sort);
    Team team = new Team("Name", "Role");
    Project entity = new Project(
        1L,
        "Name",
        "Description",
        "Status",
        LocalDate.now(),
        LocalDate.now(),
        List.of(team)
    );

    List<Project> expectedList = List.of(entity);
    Page<Project> entityPage = new PageImpl<>(expectedList);

    Specification<Project> specification = ArgumentMatchers.any();
    when(projectRepository.findAll(specification, eq(pageable))).thenReturn(entityPage);

    List<Project> result = underTest.getEntities(filterParams);

    assertEquals(expectedList, result);
    verifyNoMoreInteractions(projectRepository);
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
    Team team = new Team("Name", "Role");
    Project entity = new Project(
        1L,
        "Name",
        "Description",
        "Status",
        LocalDate.now(),
        LocalDate.now(),
        List.of(team)
    );

    List<Project> expectedList = List.of(entity);
    Page<Project> entityPage = new PageImpl<>(expectedList);

    Specification<Project> specification = ArgumentMatchers.any();
    when(projectRepository.findAll(specification, eq(pageable))).thenReturn(entityPage);

    List<Project> result = underTest.getEntities(filterParams);

    assertEquals(expectedList, result);
    verifyNoMoreInteractions(projectRepository);
  }

  @Test
  void testGetEntitiesWhenPageIsEmpty() {
    Map<String, String> filterParams = new HashMap<>();
    filterParams.put("direction", "ASC");
    filterParams.put("sort", "id");
    filterParams.put("size", "10");
    Sort sort = Sort.by(ASC, "id");
    Pageable pageable = PageRequest.of(0, 10, sort);
    Team team = new Team("Name", "Role");
    Project entity = new Project(
        1L,
        "Name",
        "Description",
        "Status",
        LocalDate.now(),
        LocalDate.now(),
        List.of(team)
    );

    List<Project> expectedList = List.of(entity);
    Page<Project> entityPage = new PageImpl<>(expectedList);

    Specification<Project> specification = ArgumentMatchers.any();
    when(projectRepository.findAll(specification, eq(pageable))).thenReturn(entityPage);

    List<Project> result = underTest.getEntities(filterParams);

    assertEquals(expectedList, result);
    verifyNoMoreInteractions(projectRepository);
  }

  @Test
  void testGetEntitiesWhenSizeIsEmpty() {
    Map<String, String> filterParams = new HashMap<>();
    filterParams.put("direction", "ASC");
    filterParams.put("sort", "id");
    filterParams.put("page", "10");
    Sort sort = Sort.by(ASC, "id");
    Pageable pageable = PageRequest.of(9, 10, sort);
    Team team = new Team("Name", "Role");
    Project entity = new Project(
        1L,
        "Name",
        "Description",
        "Status",
        LocalDate.now(),
        LocalDate.now(),
        List.of(team)
    );

    List<Project> expectedList = List.of(entity);
    Page<Project> entitPage = new PageImpl<>(expectedList);

    Specification<Project> specification = ArgumentMatchers.any();
    when(projectRepository.findAll(specification, eq(pageable))).thenReturn(entitPage);

    List<Project> result = underTest.getEntities(filterParams);

    assertEquals(expectedList, result);
    verifyNoMoreInteractions(projectRepository);
  }

  @Test
  void testGetEntitiesWithInvalidPage() {
    Map<String, String> filterParams = new HashMap<>();
    filterParams.put("direction", "ASC");
    filterParams.put("page", "-1");
    filterParams.put("size", "10");
    Sort sort = Sort.by(ASC, "id");
    Pageable pageable = PageRequest.of(0, 10, sort);
    Team team = new Team("Name", "Role");
    Project entity = new Project(
        1L,
        "Name",
        "Description",
        "Status",
        LocalDate.now(),
        LocalDate.now(),
        List.of(team)
    );

    List<Project> expectedList = List.of(entity);
    Page<Project> entityPage = new PageImpl<>(expectedList);

    Specification<Project> specification = ArgumentMatchers.any();
    when(projectRepository.findAll(specification, eq(pageable))).thenReturn(entityPage);

    List<Project> result = underTest.getEntities(filterParams);

    assertEquals(expectedList, result);
    verifyNoMoreInteractions(projectRepository);
  }

  @Test
  void testGetEntitiesWithInvalidSize() {
    Map<String, String> filterParams = new HashMap<>();
    filterParams.put("direction", "ASC");
    filterParams.put("page", "1");
    filterParams.put("size", "-1");
    Sort sort = Sort.by(ASC, "id");
    Pageable pageable = PageRequest.of(0, 10, sort);
    Team team = new Team("Name", "Role");
    Project entity = new Project(
        1L,
        "Name",
        "Description",
        "Status",
        LocalDate.now(),
        LocalDate.now(),
        List.of(team)
    );

    List<Project> expectedList = List.of(entity);
    Page<Project> entityPage = new PageImpl<>(expectedList);

    Specification<Project> specification = ArgumentMatchers.any();
    when(projectRepository.findAll(specification, eq(pageable))).thenReturn(entityPage);

    List<Project> result = underTest.getEntities(filterParams);

    assertEquals(expectedList, result);
    verifyNoMoreInteractions(projectRepository);
  }

  @Test
  void testGetEntitiesWithNotExistingField() {
    Map<String, String> filterParams = new HashMap<>();
    filterParams.put("notExistingField", "*");
    Sort sort = Sort.by(ASC, "id");
    Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE, sort);
    Team team = new Team("Name", "Role");
    Project entity = new Project(
        1L,
        "Name",
        "Description",
        "Status",
        LocalDate.now(),
        LocalDate.now(),
        List.of(team)
    );

    List<Project> expectedList = List.of(entity);
    Page<Project> entityPage = new PageImpl<>(expectedList);

    Specification<Project> specification = ArgumentMatchers.any();
    when(projectRepository.findAll(specification, eq(pageable))).thenReturn(entityPage);

    List<Project> result = underTest.getEntities(filterParams);

    assertEquals(expectedList, result);
    verifyNoMoreInteractions(projectRepository);
  }

  @Test
  void testGetTeamByProjectId() {
    Team team = new Team("Name", "Role");
    List<Team> expectedList = List.of(team);
    Project entity = new Project(
        1L,
        "Name",
        "Description",
        "Status",
        LocalDate.now(),
        LocalDate.now(),
        expectedList
    );

    when(projectRepository.findById(eq(entity.getId()))).thenReturn(Optional.of(entity));

    List<Team> result = underTest.getTeamByProjectId(entity.getId());

    assertEquals(expectedList, result);
    verify(projectRepository).findById(eq(entity.getId()));
    verifyNoMoreInteractions(projectRepository);
  }
}
