package com.example.maids_project.repositories;

import com.example.maids_project.entities.Book;
import com.example.maids_project.entities.BorrowingRecord;
import com.example.maids_project.entities.Patron;

public class TestingUtils {

    public static   Book createTestBook(){
        return Book.builder().isbn("123").title("sharks in the sea")
                .author("zeyad").publicationYear("2023").build();

    }
    public static Patron createTestPatron(){
        return Patron.builder().Id(1L).phoneNumber("01115995531").name("zeyad").build();
    }


    public static BorrowingRecord createTestBorrowingRecord(Book bookToBeBorrowed,Patron patron){

        return BorrowingRecord.builder().id(1L)
                .returned(false)
                .book(bookToBeBorrowed)
                .patron(patron)
                .borrowingDate("1/2/2023")
                .returnDate("1/2/2024").build();


    }


}
