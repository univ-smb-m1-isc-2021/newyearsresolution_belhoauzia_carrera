package com.example.newyear.persistence;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class ResolutionDo implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    private Date date;
    private int nbdo;
    private String showdate;

    public ResolutionDo(Date date, int nbdo) {
        SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
        this.date = date;
        this.nbdo = nbdo;
        this.showdate = format1.format(this.date);
    }
    public ResolutionDo(String str, int nbdo) throws ParseException {
        Date date = createDate(str);
        SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
        this.date = date;
        this.nbdo = nbdo;
        this.showdate = format1.format(this.date);
    }

    public ResolutionDo() {
        //JPA
    }

    public Date createDate(String d) throws ParseException {
        return new SimpleDateFormat("dd/MM/yyyy").parse(d);
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getNbdo() {
        return nbdo;
    }

    public void setNbdo(int nbdo) {
        this.nbdo = nbdo;
    }

    public String getShowdate() {
        return showdate;
    }

    public Long getId() {
        return id;
    }
}
