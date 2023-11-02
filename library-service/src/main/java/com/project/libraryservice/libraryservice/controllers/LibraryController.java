package com.project.libraryservice.libraryservice.controllers;

import com.project.libraryservice.libraryservice.dto.AvailableBookDTO;
import com.project.libraryservice.libraryservice.dto.BookDTO;
import com.project.libraryservice.libraryservice.models.BorrowedBook;
import com.project.libraryservice.libraryservice.service.LibraryServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/library")
public class LibraryController {
    @Autowired
    private LibraryServiceImpl libraryServiceImpl;

    @PostMapping
    @Operation(summary = "Добавить новую книгу", description = "Добавить новую книгу")
    public String newAvailableBook(@RequestParam int bookId){
        libraryServiceImpl.newAvailableBook(bookId);
        return "Ok";
    }
    @PostMapping("/borrow-book")
    @Operation(summary = "Взять книгу", description = "Взять книгу по id")
    public BorrowedBook borrowBook(@RequestParam int bookId){
        return libraryServiceImpl.addBorrowedBook(bookId);
    }
    @PostMapping("/add-book")
    @Operation(summary = "Добавить существующую книгу", description = "Добавить существующую книгу")
    public AvailableBookDTO addBook(@RequestParam int bookId){
        return libraryServiceImpl.addAvailableBook(bookId);
    }
    @PostMapping("/return-book")
    @Operation(summary = "Вернуть книгу", description = "Вернуть книгу")
    public AvailableBookDTO returnBook(@RequestParam int bookId){
        return libraryServiceImpl.returnBorrowedBook(bookId);
    }
    @GetMapping("/books")
    @Operation(summary = "Вывести все доступные книги", description = "Вывести все доступные книги")
    public List<BookDTO> getAvailableBooks(){
        return libraryServiceImpl.getAvailableBooks();
    }
}
