
package httpoperations;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Scanner;
import pojos.Position;

public class Connection {
    
    public static void sendPosition(Position p) throws MalformedURLException, IOException 
    {
        try
        {
            String sendPositionURL = "http://nouraali9.000webhostapp.com/IoTConnectedCar/SavePosition.php";
            String charset = java.nio.charset.StandardCharsets.UTF_8.name();  // Or in Java 7 and later, use the constant: java.nio.charset.StandardCharsets.UTF_8.name()
            String position_Id = Integer.toString(p.getPosID());
            String trip_Id = Integer.toString(p.getTripID());
            String latitude = Double.toString(p.getLatitude());
            String longitude = Double.toString(p.getLongitude());
            String altitude = Double.toString(p.getAltitude());
            
            String query = String.format("position_id=%s&trip_id=%s&latitude=%s&longitude=%s&altitude=%s",
                    URLEncoder.encode(position_Id, charset),
                    URLEncoder.encode(trip_Id, charset),
                    URLEncoder.encode(latitude, charset),
                    URLEncoder.encode(longitude, charset),
                    URLEncoder.encode(altitude, charset));
            
            
            URLConnection connection = new URL(sendPositionURL + "?" + query).openConnection();
            connection.setRequestProperty("Accept-Charset", charset);
            InputStream response = connection.getInputStream();
            
            try (Scanner scanner = new Scanner(response))
            {
                String responseBody = scanner.useDelimiter("\\A").next();
                System.out.println(responseBody);
            }
            
                        
        } 
        catch (UnsupportedEncodingException ex) 
        {
            System.err.println("Scheduled Task: error in encoding parameters passed to the HTTP client, error is "+ex);
        }
        
        
    }
    
}
