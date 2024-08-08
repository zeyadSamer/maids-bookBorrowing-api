package com.example.maids_project.services;

import com.example.maids_project.entities.BorrowingRecord;


public interface IBorrowService {
    public BorrowingRecord createBorrowRecored(String isbn, Long patronId, String borrowDate, String returnDate);
    public BorrowingRecord returnBook(String isbn , Long patronId);
}
