package com.romanov.model;

/**
 * Created by Kirill_Romanov1 on 29-Mar-17.
 */
public class Software {
    private int id;
    private String description;
    private int categorySoft;

    public Software() {
    }

    public Software(int id, String description, int categorySoft) {
        this.id = id;
        this.description = description;
        this.categorySoft = categorySoft;
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

    public int getCategorySoft() {
        return categorySoft;
    }

    public void setCategorySoft(int categorySoft) {
        this.categorySoft = categorySoft;
    }

    @Override
    public String toString() {
        return "Software{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", categorySoft=" + categorySoft +
                '}';
    }
}
