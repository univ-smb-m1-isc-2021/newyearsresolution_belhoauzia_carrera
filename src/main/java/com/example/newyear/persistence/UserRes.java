package com.example.newyear.persistence;

import javax.persistence.*;
import java.io.Serializable;
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
            if(liste.get(i).getDate().getDay() == d.getDay()){
                liste.get(i).setNb_do(liste.get(i).getNb_do()+nb_do);
                f=true;
                break;
            }
        }
        if(!f){
            liste.add(new ResolutionDo(d,nb_do));
        }
    }

    public ArrayList<ResolutionDo> getListe(){return liste;}

}
