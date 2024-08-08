package com.example.maids_project.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="patrons")
public class Patron {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long  Id;
    private  String name;
    private String phoneNumber;





}
