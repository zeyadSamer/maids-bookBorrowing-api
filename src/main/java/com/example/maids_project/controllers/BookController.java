package com.example.maids_project.controllers;


import com.example.maids_project.dtos.BookDTO;
import com.example.maids_project.entities.Book;
import com.example.maids_project.mappers.BookMapper;
import com.example.maids_project.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    public BookService bookService;
    @Autowired
    public BookMapper bookMapper;


    @PostMapping("/")
    public ResponseEntity<Book> createBook(@RequestBody BookDTO bookDTO){
        Book book=this.bookMapper.mapFromBookDTOtoBook(bookDTO);
        Book savedBook= this.bookService.createBook(book);

        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);

    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBook(@PathVariable("id") String bookId){
        Optional<Book> book =this.bookService.getBook(bookId);
        System.out.println(book);

        if (book.isPresent()) {
            System.out.println("present");
            return new ResponseEntity<>(this.bookMapper.mapFromBookToBookDTO(book.get()),HttpStatus.OK);

        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @GetMapping("/")
    public ResponseEntity<List<BookDTO> >getBooks(){
        List<Book> allBooks = this.bookService.getAllBooks();
        return new ResponseEntity<>(allBooks.stream().map(book -> bookMapper.mapFromBookToBookDTO(book)).toList(), HttpStatus.OK);

    }


    @PutMapping("/{id}")
    public ResponseEntity<BookDTO > updateBook(@PathVariable("id") String isbn, @RequestBody BookDTO bookDTO){
        Book book=this.bookMapper.mapFromBookDTOtoBook(bookDTO);

      if(this.bookService.isExists(book.getIsbn())) {
          Book updatedBook = this.bookService.updateBook(book, isbn);

          return new ResponseEntity<>(this.bookMapper.mapFromBookToBookDTO(updatedBook), HttpStatus.CREATED);
      }
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<BookDTO > deleteBook(@PathVariable("id") String isbn){


        if(this.bookService.isExists(isbn)) {
            Book deletedBook = this.bookService.deleteBook( isbn);

            return new ResponseEntity<>(this.bookMapper.mapFromBookToBookDTO(deletedBook), HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }







}
