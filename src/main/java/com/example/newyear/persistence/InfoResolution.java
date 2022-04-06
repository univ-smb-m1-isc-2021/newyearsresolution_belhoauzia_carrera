package com.example.newyear.persistence;

import java.text.SimpleDateFormat;
import java.util.Date;

public class InfoResolution {
    private String start_date;
    private int nb_do;

    public InfoResolution(Date start_date,int nb_do,String pattern){
        SimpleDateFormat format1 = new SimpleDateFormat(pattern);
        this.start_date = format1.format(start_date);
        this.nb_do = nb_do;
    }

    public String getStart_date() {
        return start_date;
    }

    public int getNb_do() {
        return nb_do;
    }
}
