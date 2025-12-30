package com.library.library.service;

import java.util.List;
import com.library.library.entity.Book;

public interface BookService {

    List<Book> getAllBooks();

    void saveBook(Book book);

    Book getBookById(Long id);

    void deleteBook(Long id);
}
