package com.example.quizgame.Database;

import androidx.annotation.NonNull;

public class Category {
    public static final int TECHNOLOGY = 1;
    public static final int MUSIC = 2;
    public static final int SPORT = 3;
    public static final int HISTORY = 4;
    public static final int GAME = 5;
    public static final int SCIENCE = 6;



    private int id;
    private String name;

    public Category(){

    }

    public Category(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return getName();
    }
}
