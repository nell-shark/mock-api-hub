package com.nellshark.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.data.domain.Sort.Direction.ASC;

import com.nellshark.models.Company;
import com.nellshark.repositories.CompanyRepository;
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
public class CompanyServiceTest {

  @Mock
  private CompanyRepository companyRepository;

  private CompanyService underTest;

  @BeforeEach
  void setUp() {
    underTest = new CompanyService(companyRepository);
  }

  @Test
  void testGetEntityById() {
    Company entity = new Company(
        1L,
        "Name",
        "Industry",
        1998L,
        "Location",
        "www.example.com",
        12L,
        "Description"
    );

    when(companyRepository.findById(eq(entity.getId()))).thenReturn(Optional.of(entity));

    Company result = underTest.getEntityById(entity.getId());

    assertEquals(entity, result);
    verify(companyRepository).findById(eq(entity.getId()));
    verifyNoMoreInteractions(companyRepository);
  }

  @Test
  void testGetEntityByIdNotFound() {
    Company entity = new Company(
        1L,
        "Name",
        "Industry",
        1998L,
        "Location",
        "www.example.com",
        12L,
        "Description"
    );

    when(companyRepository.findById(eq(entity.getId()))).thenReturn(Optional.empty());

    assertThrows(EntityNotFoundException.class, () -> underTest.getEntityById(entity.getId()));

    verify(companyRepository).findById(eq(entity.getId()));
    verifyNoMoreInteractions(companyRepository);
  }

  @Test
  void testGetEntitiesWithDefaultParams() {
    Map<String, String> filterParams = new HashMap<>();
    Sort sort = Sort.by(ASC, "id");
    Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE, sort);
    Company entity = new Company(
        1L,
        "Name",
        "Industry",
        1998L,
        "Location",
        "www.example.com",
        12L,
        "Description"
    );

    List<Company> expectedList = List.of(entity);
    Page<Company> entityPage = new PageImpl<>(expectedList);

    Specification<Company> specification = ArgumentMatchers.any();
    when(companyRepository.findAll(specification, eq(pageable))).thenReturn(entityPage);

    List<Company> result = underTest.getEntities(filterParams);

    assertEquals(expectedList, result);
    verifyNoMoreInteractions(companyRepository);
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
    Company entity = new Company(
        1L,
        "Name",
        "Industry",
        1998L,
        "Location",
        "www.example.com",
        12L,
        "Description"
    );

    List<Company> expectedList = List.of(entity);
    Page<Company> entityPage = new PageImpl<>(expectedList);

    Specification<Company> specification = ArgumentMatchers.any();
    when(companyRepository.findAll(specification, eq(pageable))).thenReturn(entityPage);

    List<Company> result = underTest.getEntities(filterParams);

    assertEquals(expectedList, result);
    verifyNoMoreInteractions(companyRepository);
  }

  @Test
  void testGetEntitiesWhenPageIsEmpty() {
    Map<String, String> filterParams = new HashMap<>();
    filterParams.put("direction", "ASC");
    filterParams.put("sort", "id");
    filterParams.put("size", "10");
    Sort sort = Sort.by(ASC, "id");
    Pageable pageable = PageRequest.of(0, 10, sort);
    Company entity = new Company(
        1L,
        "Name",
        "Industry",
        1998L,
        "Location",
        "www.example.com",
        12L,
        "Description"
    );

    List<Company> expectedList = List.of(entity);
    Page<Company> entityPage = new PageImpl<>(expectedList);

    Specification<Company> specification = ArgumentMatchers.any();
    when(companyRepository.findAll(specification, eq(pageable))).thenReturn(entityPage);

    List<Company> result = underTest.getEntities(filterParams);

    assertEquals(expectedList, result);
    verifyNoMoreInteractions(companyRepository);
  }

  @Test
  void testGetEntitiesWhenSizeIsEmpty() {
    Map<String, String> filterParams = new HashMap<>();
    filterParams.put("direction", "ASC");
    filterParams.put("sort", "id");
    filterParams.put("page", "10");
    Sort sort = Sort.by(ASC, "id");
    Pageable pageable = PageRequest.of(9, 10, sort);
    Company entity = new Company(
        1L,
        "Name",
        "Industry",
        1998L,
        "Location",
        "www.example.com",
        12L,
        "Description"
    );

    List<Company> expectedList = List.of(entity);
    Page<Company> entitPage = new PageImpl<>(expectedList);

    Specification<Company> specification = ArgumentMatchers.any();
    when(companyRepository.findAll(specification, eq(pageable))).thenReturn(entitPage);

    List<Company> result = underTest.getEntities(filterParams);

    assertEquals(expectedList, result);
    verifyNoMoreInteractions(companyRepository);
  }

  @Test
  void testGetEntitiesWithInvalidPage() {
    Map<String, String> filterParams = new HashMap<>();
    filterParams.put("direction", "ASC");
    filterParams.put("page", "-1");
    filterParams.put("size", "10");
    Sort sort = Sort.by(ASC, "id");
    Pageable pageable = PageRequest.of(0, 10, sort);
    Company entity = new Company(
        1L,
        "Name",
        "Industry",
        1998L,
        "Location",
        "www.example.com",
        12L,
        "Description"
    );

    List<Company> expectedList = List.of(entity);
    Page<Company> entityPage = new PageImpl<>(expectedList);

    Specification<Company> specification = ArgumentMatchers.any();
    when(companyRepository.findAll(specification, eq(pageable))).thenReturn(entityPage);

    List<Company> result = underTest.getEntities(filterParams);

    assertEquals(expectedList, result);
    verifyNoMoreInteractions(companyRepository);
  }

  @Test
  void testGetEntitiesWithInvalidSize() {
    Map<String, String> filterParams = new HashMap<>();
    filterParams.put("direction", "ASC");
    filterParams.put("page", "1");
    filterParams.put("size", "-1");
    Sort sort = Sort.by(ASC, "id");
    Pageable pageable = PageRequest.of(0, 10, sort);
    Company entity = new Company(
        1L,
        "Name",
        "Industry",
        1998L,
        "Location",
        "www.example.com",
        12L,
        "Description"
    );

    List<Company> expectedList = List.of(entity);
    Page<Company> entityPage = new PageImpl<>(expectedList);

    Specification<Company> specification = ArgumentMatchers.any();
    when(companyRepository.findAll(specification, eq(pageable))).thenReturn(entityPage);

    List<Company> result = underTest.getEntities(filterParams);

    assertEquals(expectedList, result);
    verifyNoMoreInteractions(companyRepository);
  }

  @Test
  void testGetEntitiesWithNotExistingField() {
    Map<String, String> filterParams = new HashMap<>();
    filterParams.put("notExistingField", "*");
    Sort sort = Sort.by(ASC, "id");
    Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE, sort);
    Company entity = new Company(
        1L,
        "Name",
        "Industry",
        1998L,
        "Location",
        "www.example.com",
        12L,
        "Description"
    );

    List<Company> expectedList = List.of(entity);
    Page<Company> entityPage = new PageImpl<>(expectedList);

    Specification<Company> specification = ArgumentMatchers.any();
    when(companyRepository.findAll(specification, eq(pageable))).thenReturn(entityPage);

    List<Company> result = underTest.getEntities(filterParams);

    assertEquals(expectedList, result);
    verifyNoMoreInteractions(companyRepository);
  }
}
