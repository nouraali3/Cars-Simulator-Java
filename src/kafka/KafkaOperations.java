
package kafka;

import com.google.gson.JsonObject;
import java.io.UnsupportedEncodingException;
import pojos.Position;

public class KafkaOperations
{    
    public KafkaOperations() 
    {
    }
    
    public void sendRecordInfo(long recordNum , Position currentPosition) throws UnsupportedEncodingException
    {
        int tripID = currentPosition.getTripID();
        long positionID = currentPosition.getPosID();
        double latitude = currentPosition.getLatitude();
        double longitude = currentPosition.getLongitude();
        double altitude = currentPosition.getAltitude();
        double speed = currentPosition.getSpeed();
        double rpm = currentPosition.getRpm();
        double fuel = currentPosition.getFuel();
        
        JsonObject record = new JsonObject();
        record.addProperty("trip_id", tripID);
        record.addProperty("record_number", recordNum);
        record.addProperty("position_id", positionID);
        record.addProperty("latitude", latitude);
        record.addProperty("longitude", longitude);
        record.addProperty("altitude", altitude);
        record.addProperty("speed", speed);
        record.addProperty("rpm", rpm);
        record.addProperty("fuel", fuel);
        
        try {
            KafkaProducerClass.runProducer(record.toString());
        } catch (Exception ex) {
            System.err.println("Kafka Operations :- error in running producer, error is "+ex);
        }
    }
    
    
}

