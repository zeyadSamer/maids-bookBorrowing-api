package com.example.maids_project.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class  BookDTO {
    private String isbn;
    private String title;
    private String author;
    private String publicationYear;

}
