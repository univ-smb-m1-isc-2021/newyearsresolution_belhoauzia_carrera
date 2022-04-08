package com.example.newyear.persistence;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class InfoResolution {
    private String start_date;
    private ArrayList<ResolutionDo> liste;
    private boolean isValide;

    public InfoResolution(Date start_date,ArrayList<ResolutionDo> liste,String pattern,Boolean isValide){
        SimpleDateFormat format1 = new SimpleDateFormat(pattern);
        this.start_date = format1.format(start_date);
        this.liste = liste;
        this.isValide = isValide;
    }

    public boolean isValide() {
        return isValide;
    }

    public String getStart_date() {
        return start_date;
    }

    public ArrayList<ResolutionDo> getListe() {
        return liste;
    }
}
