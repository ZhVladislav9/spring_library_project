package com.project.libraryservice.libraryservice.convert;

import com.project.libraryservice.libraryservice.dto.BorrowedBookDTO;
import com.project.libraryservice.libraryservice.models.BorrowedBook;
import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
public class BorrowedBookDTOConverter {
    @Autowired
    private ModelMapper modelMapper;
    public BorrowedBookDTO convertBorrowedBookToBorrowedBookDTO(BorrowedBook borrowedBook){
        return modelMapper.map(borrowedBook, BorrowedBookDTO.class);
    }
    public BorrowedBook convertBorrowedBookDTOToBorrowedBook(BorrowedBookDTO borrowedBookDTO){
        return modelMapper.map(borrowedBookDTO, BorrowedBook.class);
    }
}
