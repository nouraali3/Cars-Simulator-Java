
package structures;

import csvoperations.PositionOperations;
import csvoperations.TripOperations;
import datatypes.Position;
import datatypes.Trip;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TimerTask;
import java.util.Date;
import java.util.List;

public class ScheduledTask extends TimerTask
{    
    private static int recordNum = 1;
    
    private TripOperations to;
    private PositionOperations po;
    
    Trip trip;
    Position position;

    
    @Override
    public void run() 
    {   
//        trip = getTripsFromFile();
        position = getPositionFromFile();
    }

    private Trip getTripsFromFile() 
    {
        Trip t =  new Trip();        
        to = new TripOperations();
        
        // start reading from CSV file from previous entry
        try 
        {
            t = to.readFromCSVFile(recordNum++);
            System.out.println("trip info is "+t.toString());
            
            if (t == null)
                System.exit(0);
            
        } 
        catch (IOException ex) 
        {System.err.println("TestFunctionality: error in calling readFromCSVFile, error is "+ex);}
     
        return t;
    }

    private Position getPositionFromFile() 
    {
        Position p =  new Position();        
        po = new PositionOperations();
        
        // start reading from CSV file from previous entry
        try 
        {
            p = po.readFromCSVFile(recordNum++,3);
            System.out.println("position info is "+p.toString());
            
            if (p == null)
                System.exit(0);
            
        } 
        catch (IOException ex) 
        {System.err.println("TestFunctionality: error in calling readFromCSVFile, error is "+ex);}
     
        return p;
    }

    
    
}
