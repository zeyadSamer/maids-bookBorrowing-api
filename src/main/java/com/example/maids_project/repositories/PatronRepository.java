package com.example.maids_project.repositories;

import com.example.maids_project.entities.Patron;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatronRepository extends CrudRepository<Patron, Long> {







}
