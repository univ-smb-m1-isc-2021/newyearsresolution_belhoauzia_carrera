package com.example.newyear.persistence;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class ResolutionDo implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    private Date date;
    private int nb_do;
    private String show_date;

    public ResolutionDo(Date date, int nb_do) {
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        this.date = date;
        this.nb_do = nb_do;
        this.show_date = format1.format(this.date);
    }

    public ResolutionDo() {
        //JPA
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getNb_do() {
        return nb_do;
    }

    public void setNb_do(int nb_do) {
        this.nb_do = nb_do;
    }

    public String getShow_date() {
        return show_date;
    }

    public Long getId() {
        return id;
    }
}
