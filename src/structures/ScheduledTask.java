
package structures;

import csvoperations.PositionOperations;
import datatypes.Position;
import datatypes.Trip;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.TimerTask;
import java.util.Scanner;

public class ScheduledTask extends TimerTask
{    
    private static int recordNum = 1;
    private static int tripId =3;
    private PositionOperations po;
    
    Trip trip;
    Position position;
    
    public void setTrip(int tId)
    {tripId = tId;}
    
    @Override
    public void run() 
    {   
        position = getPositionFromFile();
        if (position == null)
            System.exit(0);
        else
        {
//         try { sendPosition(position);  System.out.println("position info is "+position.toString()); } 
//         catch (IOException ex) { System.err.println("Scheduled Task: error in sending the position, error is "+ex); }
            System.out.println("position info is "+position.toString());
        }
    }

    private Position getPositionFromFile() 
    {
        Position p =  new Position();        
        po = new PositionOperations();
        
        // start reading from CSV file from previous entry
        try 
        {
            p = po.readFromCSVFile(recordNum++,3);

        } 
        catch (IOException ex) 
        {System.err.println("Scheduled Task: error in calling readFromCSVFile, error is "+ex);}
     
        return p;
    }

    private void sendPosition(Position p) throws IOException 
    {
        try
        {
            String sendPositionURL = "http://nouraali9.000webhostapp.com/IoTConnectedCar/SavePosition.php";
            String charset = java.nio.charset.StandardCharsets.UTF_8.name();  // Or in Java 7 and later, use the constant: java.nio.charset.StandardCharsets.UTF_8.name()
            String tripId = Integer.toString(p.getTripID());
            String latitude = Double.toString(p.getLatitude());
            String longitude = Double.toString(p.getLongitude());
            String altitude = Double.toString(p.getAltitude());
            
            String query = String.format("trip_id=%s&latitude=%s&longitude=%s&altitude=%s",
                    URLEncoder.encode(tripId, charset),
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
