package com.example.newyear.persistence;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class UserRes {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "resolution_id")
    private Resolution resolution;

    private Date start_date;
    private int nb_do;

    public UserRes(User user, Resolution resolution, Date start_date, int nb_do) {
        this.user = user;
        this.resolution = resolution;
        this.start_date = start_date;
        this.nb_do = nb_do;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Resolution getResolution() {
        return resolution;
    }

    public void setResolution(Resolution resolution) {
        this.resolution = resolution;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public int getNb_do() {
        return nb_do;
    }

    public void setNb_do(int nb_do) {
        this.nb_do = nb_do;
    }
}
