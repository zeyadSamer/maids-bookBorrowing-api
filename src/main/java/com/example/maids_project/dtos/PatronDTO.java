package com.example.maids_project.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PatronDTO {
    private Long  Id;
    private  String name;
    private String phoneNumber;



}
