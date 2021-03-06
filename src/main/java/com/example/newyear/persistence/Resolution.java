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
    private int nboccurency;
    private int frequency;


    public Resolution(String title, String description, int nboccurency, int frequency) {
        this.title = title;
        this.description = description;
        this.nboccurency = nboccurency;
        this.frequency = frequency;
    }

    public Resolution() {
        //JPA
    }


    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public int getNboccurency() {
        return nboccurency;
    }

    public void setNboccurency(int nboccurency) {
        this.nboccurency = nboccurency;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }
}
