package com.example.newyear.persistence;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class Timeline {


    @Id
    private Date id;

    private int nb_resolution;

    @ManyToOne
    private User user_id;

    public Timeline(Date id, int nb_resolution, User user_id) {
        this.id = id;
        this.nb_resolution = nb_resolution;
        this.user_id = user_id;
    }

    public Timeline() {
        //JPA
    }

    public Date getId() {
        return id;
    }

    public void setId(Date id) {
        this.id = id;
    }

    public int getNb_resolution() {
        return nb_resolution;
    }

    public void setNb_resolution(int nb_resolution) {
        this.nb_resolution = nb_resolution;
    }

    public User getUser_id() {
        return user_id;
    }

    public void setUser_id(User user_id) {
        this.user_id = user_id;
    }
}
