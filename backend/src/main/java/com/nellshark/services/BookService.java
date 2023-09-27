package com.nellshark.services;

import com.nellshark.models.Book;
import com.nellshark.repositories.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class BookService extends AbstractGenericService<Book, Long> {

  public BookService(BookRepository bookRepository) {
    super(bookRepository);
  }
}
