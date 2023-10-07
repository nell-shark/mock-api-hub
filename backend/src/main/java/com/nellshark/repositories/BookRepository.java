package com.nellshark.repositories;

import com.nellshark.models.Book;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CustomGenericRepository<Book, Long> {

}
