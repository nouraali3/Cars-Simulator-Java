
package kafka;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;
import pojos.Position;

public class KafkaOperations
{
    private KafkaProducerClass producer;
    
    public KafkaOperations() 
    {
    }
    
    public void sendTripInfo(int tripID, int beaglebone, Position position , double velocity) throws UnsupportedEncodingException
    {
        String charset = java.nio.charset.StandardCharsets.UTF_8.name();  // Or in Java 7 and later, use the constant: java.nio.charset.StandardCharsets.UTF_8.name()
        String position_Id = Integer.toString(position.getPosID());
        String trip_Id = Integer.toString(position.getTripID());
        String latitude = Double.toString(position.getLatitude());
        String longitude = Double.toString(position.getLongitude());
        String altitude = Double.toString(position.getAltitude());
        String velocityString = Double.toString(velocity);

        String msg = String.format("position_id=%s&trip_id=%s&latitude=%s&longitude=%s&altitude=%s&velocity=%s",
                URLEncoder.encode(position_Id, charset),
                URLEncoder.encode(trip_Id, charset),
                URLEncoder.encode(latitude, charset),
                URLEncoder.encode(longitude, charset),
                URLEncoder.encode(altitude, charset),
                URLEncoder.encode(velocityString, charset)); 
        
        try {
            KafkaProducerClass.runProducer(msg);
        } catch (Exception ex) {
            System.err.println("Kafka Operations :- error in running producer, error is "+ex);
        }
    }
}

