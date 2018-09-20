
package csvoperations;

import pojos.Position;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;



public class PositionOperations 
{
    private String positionsPath = "Dataset/positions.csv";

    public PositionOperations(){}
    
    public synchronized List<Position> getTripPositions(int tripId) throws IOException
    {
        List <Position> positions = new ArrayList();
        
        Reader positionsReader = Files.newBufferedReader(Paths.get(positionsPath)); 
        CSVParser csvParser = new CSVParser(positionsReader,
                                CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());
        
        boolean tripBool = false;
        for(CSVRecord record : csvParser )
        {
            if(tripId==5)
                System.out.println("trip id is 5");
            
            if(record.get("trip_id").length()>0)
            {
                if(Integer.parseInt(record.get("trip_id"))== tripId)
                {
                    tripBool =true;
                    Position position=new Position(Integer.parseInt(record.get("pos_id")),
                                                    tripId,
                                                    Double.parseDouble(record.get("latitude")),
                                                    Double.parseDouble(record.get("longitude")),
                                                    Double.parseDouble(record.get("altitude")), 
                                                    Double.parseDouble(record.get("speed")),
                                                    Double.parseDouble(record.get("rpm")),
                                                    Double.parseDouble(record.get("fuel")) );
                    positions.add(position);
                }
                else
                {
                    if(tripBool == true)
                        break;
                }
            }
            else
                break;
        }
        return positions;
    }

}
