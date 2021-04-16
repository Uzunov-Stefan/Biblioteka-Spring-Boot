package com.example.demo.Service;

import com.example.demo.Model.Author;
import com.example.demo.Repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> getAuthors(){
        return authorRepository.findAll();
    }

    public void addNewAuthor(Author author) {
        Optional<Author> authorOptional = authorRepository.findByNameAndSurnameAndCountry(author.getName(), author.getSurname(), author.getCountry());
        if (authorOptional.isPresent()){
            throw new IllegalStateException("Author already exists!");
        }
        authorRepository.save(author);
        System.out.println(author);
    }
}
