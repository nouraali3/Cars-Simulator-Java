
package csvoperations;

import datatypes.Trip;
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
    Reader reader ;
    CSVParser csvParser;

    public TripOperations() {
    }
    
    
    /**
     * 
     * @param path is the path of the trips.csv file
     */
    public TripOperations(String path)
    {
        try 
        {
            reader = Files.newBufferedReader(Paths.get(path));             
        }
        catch (IOException ex)
        {
            System.err.print("error in loading  trips.csv file, error is "+ex);
        }
      
    }
    
      /**
       * 
       * @param position is the starting position (e.g. x) of the records to be brought
       * @return  4 records starting from position x
       */   
    public List<Trip> readFromCSVFile(long position) throws IOException
    {
        
        List<Trip> trips = new ArrayList<>();
        
        csvParser = new CSVParser(reader,
                                CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim(),
                                 position,position);
        Trip trip;
        int i=0;
        
        for(CSVRecord record : csvParser )
        {
            if(i++<4)
            {
                trip = new Trip(Integer.parseInt(record.get("trip_id")), Integer.parseInt(record.get("beaglebone_id")), record.get("date"));
                trips.add(trip);
            }

        }
        
        return trips;
    }
}
