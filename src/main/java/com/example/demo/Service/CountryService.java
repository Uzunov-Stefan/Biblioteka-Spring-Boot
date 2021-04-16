package com.example.demo.Service;

import com.example.demo.Model.Country;
import com.example.demo.Repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryService {

    private final CountryRepository countryRepository;

    @Autowired
    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public List<Country> getCountries(){
        return countryRepository.findAll();
    }

    public void addNewCountry(Country country) {
        Optional<Country> countryOptional = countryRepository.findByNameAndContinent(country.getName(), country.getContinent());
        if (countryOptional.isPresent()){
            throw new IllegalStateException("Country already exists!");
        }
        countryRepository.save(country);
        System.out.println(country);
    }


}
