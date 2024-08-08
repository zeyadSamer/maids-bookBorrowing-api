package com.example.maids_project.mappers;

import com.example.maids_project.dtos.BookDTO;
import com.example.maids_project.entities.Book;
import com.example.maids_project.entities.BorrowingRecord;
import com.example.maids_project.entities.BorrowingRecordDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class BorrowingRecordMapper {
    private final ModelMapper modelMapper;

    @Autowired
    public BorrowingRecordMapper(ModelMapper modelMapper){
        this.modelMapper=modelMapper;

    }

    public BorrowingRecordDTO mapFromBorrowingRecordToBorrowingRecordDTO(BorrowingRecord borrowingRecord){
        return this.modelMapper.map(borrowingRecord,BorrowingRecordDTO.class);
    }

    public BorrowingRecord mapFromBorrowingRecordDTOToBorrowingRecord(BorrowingRecordDTO borrowingRecordDTO){
        return this.modelMapper.map(borrowingRecordDTO,BorrowingRecord.class);
    }

}
