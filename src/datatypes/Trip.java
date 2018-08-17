/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datatypes;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Trip 
{
    int tripID, beaglebone;
    Date date;

    /**
     * 
     * @param tripID
     * @param beaglebone
     * @param date the date passed should be a string in the format "YY-mm-dd HH:mm:ss" 
     *             which is converted to a Date type by using 
     *             DateFormat formatter = new SimpleDateFormat("yy-mm-dd hh:mm:ss"); date = (Date)formatter.parse(stringDate);
     */
    public Trip(int tripID, int beaglebone, String date) {
        this.tripID = tripID;
        this.beaglebone = beaglebone;
        DateFormat formatter = new SimpleDateFormat("yy-mm-dd hh:mm:ss");
        try 
        {
            this.date = (Date)formatter.parse(date);
        }
        catch (ParseException ex) 
        {
            System.err.println("error in converting string to date of format HH:mm:ss, error is "+ex);
        }
    }

    public int getTripID() {
        return tripID;
    }

    public void setTripID(int tripID) {
        this.tripID = tripID;
    }

    public int getBeaglebone() {
        return beaglebone;
    }

    public void setBeaglebone(int beaglebone) {
        this.beaglebone = beaglebone;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(String date) {
        DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        try 
        {
            this.date = (Date)formatter.parse(date);
        }
        catch (ParseException ex) 
        {
            System.err.println("error in converting string to date of format HH:mm:ss, error is "+ex);
        }
    }

    @Override
    public String toString() {
        return "Trip{" + "tripID=" + tripID + ", beaglebone=" + beaglebone + ", date=" + date + '}';
    }
    
    
    
    
}
