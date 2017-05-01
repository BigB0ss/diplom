package com.romanov.model;

/**
 * Created by Kirill_Romanov1 on 29-Mar-17.
 */
public class CategorySoft {
    private int id;
    private String description;

    public CategorySoft() {
    }

    public CategorySoft(int id, String description) {
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

}
