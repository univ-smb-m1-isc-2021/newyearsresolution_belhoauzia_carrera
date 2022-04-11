package com.example.newyear.persistence;

import com.example.newyear.Api.NewYearController;

import javax.persistence.*;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;

@Entity
public class UserRes implements Serializable {


    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserClass user;

    @ManyToOne
    @JoinColumn(name = "resolution_id")
    private Resolution resolution;

    private Date start_date;
    private ArrayList<ResolutionDo> liste;

    public UserRes(UserClass user, Resolution resolution, Date start_date) {
        this.user = user;
        this.resolution = resolution;
        this.start_date = start_date;
        this.liste = new ArrayList<>();
    }

    public UserRes() {
        //JPA
    }


    public Long getId() {
        return id;
    }
    public Long getResId() {
        return resolution.getId();
    }

    public void setId(Long id) {
        this.id = id;
    }
    public UserClass getUser() {
        return user;
    }

    public void setUser(UserClass user) {
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

    public void addResolutionDo(Date d,int nb_do){
        boolean f = false;
        for(int i = 0;i < liste.size(); i++){
            if(isSameDay(liste.get(i).getDate(), d)){
                liste.get(i).setNb_do(liste.get(i).getNb_do()+nb_do);
                f=true;
                break;
            }
        }
        if(!f){
            liste.add(new ResolutionDo(d,nb_do));
        }
    }

    public  boolean isSameDay(Date date1, Date date2) {
        Instant instant1 = date1.toInstant()
                .truncatedTo(ChronoUnit.DAYS);
        Instant instant2 = date2.toInstant()
                .truncatedTo(ChronoUnit.DAYS);
        return instant1.equals(instant2);
    }

    public ArrayList<ResolutionDo> getListe(){return liste;}
    public void addListe(ResolutionDo d){liste.add(d);}

    public boolean isAccomplish(Date d){
        int current_oc = 0;
        for(int i = 0;i < liste.size();i++){
            if(NewYearController.nbDaysBetweenDate(d,liste.get(i).getDate()) < resolution.getFrequency()){
                current_oc += liste.get(i).getNb_do();
                if(current_oc >= resolution.getNb_occurency()){
                    return true;
                }
            }
        }
        return false;
    }

}
