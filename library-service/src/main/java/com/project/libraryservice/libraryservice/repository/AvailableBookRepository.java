package com.project.libraryservice.libraryservice.repository;

import com.project.libraryservice.libraryservice.models.AvailableBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvailableBookRepository extends JpaRepository<AvailableBook, Integer> {
    AvailableBook findByBookId(int bookId);
    List<AvailableBook> findByQuantityGreaterThan(int i);
}
