package com.example.maids_project.controllers;

import com.example.maids_project.dtos.BookDTO;
import com.example.maids_project.dtos.PatronDTO;
import com.example.maids_project.entities.Book;
import com.example.maids_project.entities.Patron;
import com.example.maids_project.mappers.BookMapper;
import com.example.maids_project.mappers.PatronMapper;
import com.example.maids_project.services.BookService;
import com.example.maids_project.services.PatronService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/patrons")
public class PatronController {


    @Autowired
    public PatronService patronService;
    @Autowired
    public PatronMapper patronMapper;


    @PostMapping("/")
    public ResponseEntity<Patron> createPatron(@RequestBody PatronDTO patronDTO){
        Patron patron=this.patronMapper.mapFromPatronDTOToPatron(patronDTO);
        Patron savedPatron= this.patronService.createPatron(patron);

        return new ResponseEntity<>(savedPatron, HttpStatus.CREATED);

    }

    @GetMapping("/{id}")
    public ResponseEntity<PatronDTO> getPatron(@PathVariable("id") Long patronId){
        Optional<Patron> patron =this.patronService.getPatron(patronId);

        if (patron.isPresent()) {
            System.out.println("present");
            return new ResponseEntity<>(this.patronMapper.mapFromPatronToPatronDTO(patron.get()),HttpStatus.OK);

        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @GetMapping("/")
    public ResponseEntity<List<PatronDTO>>getPatrons(){
        List<Patron> allPatrons = this.patronService.getAllPatrons();
        return new ResponseEntity<>(allPatrons.stream().map(patron -> patronMapper.mapFromPatronToPatronDTO(patron)).toList(), HttpStatus.OK);

    }


    @PutMapping("/{id}")
    public ResponseEntity<PatronDTO > updatePatron(@PathVariable("id") Long id, @RequestBody PatronDTO patronDTO){
        Patron patron=this.patronMapper.mapFromPatronDTOToPatron(patronDTO);

        if(this.patronService.isExists(id)) {
            Patron updatedPatron= this.patronService.updatePatron(patron, id);

            return new ResponseEntity<>(this.patronMapper.mapFromPatronToPatronDTO(updatedPatron), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<PatronDTO > deletePatron( @PathVariable("id") Long id){

        if(this.patronService.isExists(id)) {
            Patron deletedPatron = this.patronService.deletePatron(id);

            return new ResponseEntity<>(this.patronMapper.mapFromPatronToPatronDTO(deletedPatron), HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }




}
