package com.example.maids_project.services;

import com.example.maids_project.entities.Book;


import java.util.List;
import java.util.Optional;


public interface IBookService {


    public Book createBook(Book book);
    public Book updateBook(Book book,String bookId);
    public Optional<Book> getBook(String bookId);
    public List<Book> getAllBooks();
    public Book deleteBook(String bookId);


}
