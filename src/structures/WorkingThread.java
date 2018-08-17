/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structures;

import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import ui.CarsControlPanel;


public class WorkingThread extends Thread
{

    @Override
    public void run() 
    {
        Timer time = new Timer(); // Instantiate Timer Object
        ScheduledTask st = new ScheduledTask(); // Instantiate SheduledTask class
        time.schedule(st, 0, 1000); // Create Repetitively task for every 1 secs

        //for demo only.
        for (int i = 0; i <= 5; i++) {
                System.out.println("Execution in Main Thread...." + i);
            try {Thread.sleep(5000);}
            catch (InterruptedException ex) 
            {Logger.getLogger(CarsControlPanel.class.getName()).log(Level.SEVERE, null, ex);}
            if (i == 0) 
            {
                    System.out.println("Application Terminates");
                    System.exit(0);
            }
        }
    }
    
}
