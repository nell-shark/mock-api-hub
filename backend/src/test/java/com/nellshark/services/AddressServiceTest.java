package com.nellshark.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.data.domain.Sort.Direction.ASC;

import com.nellshark.models.Address;
import com.nellshark.repositories.AddressRepository;
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
public class AddressServiceTest {

  @Mock
  private AddressRepository addressRepository;

  private AddressService underTest;

  @BeforeEach
  void setUp() {
    underTest = new AddressService(addressRepository);
  }

  @Test
  void testGetEntityById() {
    Address entity = new Address(
        1L,
        "Street",
        "City",
        "CountryCode",
        "Country",
        "PostCode"
    );

    when(addressRepository.findById(eq(entity.getId()))).thenReturn(Optional.of(entity));

    Address result = underTest.getEntityById(entity.getId());

    assertEquals(entity, result);
    verify(addressRepository).findById(eq(entity.getId()));
    verifyNoMoreInteractions(addressRepository);
  }

  @Test
  void testGetEntityByIdNotFound() {
    Address entity = new Address(
        1L,
        "Street",
        "City",
        "CountryCode",
        "Country",
        "PostCode"
    );

    when(addressRepository.findById(eq(entity.getId()))).thenReturn(Optional.empty());

    assertThrows(EntityNotFoundException.class, () -> underTest.getEntityById(entity.getId()));

    verify(addressRepository).findById(eq(entity.getId()));
    verifyNoMoreInteractions(addressRepository);
  }

  @Test
  void testGetEntitiesWithDefaultParams() {
    Map<String, String> filterParams = new HashMap<>();
    Sort sort = Sort.by(ASC, "id");
    Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE, sort);
    Address entity = new Address(
        1L,
        "Street",
        "City",
        "CountryCode",
        "Country",
        "PostCode"
    );
    List<Address> expectedList = List.of(entity);
    Page<Address> entityPage = new PageImpl<>(expectedList);

    Specification<Address> specification = ArgumentMatchers.any();
    when(addressRepository.findAll(specification, eq(pageable))).thenReturn(entityPage);

    List<Address> result = underTest.getEntities(filterParams);

    assertEquals(expectedList, result);
    verifyNoMoreInteractions(addressRepository);
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
    Address entity = new Address(
        1L,
        "Street",
        "City",
        "CountryCode",
        "Country",
        "PostCode"
    );
    List<Address> expectedList = List.of(entity);
    Page<Address> entityPage = new PageImpl<>(expectedList);

    Specification<Address> specification = ArgumentMatchers.any();
    when(addressRepository.findAll(specification, eq(pageable))).thenReturn(entityPage);

    List<Address> result = underTest.getEntities(filterParams);

    assertEquals(expectedList, result);
    verifyNoMoreInteractions(addressRepository);
  }

  @Test
  void testGetEntitiesWhenPageIsEmpty() {
    Map<String, String> filterParams = new HashMap<>();
    filterParams.put("direction", "ASC");
    filterParams.put("sort", "id");
    filterParams.put("size", "10");
    Sort sort = Sort.by(ASC, "id");
    Pageable pageable = PageRequest.of(0, 10, sort);
    Address entity = new Address(
        1L,
        "Street",
        "City",
        "CountryCode",
        "Country",
        "PostCode"
    );
    List<Address> expectedList = List.of(entity);
    Page<Address> entityPage = new PageImpl<>(expectedList);

    Specification<Address> specification = ArgumentMatchers.any();
    when(addressRepository.findAll(specification, eq(pageable))).thenReturn(entityPage);

    List<Address> result = underTest.getEntities(filterParams);

    assertEquals(expectedList, result);
    verifyNoMoreInteractions(addressRepository);
  }

  @Test
  void testGetEntitiesWhenSizeIsEmpty() {
    Map<String, String> filterParams = new HashMap<>();
    filterParams.put("direction", "ASC");
    filterParams.put("sort", "id");
    filterParams.put("page", "10");
    Sort sort = Sort.by(ASC, "id");
    Pageable pageable = PageRequest.of(9, 10, sort);
    Address entity = new Address(
        1L,
        "Street",
        "City",
        "CountryCode",
        "Country",
        "PostCode"
    );
    List<Address> expectedList = List.of(entity);
    Page<Address> entitPage = new PageImpl<>(expectedList);

    Specification<Address> specification = ArgumentMatchers.any();
    when(addressRepository.findAll(specification, eq(pageable))).thenReturn(entitPage);

    List<Address> result = underTest.getEntities(filterParams);

    assertEquals(expectedList, result);
    verifyNoMoreInteractions(addressRepository);
  }

  @Test
  void testGetEntitiesWithInvalidPage() {
    Map<String, String> filterParams = new HashMap<>();
    filterParams.put("direction", "ASC");
    filterParams.put("page", "-1");
    filterParams.put("size", "10");
    Sort sort = Sort.by(ASC, "id");
    Pageable pageable = PageRequest.of(0, 10, sort);
    Address entity = new Address(
        1L,
        "Street",
        "City",
        "CountryCode",
        "Country",
        "PostCode"
    );
    List<Address> expectedList = List.of(entity);
    Page<Address> entityPage = new PageImpl<>(expectedList);

    Specification<Address> specification = ArgumentMatchers.any();
    when(addressRepository.findAll(specification, eq(pageable))).thenReturn(entityPage);

    List<Address> result = underTest.getEntities(filterParams);

    assertEquals(expectedList, result);
    verifyNoMoreInteractions(addressRepository);
  }

  @Test
  void testGetEntitiesWithInvalidSize() {
    Map<String, String> filterParams = new HashMap<>();
    filterParams.put("direction", "ASC");
    filterParams.put("page", "1");
    filterParams.put("size", "-1");
    Sort sort = Sort.by(ASC, "id");
    Pageable pageable = PageRequest.of(0, 10, sort);
    Address entity = new Address(
        1L,
        "Street",
        "City",
        "CountryCode",
        "Country",
        "PostCode"
    );
    List<Address> expectedList = List.of(entity);
    Page<Address> entityPage = new PageImpl<>(expectedList);

    Specification<Address> specification = ArgumentMatchers.any();
    when(addressRepository.findAll(specification, eq(pageable))).thenReturn(entityPage);

    List<Address> result = underTest.getEntities(filterParams);

    assertEquals(expectedList, result);
    verifyNoMoreInteractions(addressRepository);
  }

  @Test
  void testGetEntitiesWithNotExistingField() {
    Map<String, String> filterParams = new HashMap<>();
    filterParams.put("notExistingField", "*");
    Sort sort = Sort.by(ASC, "id");
    Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE, sort);
    Address entity = new Address(
        1L,
        "Street",
        "City",
        "CountryCode",
        "Country",
        "PostCode"
    );
    List<Address> expectedList = List.of(entity);
    Page<Address> entityPage = new PageImpl<>(expectedList);

    Specification<Address> specification = ArgumentMatchers.any();
    when(addressRepository.findAll(specification, eq(pageable))).thenReturn(entityPage);

    List<Address> result = underTest.getEntities(filterParams);

    assertEquals(expectedList, result);
    verifyNoMoreInteractions(addressRepository);
  }
}
