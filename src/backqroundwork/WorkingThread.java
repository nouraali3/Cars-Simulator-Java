/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backqroundwork;

import csvoperations.PositionOperations;
import csvoperations.TripOperations;
import pojos.Trip;
import java.io.IOException;
import java.util.Timer;


public class WorkingThread extends Thread
{
    
    
    //operations
    //read all trips when initializing tripOperations
    private static TripOperations tripOperations = new TripOperations();
    private PositionOperations positionOperations = new PositionOperations();
    
    
   //information
    private int tripId;
    private Trip trip;
    
    public WorkingThread() {
    }
    
    public WorkingThread(int tripId) throws IOException 
    {
        this.tripId = tripId;
        this.trip = tripOperations.getTrip(tripId);
        
        //this method must be synchronous, as multiple thread may access positions.csv file at the same time
        this.trip.setPositions(positionOperations.getTripPositions(tripId));
        
    }
    
    @Override
    public void run() 
    {
        Timer timer = new Timer(true); 
        System.out.println("Working Thread: trip id is "+tripId + " trip positions = "+trip.getPositions().size());
        
        ScheduledTask scheduledTask = new ScheduledTask();
        scheduledTask.setTripInformation(trip);
        timer.scheduleAtFixedRate(scheduledTask, 0, 1000); 
        
        try {  Thread.sleep(15000000); } 
        catch (InterruptedException ex) {System.err.println("error in sleeping thread, error is "+ex);}
        timer.cancel();
    }
    
}
