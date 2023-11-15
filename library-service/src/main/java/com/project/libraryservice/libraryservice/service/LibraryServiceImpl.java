package com.project.libraryservice.libraryservice.service;

import com.project.libraryservice.libraryservice.convert.AvailableBookDTOConverter;
import com.project.libraryservice.libraryservice.convert.BorrowedBookDTOConverter;
import com.project.libraryservice.libraryservice.dto.AvailableBookDTO;
import com.project.libraryservice.libraryservice.dto.BookDTO;
import com.project.libraryservice.libraryservice.models.AvailableBook;
import com.project.libraryservice.libraryservice.models.BorrowedBook;
import com.project.libraryservice.libraryservice.repository.AvailableBookRepository;
import com.project.libraryservice.libraryservice.repository.BorrowedBookRepository;
import com.project.libraryservice.libraryservice.service.interfaces.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LibraryServiceImpl implements LibraryService {
    @Autowired
    private AvailableBookRepository availableBookRepository;
    @Autowired
    private BorrowedBookRepository borrowedBookRepository;
    @Autowired
    private AvailableBookDTOConverter availableBookDTOConverter;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private BorrowedBookDTOConverter borrowedBookDTOConverter;

    public AvailableBookDTO newAvailableBook(int bookId) {
        AvailableBook availableBook = AvailableBook.builder()
                .bookId(bookId)
                .quantity(1)
                .build();
        availableBookRepository.save(availableBook);
        return availableBookDTOConverter.convertAvailableBookToAvailableBookDTO(availableBook);
    }
    public AvailableBookDTO addAvailableBook(int bookId) {
        AvailableBook availableBook = availableBookRepository.findByBookId(bookId);
        if (availableBook != null) {
            availableBook.setQuantity(availableBook.getQuantity() + 1);
            availableBookRepository.save(availableBook);
            return availableBookDTOConverter.convertAvailableBookToAvailableBookDTO(availableBook);
        }else return null;
    }
    public BorrowedBook addBorrowedBook(int bookId) {
        AvailableBook availableBook = availableBookRepository.findByBookId(bookId);

        if (availableBook != null && availableBook.getQuantity() > 0) {
            availableBook.setQuantity(availableBook.getQuantity() - 1);
            availableBookRepository.save(availableBook);
            BorrowedBook borrowedBook = BorrowedBook.builder()
                    .bookId(bookId)
                    .borrowedDate(LocalDateTime.now())
                    .returnDate(LocalDateTime.now().plusWeeks(2))
                    .build();
            borrowedBookRepository.save(borrowedBook);
            return borrowedBook;
        }else return null;
    }
    public List<BookDTO> getAvailableBooks(){
        ResponseEntity<List<BookDTO>> responseEntity = restTemplate.exchange(
                "http://localhost:8084/book/all-books",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<BookDTO>>() {}
        );

        List<AvailableBook> availableBookList = availableBookRepository.findByQuantityGreaterThan(0);
        List<BookDTO> bookDTOs = responseEntity.getBody();

        List<BookDTO> matchingBooks = bookDTOs.stream()
                .filter(bookDTO -> availableBookList.stream()
                        .map(AvailableBook::getBookId)
                        .anyMatch(id -> id.equals(bookDTO.getId())))
                .toList();

        return matchingBooks;
    }

    public AvailableBookDTO returnBorrowedBook(int bookId) {
        AvailableBook availableBook = availableBookRepository.findByBookId(bookId);
        BorrowedBook borrowedBook = borrowedBookRepository.findByBookId(bookId);

        if (borrowedBook != null) {
            availableBook.setQuantity(availableBook.getQuantity() + 1);
            availableBookRepository.save(availableBook);
            borrowedBookRepository.delete(borrowedBook);
            return availableBookDTOConverter.convertAvailableBookToAvailableBookDTO(availableBook);
        }else return null;
    }
}
