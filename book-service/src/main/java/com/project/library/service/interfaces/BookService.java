package com.project.library.service.interfaces;

import com.project.library.dto.BookDTO;
import com.project.library.models.Book;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public interface BookService {
    public List<BookDTO> getBooks();
    public BookDTO bookFindById(int id);
    public Book addBook(BookDTO bookDTO);
    public BookDTO bookUpdate(int id, BookDTO bookDTO);
    public ResponseEntity<HttpStatus> bookDelete(int id);
    public BookDTO getBookByIsbn(String isbn);

}
