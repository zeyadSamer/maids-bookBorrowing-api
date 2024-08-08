package com.example.maids_project.services;

import com.example.maids_project.entities.Book;
import com.example.maids_project.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService implements IBookService{
    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book createBook(Book book) {
        return this.bookRepository.save(book);
    }

    @Override
    public Book updateBook(Book book,String bookId) {
        Optional<Book> previouslySavedBook =this.bookRepository.findById(bookId);

           return this.bookRepository.save(book);

    }

    @Override
    public Optional<Book> getBook(String bookId) {


         return this.bookRepository.findById(bookId);


    }

    @Override
    public List<Book> getAllBooks() {
        return (List<Book>) this.bookRepository.findAll();
    }

    @Override
    public Book deleteBook(String bookId) {
        Optional<Book> toBeDeleted=this.bookRepository.findById(bookId);

        this.bookRepository.deleteById(bookId);
        return toBeDeleted.get();
    }


    public boolean isExists(String isbn){
        return this.bookRepository.findById(isbn).isPresent();
    }


}
