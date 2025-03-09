package aidid.library.service;

import aidid.library.model.Book;
import java.util.List;

public interface BookService {
    Book registerBook(Book book);
    List<Book> getAllBooks();
    void borrowBook(Long bookId, Long borrowerId);
    void returnBook(Long bookId, Long borrowerId);
}