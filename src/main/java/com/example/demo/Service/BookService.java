package com.example.demo.Service;

import com.example.demo.Model.Author;
import com.example.demo.Model.Book;
import com.example.demo.Model.Category;
import com.example.demo.Model.Country;
import com.example.demo.Repository.AuthorRepository;
import com.example.demo.Repository.BookRepository;
import com.example.demo.Repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CountryRepository countryRepository;

    @Autowired
    public BookService(BookRepository bookRepository, AuthorRepository authorRepository, CountryRepository countryRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
    }

    public List<Book> getBooks(){
        return bookRepository.findAll();
    }

    public void addNewBook(Book book) {
        Country country = book.getAuthor().getCountry();
        Optional<Country> countryOptional;
        if (countryRepository.findByNameAndContinent(country.getName(), country.getContinent()).isPresent()){
            countryOptional = countryRepository.findByNameAndContinent(country.getName(), country.getContinent());
            if (countryOptional.isPresent()){
                book.getAuthor().setCountry(countryOptional.orElseThrow());
            }
        } else {
            countryRepository.save(country);
        }

        Author author = book.getAuthor();
        Optional<Author> authorOptional;
        if (authorRepository.findByNameAndSurnameAndCountry(author.getName(),author.getSurname(),author.getCountry()).isPresent()){
            authorOptional = authorRepository.findByNameAndSurnameAndCountry(author.getName(),author.getSurname(),author.getCountry());
            if (authorOptional.isPresent()){
                book.setAuthor(authorOptional.orElseThrow());
            }
        } else{
            authorRepository.save(author);
        }

        if (bookRepository.findByNameAndAuthor(book.getName(),book.getAuthor()).isPresent()){
            throw new IllegalStateException("Book already exists");
        }else{
            bookRepository.save(book);
        }

        System.out.println(book);
    }

    public void deleteBookById(Long bookId) {
        boolean exists = bookRepository.existsById(bookId);
        if (!exists){
            throw new IllegalStateException("Book with id:"+ bookId + " does not exist");
        }

        bookRepository.deleteById(bookId);
    }

    @Transactional
    public void updateBook(Long bookId, String bookName, Category bookCategory, String authName, String authSurname, String counName, String counContinent, Integer availableCopies) {
        Book book = bookRepository.findById(bookId).orElseThrow(() ->
                new IllegalStateException("Book with id:"+ bookId + "does not exist"));

        if (bookName != null && bookName.length() > 0 && !Objects.equals(book.getName(), bookName)){
            book.setName(bookName);
        }

        if (bookCategory != null && !Objects.equals(book.getCategory(), bookCategory)){
            book.setCategory(bookCategory);
        }

        /*
            country = countryRepo.find(cName,cContinent)
            if country exists
                book.author.country = country

            author = authorRepo.find(aName,aSurname, country)
            if author exists
                book.author = author

         */
        if (counName == null ){
            counName = book.getAuthor().getCountry().getName();
        }

        if (counContinent == null){
            counContinent = book.getAuthor().getCountry().getContinent();
        }

        if (authName == null){
            authName = book.getAuthor().getName();
        }

        if (authSurname == null){
            authSurname = book.getAuthor().getSurname();
        }

        if (countryRepository.findByNameAndContinent(counName,counContinent).isPresent()){
            Country country = countryRepository.findByNameAndContinent(counName,counContinent).orElseThrow();
            book.getAuthor().setCountry(country);
        } else{
            if (counName != null && counName.length() > 0 && !Objects.equals(book.getAuthor().getCountry().getName(), counName)){
                book.getAuthor().getCountry().setName(counName);
            }

            if (counContinent != null && counContinent.length() > 0 && !Objects.equals(book.getAuthor().getCountry().getContinent(), counContinent)){
                book.getAuthor().getCountry().setContinent(counContinent);
            }
        }

        if (authorRepository.findByNameAndSurnameAndCountry(authName, authSurname, book.getAuthor().getCountry()).isPresent()) {
            Author author = authorRepository.findByNameAndSurnameAndCountry(authName, authSurname, book.getAuthor().getCountry()).orElseThrow();
            book.setAuthor(author);
        } else {
            if (authName != null && authName.length() > 0 && !Objects.equals(book.getAuthor().getName(), authName)){
                book.getAuthor().setName(authName);
            }

            if (authSurname != null && authSurname.length() > 0 && !Objects.equals(book.getAuthor().getSurname(), authSurname)){
                book.getAuthor().setSurname(authSurname);
            }
        }

        if (availableCopies != null && !Objects.equals(book.getAvailableCopies(), availableCopies)){
            book.setAvailableCopies(availableCopies);
        }
    }
}
