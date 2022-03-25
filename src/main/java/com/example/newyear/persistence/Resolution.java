package com.example.newyear.persistence;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Resolution {

    @Id
    @GeneratedValue
    private long id;

    private String title;
    private String description;
    private int nb_occurency;
    private int frequency;


    public Resolution(String title, String description, int nb_occurency, int frequency) {
        this.title = title;
        this.description = description;
        this.nb_occurency = nb_occurency;
        this.frequency = frequency;
    }


    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNb_occurency() {
        return nb_occurency;
    }

    public void setNb_occurency(int nb_occurency) {
        this.nb_occurency = nb_occurency;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }
}
