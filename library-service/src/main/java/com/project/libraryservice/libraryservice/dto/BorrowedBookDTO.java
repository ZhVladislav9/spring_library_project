package com.project.libraryservice.libraryservice.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BorrowedBookDTO {
    public class BorrowedBook {
        private Integer id;
        private Integer bookId;
        private LocalDateTime borrowedDate;
        private LocalDateTime returnDate;
    }
}
