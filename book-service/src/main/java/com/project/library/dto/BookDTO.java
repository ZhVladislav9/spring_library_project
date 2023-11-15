package com.project.library.dto;

import lombok.Data;
import java.util.List;

@Data
public class BookDTO {
    private Integer id;
    private String isbn;
    private String name;
    private String genre;
    private String description;
    private String author;

    private List<BookDTO> books;
}
