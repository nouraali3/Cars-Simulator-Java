
package structures;



import pojos.Position;
import pojos.Trip;
import java.util.TimerTask;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import kafka.KafkaOperations;
import kafka.KafkaProducerClass;


public class ScheduledTask extends TimerTask
{    
    private Trip trip;
    private int recordNumber = 0;
    
    
    public void setTripInformation(Trip trip)
    {
        this.trip = trip;
    }
    
    @Override
    public void run() 
    {   
        if(recordNumber < trip.getPositions().size())
            sendInfo(recordNumber++);
        else
            return;
    }

    private void sendInfo(int positionNum)
    {        
        double velocity = calculateVelocity(positionNum);
        System.out.println("ScheduledTask , trip is "+trip.getTripID() + " record number is " +(recordNumber-1) + " velocity is "+ velocity);
        try {          
            new KafkaOperations().sendTripInfo(trip.getTripID() , trip.getBeaglebone() , trip.getPositions().get(positionNum) , velocity );
        } catch (UnsupportedEncodingException ex) 
        {
            System.err.println("Scheduled Task:- error in sending msg, error is "+ex);
        }
    }
    
    private double calculateVelocity(int posNum)
    {
        Position currentPosition = trip.getPositions().get(posNum);
        double velocity = 0.0;
        if(posNum!=0)
        {
            Position previousPosition = trip.getPositions().get(posNum -1);
            double distanceTraveled = previousPosition.getDifference(currentPosition);
            velocity = distanceTraveled;
        }
        return velocity;
    }
  
}
