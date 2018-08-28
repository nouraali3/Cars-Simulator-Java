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

    @Override
    public void run() 
    {
        Timer timer = new Timer(true); // Instantiate Timer Object
        //ScheduledTask st = new ScheduledTask(); // Instantiate SheduledTask class
        timer.scheduleAtFixedRate(new ScheduledTask(), 0, 1000); // Create Repetitively task for every 1 second and ==> force the working thread to sleep for 5 seconds
        try {  Thread.sleep(150000); } 
        catch (InterruptedException ex) {System.err.println("error in sleeping thread, error is "+ex);}
        timer.cancel();
    }
    
}
