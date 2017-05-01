package com.romanov.model;

/**
 * Created by Kirill_Romanov1 on 29-Mar-17.
 */
public class Faculty {
    private int id;
    private String name;

    public Faculty() {
    }

    public Faculty(int id, String name) {
        this.id = id;
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
}
