package com.example.demo.Controller;

import com.example.demo.Model.Book;
import com.example.demo.Model.Category;
import com.example.demo.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/book")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getBooks(){
        return bookService.getBooks();
    }

    @PostMapping
    public void registerNewBook(@RequestBody Book book){
        bookService.addNewBook(book);
    }

    @DeleteMapping("{bookId}")
    public void deleteBook(@PathVariable("bookId") Long bookId){
        bookService.deleteBookById(bookId);
    }

    @PutMapping("{bookId}")
    public void updateBook(@PathVariable("bookId") Long bookId,
                           @RequestParam(required = false) String bookName,
                           @RequestParam(required = false) Category bookCategory,
                           @RequestParam(required = false) String authName,
                           @RequestParam(required = false) String authSurname,
                           @RequestParam(required = false) String counName,
                           @RequestParam(required = false) String counContinent,
                           @RequestParam(required = false) Integer availableCopies){
        bookService.updateBook(bookId,bookName, bookCategory, authName, authSurname, counName, counContinent, availableCopies);
    }
}
