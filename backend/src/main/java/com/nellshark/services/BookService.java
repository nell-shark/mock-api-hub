package com.nellshark.services;

import com.nellshark.models.Book;
import com.nellshark.repositories.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
public class BookService {

  private static final Logger logger = LoggerFactory.getLogger(BookService.class);

  private final BookRepository bookRepository;

  public BookService(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  public void saveBook(@NonNull Book book) {
    logger.info("Save book: {}", book);
    bookRepository.save(book);
  }
}
