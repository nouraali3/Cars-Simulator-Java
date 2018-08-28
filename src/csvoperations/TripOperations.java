
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
    
    String path = "Dataset/trips.csv";

    public TripOperations()
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
    public Trip readFromCSVFile(long position) throws IOException
    {
        
        
        CSVParser csvParser = new CSVParser(reader,
                                CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());
        Trip trip = null;
        
        
        long totalRecordsNum = csvParser.getRecords().size();

        
        System.out.println("position = "+position);
        System.out.println("total records = "+totalRecordsNum);
        
        if(position > totalRecordsNum)
            return trip;
        
        long curRecordNum;
        
        reader = Files.newBufferedReader(Paths.get(path));   
        csvParser = new CSVParser(reader,
                                CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());
        for(CSVRecord record : csvParser )
        {
            curRecordNum = record.getRecordNumber();
            if(curRecordNum < position)
            {}
            else if(curRecordNum == position)
            {
                trip = new Trip(Integer.parseInt(record.get("trip_id")), Integer.parseInt(record.get("beaglebone_id")), record.get("date"));
                break;
            }
            else
                break;
        }
        return trip;
    }
}
