package com.example.adama.findmypharmacie.models;

/**
 * Created by adama on 16/03/2016.
 */
public class Pharmacie {
    private String name, telephone, address, emei;
    private String latitude, longitude, accracy;

    public Pharmacie(String name, String telephone, String address, String emei, String latitude, String longitude, String accracy) {
        this.name = name;
        this.telephone = telephone;
        this.address = address;
        this.emei = emei;
        this.latitude = latitude;
        this.longitude = longitude;
        this.accracy = accracy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmei() {
        return emei;
    }

    public void setEmei(String emei) {
        this.emei = emei;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getAccracy() {
        return accracy;
    }

    public void setAccracy(String accracy) {
        this.accracy = accracy;
    }
}
