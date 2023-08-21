package com.nellshark.repositories;

import com.nellshark.models.Book;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class BookRepository extends AbstractGenericRepository<Book> {

  public BookRepository() {
    super(List.of(new Book(1L)));
  }
}
