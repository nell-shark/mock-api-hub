package com.nellshark.services;

import com.nellshark.repositories.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class BookService {

  private static final Logger logger = LoggerFactory.getLogger(BookService.class);

  private final BookRepository bookRepository;

  public BookService(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }
}
