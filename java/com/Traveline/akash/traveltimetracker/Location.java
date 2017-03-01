package com.Traveline.akash.traveltimetracker;

import com.google.android.gms.maps.model.LatLng;

import java.util.Vector;

public class Location {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    private String name;
    private int _id;
    private double lat;
    private double lng;

}





