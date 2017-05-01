package com.romanov.model;

/**
 * Created by Kirill_Romanov1 on 29-Mar-17.
 */
public class Hardware {
    private int id;
    private String description;
    private int idCategory;

    public Hardware() {
    }

    public Hardware(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }
}
