package com.example.demo.model;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "LocationStates")
public class LocationStates {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int countryid;
    private String state;
    private String country;
    private int latestTotalDeaths; // Corrected variable name here
    private int diffFromPrevay;

    public int getCountryid() {
        return countryid;
    }

    public void setCountryid(int countryid) {
        this.countryid = countryid;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getLatestTotalDeaths() {
        return latestTotalDeaths;
    }

    public void setLatestTotalDeaths(int latestTotalDeaths) { // Corrected method name here
        this.latestTotalDeaths = latestTotalDeaths;
    }

    public int getDiffFromPrevay() {
        return diffFromPrevay;
    }

    public void setDiffFromPrevay(int diffFromPrevay) {
        this.diffFromPrevay = diffFromPrevay;
    }

    @Override
    public String toString() {
        return "LocationStates [countryid=" + countryid + ", state=" + state + ", country=" + country
                + ", latestTotalDeaths=" + latestTotalDeaths + ", diffFromPrevay=" + diffFromPrevay + "]";
    }
}
