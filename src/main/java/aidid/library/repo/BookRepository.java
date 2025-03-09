package aidid.library.repo;

import aidid.library.model.Book;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByBorrowerId(Long borrowerId);
}