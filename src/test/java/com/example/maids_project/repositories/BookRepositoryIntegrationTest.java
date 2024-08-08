package com.example.maids_project.repositories;

import com.example.maids_project.entities.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Java6Assertions.assertThat;


@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode =DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookRepositoryIntegrationTest {

    @Autowired
    public BookRepository bookRepository;

    private Book saveTestBook(){
        Book book=TestingUtils.createTestBook();
        this.bookRepository.save(book);
        return book;
    }

    @Test
    public void testThatMultipleBooksCanBeRecalled(){
        Book book=saveTestBook();
        List<Book> books=(List<Book>) this.bookRepository.findAll();
        assertThat(books.size()).isEqualTo(1);

    }

    @Test
    public void testThatBookCanBeCreatedAndRecalled(){
       Book book=saveTestBook();

        Optional<Book> recalledBook=this.bookRepository.findById(book.getIsbn());
        System.out.println(recalledBook);
        assertThat(recalledBook.isPresent()).isEqualTo(true);
        recalledBook.ifPresent(value -> assertThat(value).isEqualTo(book));


    }

    @Test
    public void testThatBookCanBeDeleted(){
        Book book=saveTestBook();

        this.bookRepository.delete(book);

        Optional<Book> recalledBook=this.bookRepository.findById(book.getIsbn());
        assertThat(recalledBook.isEmpty()).isEqualTo(true);
    }

    @Test
    public void testThatBookCanBeUpdated(){
        Book book=saveTestBook();

        book.setAuthor("tom");
        this.bookRepository.save(book);
        Optional<Book> recalledBook=this.bookRepository.findById(book.getIsbn());
        assertThat(recalledBook.get().getAuthor()).isEqualTo("tom");


    }



}
