package com.project.libraryservice.libraryservice.repository;

import com.project.libraryservice.libraryservice.models.BorrowedBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowedBookRepository extends JpaRepository<BorrowedBook, Integer> {
    BorrowedBook findByBookId(Integer bookId);
}
