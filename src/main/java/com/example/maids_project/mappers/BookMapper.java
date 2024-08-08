package com.example.maids_project.mappers;

import com.example.maids_project.dtos.BookDTO;
import com.example.maids_project.entities.Book;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class BookMapper {
    private final ModelMapper modelMapper;
    @Autowired
    public BookMapper(ModelMapper modelMapper){
        this.modelMapper=modelMapper;

    }

    public BookDTO mapFromBookToBookDTO(Book book){
        return this.modelMapper.map(book,BookDTO.class);
    }

    public Book mapFromBookDTOtoBook(BookDTO book){
        return this.modelMapper.map(book,Book.class);
    }

}
