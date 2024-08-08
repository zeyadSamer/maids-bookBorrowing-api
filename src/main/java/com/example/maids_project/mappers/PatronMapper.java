package com.example.maids_project.mappers;

import com.example.maids_project.dtos.BookDTO;
import com.example.maids_project.dtos.PatronDTO;
import com.example.maids_project.entities.Book;
import com.example.maids_project.entities.Patron;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class PatronMapper {
    private final ModelMapper modelMapper;
    @Autowired
    public PatronMapper(ModelMapper modelMapper){
        this.modelMapper=modelMapper;

    }

    public PatronDTO mapFromPatronToPatronDTO(Patron patron){
        return this.modelMapper.map(patron,PatronDTO.class);
    }

    public Patron mapFromPatronDTOToPatron(PatronDTO patronDTO){
        return this.modelMapper.map(patronDTO,Patron.class);
    }

}
