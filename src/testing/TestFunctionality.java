
package testing;

import csvoperations.TripOperations;
import datatypes.Trip;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

/**
 *
 * @author Noura Ali
 */
public class TestFunctionality {
   
    public static void main(String[] args) 
    {
        long position = 2;  
        TripOperations to = new TripOperations("Dataset/trips.csv");
        List<Trip> trips = new ArrayList<>();
        
        try 
            {trips = to.readFromCSVFile(position);} 
        catch (IOException ex) 
            {System.err.println("TestFunctionality: error in calling readFromCSVFile, error is "+ex);}
        int i=0;
        for(Trip trip:trips)
        {
            System.out.println("trip "+ (i++) +" is "+trip.toString());
        }
    }
    
}
