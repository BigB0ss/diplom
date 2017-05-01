package com.romanov.model;

/**
 * Created by Kirill_Romanov1 on 29-Mar-17.
 */
public class Cathedra {
    private int id;
    private String name;
    private int idFaculty;

    public Cathedra() {
    }

    public Cathedra(int id, String name, int idFaculty) {
        this.id = id;
        this.name = name;
        this.idFaculty = idFaculty;
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

    public int getIdFaculty() {
        return idFaculty;
    }

    public void setIdFaculty(int idFaculty) {
        this.idFaculty = idFaculty;
    }
}
