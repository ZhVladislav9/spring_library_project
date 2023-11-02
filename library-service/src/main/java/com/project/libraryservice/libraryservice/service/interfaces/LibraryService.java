package com.project.libraryservice.libraryservice.service.interfaces;

import com.project.libraryservice.libraryservice.dto.AvailableBookDTO;
import com.project.libraryservice.libraryservice.dto.BookDTO;
import com.project.libraryservice.libraryservice.models.BorrowedBook;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LibraryService {
    public AvailableBookDTO newAvailableBook(int bookId);
    public AvailableBookDTO addAvailableBook(int bookId);
    public BorrowedBook addBorrowedBook(int bookId);
    public List<BookDTO> getAvailableBooks();
    public AvailableBookDTO returnBorrowedBook(int bookId);
}
