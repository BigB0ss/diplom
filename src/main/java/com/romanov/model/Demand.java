package com.romanov.model;

/**
 * Created by Kirill on 21.05.2017.
 */
public class Demand {
    private Integer id;
    private String description;
    private Integer sectionId;
    private Integer technicalTaskId;

    public Demand() {
    }

    public Demand(Integer id, String description, Integer sectionId, Integer technicalTaskId) {
        this.id = id;
        this.description = description;
        this.sectionId = sectionId;
        this.technicalTaskId = technicalTaskId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSectionId() {
        return sectionId;
    }

    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
    }

    public Integer getTechnicalTaskId() {
        return technicalTaskId;
    }

    public void setTechnicalTaskId(Integer technicalTaskId) {
        this.technicalTaskId = technicalTaskId;
    }
}
