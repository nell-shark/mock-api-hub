package com.nellshark.controllers;

import com.nellshark.models.Book;
import com.nellshark.services.BookService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/books")
public class BookController extends AbstractGenericController<Book, Long> {

  public BookController(BookService bookService) {
    super(bookService);
  }
}
