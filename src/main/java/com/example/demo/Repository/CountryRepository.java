package com.example.demo.Repository;

import com.example.demo.Model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {


    Optional<Country> findByNameAndContinent(String name, String continent);
}
