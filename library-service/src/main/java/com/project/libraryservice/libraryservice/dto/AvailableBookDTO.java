package com.project.libraryservice.libraryservice.dto;

import lombok.Data;

@Data
public class AvailableBookDTO {
    private Integer id;
    private Integer bookId;
    private int quantity;
}
