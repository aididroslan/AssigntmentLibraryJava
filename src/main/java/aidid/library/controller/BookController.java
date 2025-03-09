package aidid.library.controller;

import aidid.library.model.Book;
import aidid.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping
    public Book registerBook(@RequestBody Book book) {
        return bookService.registerBook(book);
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @PostMapping("/{bookId}/borrow/{borrowerId}")
    public void borrowBook(@PathVariable Long bookId, @PathVariable Long borrowerId) {
        bookService.borrowBook(bookId, borrowerId);
    }

    @PostMapping("/{bookId}/return/{borrowerId}")
    public void returnBook(@PathVariable Long bookId, @PathVariable Long borrowerId) {
        bookService.returnBook(bookId, borrowerId);
    }
}