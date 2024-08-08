package com.example.maids_project.repositories;
import com.example.maids_project.entities.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book,String> {


}
