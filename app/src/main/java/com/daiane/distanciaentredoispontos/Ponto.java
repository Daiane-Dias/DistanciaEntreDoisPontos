package com.daiane.distanciaentredoispontos;

import android.icu.lang.UProperty;

public class Ponto {
    private double latitude;
    private double longitude;
    private double altitude;
     Ponto() {
        this.latitude =0.0;
        this.longitude = 0.0;
        this.altitude = 0.0;
    }
     Ponto(double latitude, double  longitude) {
        this();
        this.latitude =latitude;
        this.longitude = longitude;
    }
    Ponto(double latitude, double  longitude, double altitude) {
        this();
        this.latitude =latitude;
        this.longitude = longitude;
        this.altitude = altitude;
    }
    public String Imprimir(){
         String aux = "Lat: "+ this.latitude+", "+
           "Long: "+this.longitude+", "+
           "Alt: "+this.altitude;
         return aux;
    }
    public String Imprimir2(){
        String aux = "*************************/n"+
                "Latitude: "+ this.latitude+", "+
                "Longitude: "+this.longitude+", "+
                "Altitude: "+this.altitude+
                "*********************************/n";
        return aux;
    }
    public String ImprimirHtml(){
        String aux = "<html>"+
                "Latitude: "+ this.latitude+"<br> "+
                "Longitude: "+this.longitude+"<br>"+
                "Altitude: "+this.altitude+ "<br>"+
                "</html>";
        return aux;
    }


    public double getLatitude(){return this.latitude;}
    public void setLatitude(double latitude){this.latitude = latitude;}
    public double getLongitude(){return this.longitude;}
    public void setLongitude(double longitude){this.longitude = longitude;}
    public double getAltitude(){return this.altitude;}
    public void setAltitude(double altitude){this.altitude = altitude;}

}
