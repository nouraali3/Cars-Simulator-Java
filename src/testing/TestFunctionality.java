
package testing;

import csvoperations.TripOperations;
import datatypes.Trip;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
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
        TripOperations to = new TripOperations("Dataset/trips.csv");
        List<Trip> trips = to.readFromCSVFile(0);
        int i=0;
        for(Trip trip:trips)
        {
            System.out.println("trip "+ (i++) +" is "+trip.toString());
        }
    }
    
}
