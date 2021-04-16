package com.example.demo.Config;

import com.example.demo.Model.Author;
import com.example.demo.Model.Book;
import com.example.demo.Model.Category;
import com.example.demo.Model.Country;
import com.example.demo.Repository.AuthorRepository;
import com.example.demo.Repository.BookRepository;
import com.example.demo.Repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Optional;

@Configuration
public class BookConfig {

    @Bean
    CommandLineRunner commandLineRunner(BookRepository bookRepository, AuthorRepository authorRepository, CountryRepository countryRepository){
        return args -> {
            Country country = new Country("Makedonija","Evropa");
            Country country1 = new Country("Srbija","Evropa");

            countryRepository.saveAll(List.of(country,country1));

            Author author = new Author("Risto","Razbiras", country);
            Author author1 = new Author("daads","d", country1);

            authorRepository.saveAll(List.of(author, author1));

            Book book = new Book("Buunime po edna, Razbiras", Category.CLASSICS, author, 100);
            Book book1 = new Book("dddd", Category.BIOGRAPHY,author1,55);

            bookRepository.saveAll(List.of(book, book1));
        };
    }
}
