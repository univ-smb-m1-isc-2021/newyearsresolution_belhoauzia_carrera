package com.example.newyear.persistence;

public class PopularityResolution {
    private Resolution r;
    private int popularity;

    public PopularityResolution(Resolution r, int popularity) {
        this.r = r;
        this.popularity = popularity;
    }

    public Resolution getR() {
        return r;
    }

    public void setR(Resolution r) {
        this.r = r;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }
}
