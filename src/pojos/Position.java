/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojos;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author user
 */
public class Position {
    int posID, tripID;
    double latitude, longitude, altitude,speed,rpm,fuel;
    Date timeStamp;
    
    public Position(int posID, int tripID, double latitude, double longitude, double altitude, String timeStamp) {
        this.posID = posID;
        this.tripID = tripID;
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
        this.speed = speed;
        this.rpm = rpm;
        this.fuel = fuel;
        
        DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        try 
            {this.timeStamp = (Date)formatter.parse(timeStamp);}
        catch (ParseException ex) 
            {System.err.println("error in converting string to date of format HH:mm:ss, error is "+ex);}
        
    }
    
    public Position(int posID, int tripID, double latitude, double longitude, double altitude, double speed, double rpm, double fuel) {
        this.posID = posID;
        this.tripID = tripID;
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
        this.speed = speed;
        this.rpm = rpm;
        this.fuel = fuel;
    }

    public Position() {
    }

    public int getPosID() {
        return posID;
    }

    public void setPosID(int posID) {
        this.posID = posID;
    }

    public int getTripID() {
        return tripID;
    }

    public void setTripID(int tripID) {
        this.tripID = tripID;
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

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        DateFormat formatter = new SimpleDateFormat("yy-mm-dd hh:mm:ss");
        try 
        {
            this.timeStamp = (Date)formatter.parse(timeStamp);
        }
        catch (ParseException ex) 
        {
            System.err.println("error in converting string to date of format HH:mm:ss, error is "+ex);
        }
    }
    
    public double getGeoDistance(Position secondPosition)
    {
        double lat1 = this.latitude ;
	double lon1 = this.longitude ;
        double el1 = this.altitude;
 
	double lat2 = secondPosition.latitude ;
	double lon2 = secondPosition.longitude ;
        double el2 = secondPosition.altitude;
        
        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        double height = el1 - el2;

        distance = Math.pow(distance, 2) + Math.pow(height, 2);

        return Math.sqrt(distance);
    }

    @Override
    public String toString() {
        return "Position{" + "posID=" + posID + ", tripID=" + tripID + ", latitude=" + latitude + ", longitude=" + longitude + ", altitude=" + altitude + ", speed=" + speed + ", rpm=" + rpm + ", fuel=" + fuel + ", timeStamp=" + timeStamp + '}';
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getRpm() {
        return rpm;
    }

    public void setRpm(double rpm) {
        this.rpm = rpm;
    }

    public double getFuel() {
        return fuel;
    }

    public void setFuel(double fuel) {
        this.fuel = fuel;
    }

    
    
    
    
    
}
