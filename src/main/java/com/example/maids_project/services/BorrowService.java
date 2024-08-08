package com.example.maids_project.services;

import com.example.maids_project.entities.Book;
import com.example.maids_project.entities.BorrowingRecord;
import com.example.maids_project.entities.Patron;
import com.example.maids_project.repositories.BookRepository;
import com.example.maids_project.repositories.BorrowingRepository;
import com.example.maids_project.repositories.PatronRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;
@Service
public class BorrowService implements IBorrowService {
    @Autowired
    private BorrowingRepository borrowingRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private PatronRepository patronRepository;


    @Override
    public BorrowingRecord createBorrowRecored(String isbn, Long patronId,String borrowDate, String returnDate) {
        Optional<Book> book =this.bookRepository.findById(isbn);
        Optional<Patron> patron =this.patronRepository.findById(patronId);

        BorrowingRecord borrowingRecord=BorrowingRecord.builder()
                .patron(patron.get())
                .book(book.get())
                .returned(false)
                .borrowingDate(borrowDate)
                .returnDate(returnDate)
                .build();

        return this.borrowingRepository.save(borrowingRecord);


    }

    @Override
    public BorrowingRecord returnBook(String isbn, Long patronId) {
        Optional<BorrowingRecord> borrowingRecord =this.borrowingRepository.findByBookIsbnAndPatronId(isbn,patronId);
        BorrowingRecord updatedBorrowingRecord= borrowingRecord.get();
            updatedBorrowingRecord.setReturned(true);
             this.borrowingRepository.save(updatedBorrowingRecord);

         return  updatedBorrowingRecord;
    }

    public boolean isExists(String isbn, Long patronId){
        return this.borrowingRepository.findByBookIsbnAndPatronId(isbn,patronId).isPresent();
    }


}
