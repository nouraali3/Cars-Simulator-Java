
package structures;

import csvoperations.TripOperations;
import datatypes.Trip;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TimerTask;
import java.util.Date;
import java.util.List;

public class ScheduledTask extends TimerTask
{    
    Date now; // to display current time
	// Add your task here
    
    int position;
    private TripOperations to;
    List<Trip> trips;
    private static int tripID=0;
    
//    public ScheduledTask() {
//        this.position = tripID;
//        to = new TripOperations("Dataset/trips.csv");
//        trips = new ArrayList<>();
//        System.out.println("position is "+position);
//    }

    
//    public ScheduledTask(int position) {
//        this.position = position;
//        to = new TripOperations("Dataset/trips.csv");
//        trips = new ArrayList<>();
//        System.out.println("position is "+position);
//    }
    
    
    @Override
    public void run() 
    {   
        trips = getTripsFromFile();
        
        //send to spring batch job through http client
//        sendTripsToHTTPClient(trips);
        
    }

    private List<Trip> getTripsFromFile() 
    {
        List<Trip> mtrips = new ArrayList<>();
        to = new TripOperations("Dataset/trips.csv");
        this.position = tripID;        
        System.out.println("position is "+position);
        // start reading from CSV file from previous entry
        try 
        {
            mtrips = to.readFromCSVFile(position);
            if (mtrips.size()==0)
                System.exit(0);
        } 
        catch (IOException ex) 
            {System.err.println("TestFunctionality: error in calling readFromCSVFile, error is "+ex);}
        for(Trip trip : mtrips)
        {
            System.out.println("trip id is "+ (tripID++) +" is "+trip.toString());
        }
        
        return mtrips;
    }

    private void sendTripsToHTTPClient(List<Trip> trips) 
    {
    }

    
    
}
