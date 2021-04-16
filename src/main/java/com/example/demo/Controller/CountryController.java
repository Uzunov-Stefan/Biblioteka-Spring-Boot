package com.example.demo.Controller;

import com.example.demo.Model.Country;
import com.example.demo.Service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/country")
public class CountryController {

    private final CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public List<Country> getCountries(){
        return countryService.getCountries();
    }

    @PostMapping
    public void registerNewCountry(@RequestBody Country country){
        countryService.addNewCountry(country);
    }
}
