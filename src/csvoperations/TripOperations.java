
package csvoperations;

import pojos.Position;
import pojos.Trip;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;



public class TripOperations 
{
    private String path = "Dataset/trips.csv";
    private List<Trip> trips = new ArrayList<>();
    
    //initialize trips
    public TripOperations()
    {
       try 
        {
            
            Reader reader = Files.newBufferedReader(Paths.get(path));  
            CSVParser csvParser = new CSVParser(reader,
                                CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());
            System.out.println("printing all trips ");
            int i=1;
            for(CSVRecord record : csvParser )
            {
                Trip trip =new Trip(Integer.parseInt(record.get("trip_id")),
                                    Integer.parseInt(record.get("beaglebone_id")),
                                    record.get("date"));
                System.out.println("trip "+(i++)+" is "+trip.toString());
                trips.add(trip);
            }
            
            
        }
        catch (IOException ex)
        {
            System.err.print("error in loading  trips.csv file, error is "+ex);
        }
    }

    public Trip getTrip(int tripId)
    {
        for (Trip trip : trips) 
        {
            if(trip.getTripID()== tripId)
                return trip;
        }
        return null;
    }
    
    
}
