package com.example.maids_project.entities;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="books")

public class Book {

        @Id
        private String isbn;
        private String title;
        private String author;
        private String publicationYear;


//        @OneToMany(mappedBy = "book")
//        private List<BorrowingRecord> borrowingRecords;

        // Getters and Setters
    }





