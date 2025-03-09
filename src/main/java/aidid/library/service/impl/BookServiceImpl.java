package aidid.library.service.impl;

import aidid.library.model.Book;
import aidid.library.repo.BookRepository;
import aidid.library.repo.BorrowerRepository;
import aidid.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book registerBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public void borrowBook(Long bookId, Long borrowerId) {
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();
            if (book.getBorrowerId() == null) {
                // Check if the borrower is already borrowing another book
                List<Book> borrowedBooks = bookRepository.findByBorrowerId(borrowerId);
                if (borrowedBooks.isEmpty()) {
                    book.setBorrowerId(borrowerId);
                    bookRepository.save(book);
                } else {
                    throw new IllegalStateException("Borrower is already borrowing another book");
                }
            } else {
                throw new IllegalStateException("Book is already borrowed");
            }
        } else {
            throw new IllegalArgumentException("Book not found");
        }
    }

    @Override
    public void returnBook(Long bookId, Long borrowerId) {
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();
            if (borrowerId.equals(book.getBorrowerId())) {
                book.setBorrowerId(null);
                bookRepository.save(book);
            } else {
                throw new IllegalStateException("Book was not borrowed by this borrower");
            }
        } else {
            throw new IllegalArgumentException("Book not found");
        }
    }
}