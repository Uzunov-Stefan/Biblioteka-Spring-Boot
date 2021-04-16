package com.example.demo;

import com.example.demo.Model.Author;
import com.example.demo.Model.Book;
import com.example.demo.Model.Category;
import com.example.demo.Model.Country;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


/*
============================
== 166007 - Stefan Uzunov ==
============================
 */


@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }



}
