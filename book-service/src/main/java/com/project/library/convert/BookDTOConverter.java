package com.project.library.convert;

import com.project.library.dto.BookDTO;
import com.project.library.models.Book;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class BookDTOConverter{
    @Autowired
    private ModelMapper modelMapper;

    public BookDTO convertBookToBookDTO(Book book){
        return modelMapper.map(book,BookDTO.class);
    }
    public Book convertBookDTOToBook(BookDTO bookDTO){
        return modelMapper.map(bookDTO,Book.class);
    }
}
