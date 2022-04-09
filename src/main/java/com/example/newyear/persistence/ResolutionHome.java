package com.example.newyear.persistence;

public class ResolutionHome {

    private Resolution resolution;
    private int percent;

    public ResolutionHome(Resolution resolution, int percent) {
        this.resolution = resolution;
        this.percent = percent;
    }

    public Resolution getResolution() {
        return resolution;
    }

    public void setResolution(Resolution resolution) {
        this.resolution = resolution;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }
}
