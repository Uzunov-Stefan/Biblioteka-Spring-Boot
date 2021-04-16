package com.example.demo.Model;

public enum Category {
    NOVEL,
    THRILER,
    HISTORY,
    FANTASY,
    BIOGRAPHY,
    CLASSICS,
    DRAMA;

    public String getCategory(){
        return name();
    }

}
