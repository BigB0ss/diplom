package com.romanov.model;

import java.util.Date;

/**
 * Created by Kirill on 27.05.2017.
 */
public class Task {
    private  long id;
    private String description;
    private Date date;

    public Task() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
