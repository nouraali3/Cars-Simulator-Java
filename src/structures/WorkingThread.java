/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structures;

import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;


public class WorkingThread extends Thread
{
    int tripId;
    public WorkingThread() {
    }
    
    public WorkingThread(int tripId) {
        this.tripId = tripId;
    }
    
    @Override
    public void run() 
    {
        Timer timer = new Timer(true); 
        ScheduledTask scheduledTask = new ScheduledTask();
        scheduledTask.setTrip(tripId);
        timer.scheduleAtFixedRate(scheduledTask, 0, 1000); 
        
        try {  Thread.sleep(150000); } 
        catch (InterruptedException ex) {System.err.println("error in sleeping thread, error is "+ex);}
        timer.cancel();
    }
    
}
