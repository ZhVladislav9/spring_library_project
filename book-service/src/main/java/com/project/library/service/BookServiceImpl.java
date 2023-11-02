package com.project.library.service;

import com.project.library.convert.BookDTOConverter;
import com.project.library.dto.BookDTO;
import com.project.library.models.Book;
import com.project.library.repository.BookRepository;
import com.project.library.service.interfaces.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    private BookDTOConverter bookDTOConverter;
    @Autowired
    RestTemplate restTemplate;

    public List<BookDTO> getBooks() {
        Iterable<Book> books = bookRepository.findAll();
        List<BookDTO> bookDTOs = new ArrayList<>();

        for (Book book : books) {
            bookDTOs.add(bookDTOConverter.convertBookToBookDTO(book));
        }

        return bookDTOs;
    }

    public BookDTO bookFindById(@PathVariable(value = "id") int id){
        return bookDTOConverter.convertBookToBookDTO(bookRepository.findById(id).orElseThrow());
    }

    public Book addBook(@RequestBody BookDTO bookDTO){
        Book book = bookDTOConverter.convertBookDTOToBook(bookDTO);
        bookRepository.save(book);
        String libraryServiceUrl = "http://localhost:8085/library?bookId=" + book.getId();
        HttpEntity<Integer> request = new HttpEntity<>(book.getId());
        ResponseEntity<String> response = restTemplate.postForEntity(libraryServiceUrl,request,String.class);
        return book;
    }
    public BookDTO bookUpdate(@RequestParam int id, @RequestBody BookDTO bookDTO){
        Book editedBook = bookRepository.findById(id).orElseThrow();
        Book book = bookDTOConverter.convertBookDTOToBook(bookDTO);

        if(book.getIsbn() != null)
            editedBook.setIsbn(book.getIsbn());
        if(book.getName() != null)
            editedBook.setName(book.getName());
        if(book.getGenre() != null)
            editedBook.setGenre(book.getGenre());
        if(book.getDescription() != null)
            editedBook.setDescription(book.getDescription());
        if(book.getAuthor() != null)
            editedBook.setAuthor(book.getAuthor());
        bookRepository.save(editedBook);
        return bookDTOConverter.convertBookToBookDTO(editedBook);
    }
    public ResponseEntity<HttpStatus> bookDelete(@PathVariable(value = "id") int id){
        Book book = bookRepository.findById(id).orElseThrow();
        bookRepository.delete(book);
        return ResponseEntity.noContent().build();
    }
    public BookDTO getBookByIsbn(@RequestParam(value = "isbn") String isbn){
        return bookDTOConverter.convertBookToBookDTO(bookRepository.findByIsbn(isbn).orElseThrow());
    }
}
