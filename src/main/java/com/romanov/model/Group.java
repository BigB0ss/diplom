package com.romanov.model;

/**
 * Created by Kirill_Romanov1 on 28-Mar-17.
 */
public class Group {
    private int id;
    private String name;
    private int cathedra;

    public Group() {
    }

    public Group(int id, String name, int cathedra) {
        this.id = id;
        this.name = name;
        this.cathedra = cathedra;
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

    public int getCathedra() {
        return cathedra;
    }

    public void setCathedra(int cathedra) {
        this.cathedra = cathedra;
    }
}
