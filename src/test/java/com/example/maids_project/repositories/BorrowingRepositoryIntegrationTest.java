package com.example.maids_project.repositories;


import com.example.maids_project.entities.Book;
import com.example.maids_project.entities.BorrowingRecord;
import com.example.maids_project.entities.Patron;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode =DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BorrowingRepositoryIntegrationTest {
    @Autowired
    public BorrowingRepository borrowingRepository;
    @Autowired
    public BookRepository bookRepository;
    @Autowired
    public PatronRepository patronRepository;



    @Test
    public void testThatBorrowingRecordsCanBeRecalled(){
        Book book = TestingUtils.createTestBook();
        Patron patron = TestingUtils.createTestPatron();
        this.bookRepository.save(book);
        this.patronRepository.save(patron);

        BorrowingRecord borrowingRecord=TestingUtils.createTestBorrowingRecord(book,patron);
        this.borrowingRepository.save(borrowingRecord);

        BorrowingRecord recalledBorrowingRecord= this.borrowingRepository.findByBookIsbnAndPatronId(book.getIsbn(), patron.getId()).get();

        assertThat(recalledBorrowingRecord).isEqualTo(borrowingRecord);

    }





    @Test
    public void testThatBookCanBeBorrowedByPatron(){

        Book book = TestingUtils.createTestBook();
        Patron patron = TestingUtils.createTestPatron();
        this.bookRepository.save(book);
        this.patronRepository.save(patron);

        Optional<Book> recalledBook = this.bookRepository.findById(book.getIsbn());
        Optional<Patron> recalledPatron = this.patronRepository.findById(patron.getId());

        BorrowingRecord borrowingRecord =TestingUtils.createTestBorrowingRecord(book,patron);

        this.borrowingRepository.save(borrowingRecord);

        Optional<BorrowingRecord> recalledBorrowingRecord=this.borrowingRepository.findByBookIsbnAndPatronId(book.getIsbn(),patron.getId());

        assertThat(recalledBorrowingRecord.get()).isEqualTo(borrowingRecord);

    }

    @Test
    public void testThatBookCanBeReturned(){
        Book book = TestingUtils.createTestBook();
        Patron patron = TestingUtils.createTestPatron();
        this.bookRepository.save(book);
        this.patronRepository.save(patron);
        BorrowingRecord borrowingRecord =TestingUtils.createTestBorrowingRecord(book,patron);

        this.borrowingRepository.save(borrowingRecord);

        borrowingRecord.setReturned(true);
        this.borrowingRepository.save(borrowingRecord);

        Optional<BorrowingRecord> recalledBorrowingRecord=this.borrowingRepository.findReturnedRecords(book.getIsbn(),patron.getId());

        assertThat(recalledBorrowingRecord.get().isReturned()).isEqualTo(true);
        assertThat(recalledBorrowingRecord.get().getBook()).isEqualTo(book);
        assertThat(recalledBorrowingRecord.get().getPatron()).isEqualTo(patron);


    }





}
