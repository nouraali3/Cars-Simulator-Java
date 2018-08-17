
package structures;

import java.util.TimerTask;
import java.util.Date;

public class ScheduledTask extends TimerTask
{    
    Date now; // to display current time
	// Add your task here
    @Override
    public void run() 
    {
        now = new Date(); // initialize date
        System.out.println("Time is :" + now); // Display current time
        
        // start reading from CSV file from previous entry
        //send to spring batch job through http client
    }

    
    
}
