package com.project.library.controllers;

import com.project.library.dto.BookDTO;
import com.project.library.service.BookServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/book")
@Tag(name = "Book")
public class BookController {
    @Autowired
    private BookServiceImpl bookServiceImpl;
    @GetMapping("/all-books")
    @Operation(summary = "Вывести книги", description = "Вывести все книги")
    public List<BookDTO> getBooks(){
        return bookServiceImpl.getBooks();
    }
    @GetMapping("/{id}")
    @Operation(summary = "Вывести книгу", description = "Вывести книгу по id")
    public BookDTO bookFindById(@PathVariable int id){
        return bookServiceImpl.bookFindById(id);
    }
    @PostMapping
    @Operation(summary = "Добавить книгу", description = "Добавить книгу")
    public BookDTO bookAdd(@RequestBody BookDTO bookDTO){
        bookServiceImpl.addBook(bookDTO);
        return bookDTO;
    }
    @PutMapping
    @Operation(summary = "Изменить книгу", description = "Изменить книгу")
    public BookDTO bookUpdate(@RequestParam int id, @RequestBody BookDTO bookDTO){
        return bookServiceImpl.bookUpdate(id, bookDTO);
    }
    @DeleteMapping
    @Operation(summary = "Удалить книгу", description = "Удалить книгу")
    public ResponseEntity<HttpStatus> bookDelete(@RequestParam(value = "id") int id){
        bookServiceImpl.bookDelete(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/isbn")
    @Operation(summary = "Вывести книгу по isbn", description = "Вывести книгу по isbn")
    public BookDTO getBookByIsbn(@RequestParam String isbn){
        return bookServiceImpl.getBookByIsbn(isbn);
    }
}
