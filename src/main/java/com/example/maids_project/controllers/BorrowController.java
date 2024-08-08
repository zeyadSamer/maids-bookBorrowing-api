package com.example.maids_project.controllers;

import com.example.maids_project.entities.BorrowingRecord;
import com.example.maids_project.entities.BorrowingRecordDTO;
import com.example.maids_project.mappers.BorrowingRecordMapper;
import com.example.maids_project.services.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BorrowController {

    @Autowired
    private BorrowService borrowService;
    @Autowired
    private BorrowingRecordMapper borrowingRecordMapper;

    @PostMapping("/borrow/{bookId}/patron/{patronId}")
    public ResponseEntity<BorrowingRecordDTO> createBorrowRecordForPattron(@PathVariable("bookId") String isbn, @PathVariable("patronId")Long patronId, @RequestBody()BorrowingRecordDTO borrowingRecordDTO){
        BorrowingRecord borrowingRecord=this.borrowService.createBorrowRecored(isbn,patronId,borrowingRecordDTO.getBorrowingDate(), borrowingRecordDTO.getReturnDate());
        BorrowingRecordDTO recalledBorrowingRecordDTO=this.borrowingRecordMapper.mapFromBorrowingRecordToBorrowingRecordDTO(borrowingRecord);
        return new ResponseEntity<>(recalledBorrowingRecordDTO, HttpStatus.CREATED);
    }

    @PutMapping("/return/{bookId}/patron/{patronId}")
    public ResponseEntity<BorrowingRecordDTO> createBorrowRecordForPattron(@PathVariable("bookId") String isbn, @PathVariable("patronId")Long patronId){
        if(this.borrowService.isExists(isbn,patronId)) {

            BorrowingRecord borrowingRecord = this.borrowService.returnBook(isbn, patronId);

            BorrowingRecordDTO recalledBorrowingRecordDTO = this.borrowingRecordMapper.mapFromBorrowingRecordToBorrowingRecordDTO(borrowingRecord);


            return new ResponseEntity<>(recalledBorrowingRecordDTO, HttpStatus.ACCEPTED);

        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }






}
