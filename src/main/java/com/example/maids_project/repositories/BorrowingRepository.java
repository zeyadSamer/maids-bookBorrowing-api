package com.example.maids_project.repositories;

import com.example.maids_project.entities.Book;
import com.example.maids_project.entities.BorrowingRecord;
import com.example.maids_project.entities.Patron;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface BorrowingRepository extends CrudRepository<BorrowingRecord, Long> {

    @Query("SELECT br FROM BorrowingRecord br WHERE br.book.isbn = :isbn AND br.patron.id = :patronId AND br.returned = false")
    public Optional<BorrowingRecord> findByBookIsbnAndPatronId(@Param("isbn") String isbn, @Param("patronId") Long patronId);

    @Query("SELECT br FROM BorrowingRecord br WHERE br.book.isbn = :isbn AND br.patron.id = :patronId AND br.returned = true")
    public Optional<BorrowingRecord> findReturnedRecords(@Param("isbn") String isbn, @Param("patronId") Long patronId);

}

