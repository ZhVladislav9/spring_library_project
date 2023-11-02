package com.project.libraryservice.libraryservice.convert;

import com.project.libraryservice.libraryservice.dto.AvailableBookDTO;
import com.project.libraryservice.libraryservice.models.AvailableBook;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AvailableBookDTOConverter {
    @Autowired
    private ModelMapper modelMapper;

    public AvailableBookDTO convertAvailableBookToAvailableBookDTO(AvailableBook availableBook){
        return modelMapper.map(availableBook,AvailableBookDTO.class);
    }
    public AvailableBook convertAvailableBookDTOToAvailableBook(AvailableBookDTO availableBookDTO){
        return modelMapper.map(availableBookDTO,AvailableBook.class);
    }
}
