package com.example.maids_project.services;

import com.example.maids_project.entities.Patron;

import java.util.List;
import java.util.Optional;

public interface IPatronService {

    public Optional<Patron> getPatron(Long patronId);
    public List<Patron> getAllPatrons();
    public Patron createPatron(Patron patron);
    public Patron updatePatron(Patron patron,Long patronId);
    public Patron deletePatron(Long patronId);

}
