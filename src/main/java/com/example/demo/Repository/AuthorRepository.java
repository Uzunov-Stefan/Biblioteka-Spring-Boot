package com.example.demo.Repository;

import com.example.demo.Model.Author;
import com.example.demo.Model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    Optional<Author> findByNameAndSurnameAndCountry(String name, String surname, Country country);

}
