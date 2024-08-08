package com.example.maids_project.services;

import com.example.maids_project.entities.Book;
import com.example.maids_project.entities.Patron;
import com.example.maids_project.repositories.BookRepository;
import com.example.maids_project.repositories.PatronRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class PatronService implements IPatronService{
    @Autowired
    private PatronRepository patronRepository;

    @Override
    public Patron createPatron(Patron patron) {
        return this.patronRepository.save(patron);
    }

    @Override
    public Patron updatePatron(Patron patron,Long patronId) {
        Optional<Patron> previouslySavedPatron =this.patronRepository.findById(patronId);

        return this.patronRepository.save(patron);

    }

    @Override
    public Optional<Patron> getPatron(Long patronId) {


        return this.patronRepository.findById(patronId);


    }

    @Override
    public List<Patron> getAllPatrons() {
        return (List<Patron>) this.patronRepository.findAll();
    }

    @Override
    public Patron deletePatron(Long patronId) {
        Optional<Patron> toBeDeleted=this.patronRepository.findById(patronId);

        this.patronRepository.deleteById(patronId);
        return toBeDeleted.get();
    }


    public boolean isExists(Long patronId){
        return this.patronRepository.findById(patronId).isPresent();
    }
}
