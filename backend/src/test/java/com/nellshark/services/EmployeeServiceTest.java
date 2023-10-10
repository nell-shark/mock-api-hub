package com.nellshark.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.data.domain.Sort.Direction.ASC;

import com.nellshark.models.Employee;
import com.nellshark.repositories.EmployeeRepository;
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
public class EmployeeServiceTest {

  @Mock
  private EmployeeRepository employeeRepository;

  private EmployeeService underTest;

  @BeforeEach
  void setUp() {
    underTest = new EmployeeService(employeeRepository);
  }

  @Test
  void testGetEntityById() {
    Employee entity = new Employee(
        1L,
        "FisrtName",
        "LastName",
        1999L,
        "email@google.com",
        "Department",
        "Postion"
    );

    when(employeeRepository.findById(eq(entity.getId()))).thenReturn(Optional.of(entity));

    Employee result = underTest.getEntityById(entity.getId());

    assertEquals(entity, result);
    verify(employeeRepository).findById(eq(entity.getId()));
    verifyNoMoreInteractions(employeeRepository);
  }

  @Test
  void testGetEntityByIdNotFound() {
    Employee entity = new Employee(
        1L,
        "FisrtName",
        "LastName",
        1999L,
        "email@google.com",
        "Department",
        "Postion"
    );

    when(employeeRepository.findById(eq(entity.getId()))).thenReturn(Optional.empty());

    assertThrows(EntityNotFoundException.class, () -> underTest.getEntityById(entity.getId()));

    verify(employeeRepository).findById(eq(entity.getId()));
    verifyNoMoreInteractions(employeeRepository);
  }

  @Test
  void testGetEntitiesWithDefaultParams() {
    Map<String, String> filterParams = new HashMap<>();
    Sort sort = Sort.by(ASC, "id");
    Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE, sort);
    Employee entity = new Employee(
        1L,
        "FisrtName",
        "LastName",
        1999L,
        "email@google.com",
        "Department",
        "Postion"
    );

    List<Employee> expectedList = List.of(entity);
    Page<Employee> entityPage = new PageImpl<>(expectedList);

    Specification<Employee> specification = ArgumentMatchers.any();
    when(employeeRepository.findAll(specification, eq(pageable))).thenReturn(entityPage);

    List<Employee> result = underTest.getEntities(filterParams);

    assertEquals(expectedList, result);
    verifyNoMoreInteractions(employeeRepository);
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
    Employee entity = new Employee(
        1L,
        "FisrtName",
        "LastName",
        1999L,
        "email@google.com",
        "Department",
        "Postion"
    );

    List<Employee> expectedList = List.of(entity);
    Page<Employee> entityPage = new PageImpl<>(expectedList);

    Specification<Employee> specification = ArgumentMatchers.any();
    when(employeeRepository.findAll(specification, eq(pageable))).thenReturn(entityPage);

    List<Employee> result = underTest.getEntities(filterParams);

    assertEquals(expectedList, result);
    verifyNoMoreInteractions(employeeRepository);
  }

  @Test
  void testGetEntitiesWhenPageIsEmpty() {
    Map<String, String> filterParams = new HashMap<>();
    filterParams.put("direction", "ASC");
    filterParams.put("sort", "id");
    filterParams.put("size", "10");
    Sort sort = Sort.by(ASC, "id");
    Pageable pageable = PageRequest.of(0, 10, sort);
    Employee entity = new Employee(
        1L,
        "FisrtName",
        "LastName",
        1999L,
        "email@google.com",
        "Department",
        "Postion"
    );

    List<Employee> expectedList = List.of(entity);
    Page<Employee> entityPage = new PageImpl<>(expectedList);

    Specification<Employee> specification = ArgumentMatchers.any();
    when(employeeRepository.findAll(specification, eq(pageable))).thenReturn(entityPage);

    List<Employee> result = underTest.getEntities(filterParams);

    assertEquals(expectedList, result);
    verifyNoMoreInteractions(employeeRepository);
  }

  @Test
  void testGetEntitiesWhenSizeIsEmpty() {
    Map<String, String> filterParams = new HashMap<>();
    filterParams.put("direction", "ASC");
    filterParams.put("sort", "id");
    filterParams.put("page", "10");
    Sort sort = Sort.by(ASC, "id");
    Pageable pageable = PageRequest.of(9, 10, sort);
    Employee entity = new Employee(
        1L,
        "FisrtName",
        "LastName",
        1999L,
        "email@google.com",
        "Department",
        "Postion"
    );

    List<Employee> expectedList = List.of(entity);
    Page<Employee> entitPage = new PageImpl<>(expectedList);

    Specification<Employee> specification = ArgumentMatchers.any();
    when(employeeRepository.findAll(specification, eq(pageable))).thenReturn(entitPage);

    List<Employee> result = underTest.getEntities(filterParams);

    assertEquals(expectedList, result);
    verifyNoMoreInteractions(employeeRepository);
  }

  @Test
  void testGetEntitiesWithInvalidPage() {
    Map<String, String> filterParams = new HashMap<>();
    filterParams.put("direction", "ASC");
    filterParams.put("page", "-1");
    filterParams.put("size", "10");
    Sort sort = Sort.by(ASC, "id");
    Pageable pageable = PageRequest.of(0, 10, sort);
    Employee entity = new Employee(
        1L,
        "FisrtName",
        "LastName",
        1999L,
        "email@google.com",
        "Department",
        "Postion"
    );

    List<Employee> expectedList = List.of(entity);
    Page<Employee> entityPage = new PageImpl<>(expectedList);

    Specification<Employee> specification = ArgumentMatchers.any();
    when(employeeRepository.findAll(specification, eq(pageable))).thenReturn(entityPage);

    List<Employee> result = underTest.getEntities(filterParams);

    assertEquals(expectedList, result);
    verifyNoMoreInteractions(employeeRepository);
  }

  @Test
  void testGetEntitiesWithInvalidSize() {
    Map<String, String> filterParams = new HashMap<>();
    filterParams.put("direction", "ASC");
    filterParams.put("page", "1");
    filterParams.put("size", "-1");
    Sort sort = Sort.by(ASC, "id");
    Pageable pageable = PageRequest.of(0, 10, sort);
    Employee entity = new Employee(
        1L,
        "FisrtName",
        "LastName",
        1999L,
        "email@google.com",
        "Department",
        "Postion"
    );

    List<Employee> expectedList = List.of(entity);
    Page<Employee> entityPage = new PageImpl<>(expectedList);

    Specification<Employee> specification = ArgumentMatchers.any();
    when(employeeRepository.findAll(specification, eq(pageable))).thenReturn(entityPage);

    List<Employee> result = underTest.getEntities(filterParams);

    assertEquals(expectedList, result);
    verifyNoMoreInteractions(employeeRepository);
  }

  @Test
  void testGetEntitiesWithNotExistingField() {
    Map<String, String> filterParams = new HashMap<>();
    filterParams.put("notExistingField", "*");
    Sort sort = Sort.by(ASC, "id");
    Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE, sort);
    Employee entity = new Employee(
        1L,
        "FisrtName",
        "LastName",
        1999L,
        "email@google.com",
        "Department",
        "Postion"
    );

    List<Employee> expectedList = List.of(entity);
    Page<Employee> entityPage = new PageImpl<>(expectedList);

    Specification<Employee> specification = ArgumentMatchers.any();
    when(employeeRepository.findAll(specification, eq(pageable))).thenReturn(entityPage);

    List<Employee> result = underTest.getEntities(filterParams);

    assertEquals(expectedList, result);
    verifyNoMoreInteractions(employeeRepository);
  }
}
