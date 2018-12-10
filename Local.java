package com.example.prof803.mapaapplication;

public class Local {

    private String id;
    private String endereco;
    private double latitude;
    private double longitude;

    public Local(String id, String endereco, double latitude, double longitude) {
        this.id = id;
        this.endereco = endereco;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Local() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
