
package csvoperations;

import datatypes.Position;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;



public class PositionOperations 
{
    Reader reader ;
    
    String path = "Dataset/positions.csv";

    public PositionOperations()
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
       * @param requiredRecordNum is the starting position (e.g. x) of the records to be brought
       * @return  4 records starting from position x
       */   
    public Position readFromCSVFile(long requiredRecordNum, int tripID) throws IOException
    {
        
        
        CSVParser csvParser = new CSVParser(reader,
                                CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());
        
//        Trip trip = null;
        Position position = null ;
        
        long totalRecordsNum = csvParser.getRecords().size();

        
        System.out.println("position = "+requiredRecordNum);
        System.out.println("total records = "+totalRecordsNum);
        
        if(requiredRecordNum > totalRecordsNum)
            return position;
        
        
        
        reader = Files.newBufferedReader(Paths.get(path));   
        csvParser = new CSVParser(reader,
                                CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());
        
        
        boolean trip = false;
        long curRecordNum;
        for(CSVRecord record : csvParser )
        {
            if(Integer.parseInt(record.get("trip_id"))== tripID)
            {
            
                curRecordNum = record.getRecordNumber();
                if(curRecordNum < requiredRecordNum)
                {}
                else if(curRecordNum == requiredRecordNum)
                {
                    position=new Position(Integer.parseInt(record.get("pos_id")),
                            tripID, 
                            Double.parseDouble(record.get("latitude")),
                            Double.parseDouble(record.get("longitude")),
                            Double.parseDouble(record.get("altitude")), 
                            record.get("timestamp"));
                    System.out.println(position.toString());
                    break;
                }
                else
                    break;
            }
            if(trip == true && Integer.parseInt(record.get("trip_id"))!= tripID)
                break;
        }
        return position;
    }
}
