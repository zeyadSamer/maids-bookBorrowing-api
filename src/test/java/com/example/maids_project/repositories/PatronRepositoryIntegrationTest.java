package com.example.maids_project.repositories;

import com.example.maids_project.entities.Patron;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode =DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class PatronRepositoryIntegrationTest {

    @Autowired
    public PatronRepository patronRepository;


    private Patron saveTestPatron(){
        Patron patron =TestingUtils.createTestPatron();

        patronRepository.save(patron);
        return patron;
    }


    @Test
    public void testThatMultiplePatronsCanBeRecalled(){

        Patron patron=this.saveTestPatron();
        List<Patron> patrons=(List<Patron>) this.patronRepository.findAll();

        assertThat(patrons.size()).isEqualTo(1);


    }



    @Test
    public void testThatPatronCanBeCreatedAndRecalled(){
        Patron patron=this.saveTestPatron();


        Optional<Patron> recalledPatron=patronRepository.findById(patron.getId());
        assertThat(recalledPatron).isPresent();
        assertThat(recalledPatron.get()).isEqualTo(patron);


    }
    @Test
    public void testThatPatronCanBeDeleted(){
        Patron patron=this.saveTestPatron();


        patronRepository.delete(patron);

        Optional<Patron> recalledPatron =patronRepository.findById(patron.getId());

        assertThat(recalledPatron).isNotPresent();
    }

    @Test
    public void testThatPatronCanBeUpdated(){
       Patron patron= this.saveTestPatron();

       patron.setName("Ahmed");
       patronRepository.save(patron);
        Optional<Patron> recalledPatron =patronRepository.findById(patron.getId());

        assertThat(recalledPatron.get().getName()).isEqualTo("Ahmed");
        assertThat(recalledPatron.get()).isEqualTo(patron);


    }
    @Test
    public void testThatPatronCanBorrowABook(){
        Patron patron =this.saveTestPatron();




    }



}
