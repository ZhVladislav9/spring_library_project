package com.project.library.dto;

import lombok.Data;

@Data
public class BookDTO {
    private Integer id;
    private String isbn;
    private String name;
    private String genre;
    private String description;
    private String author;
}
