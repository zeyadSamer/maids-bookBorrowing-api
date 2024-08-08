package com.example.maids_project.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BorrowingRecordDTO {

    private Long id;

    private Book book;

    private Patron patron;

    private String borrowingDate;

    private String returnDate;

    private boolean returned;

    // Getters and Setters
}