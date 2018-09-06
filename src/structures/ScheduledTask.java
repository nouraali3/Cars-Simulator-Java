
package structures;



import pojos.Position;
import pojos.Trip;
import java.util.TimerTask;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Scanner;


public class ScheduledTask extends TimerTask
{    
    private Trip trip;
    private int recordNumber = 0;
    
    
    public void setTripInformation(Trip trip)
    {
        this.trip = trip;
    }
    
    @Override
    public void run() 
    {   
        if(recordNumber < trip.getPositions().size())
            sendInfo(recordNumber++);
        else
            return;
    }

    private void sendInfo(int positionNum)
    {
//        double velocity = calculateVelocity(positionNum);
//        System.out.println("ScheduledTask , trip is "+trip.getTripID() + " record number is " +(recordNumber-1) + " velocity is "+ velocity);
        
        try
        {
            double velocity = calculateVelocity(positionNum);
            System.out.println("ScheduledTask , trip is "+trip.getTripID() + " record number is " +(recordNumber-1) + " velocity is "+ velocity);
            sendInfo(trip.getTripID() , trip.getBeaglebone() , trip.getPositions().get(positionNum) , velocity ) ;
        }
        catch (IOException ex) 
        {
            System.err.println("Scheduled Task, sendInfo function : error in sending data error is "+ex);
        }
        
        
    }
    
    private double calculateVelocity(int posNum)
    {
        
        Position currentPosition = trip.getPositions().get(posNum);
        double velocity = 0.0;

        if(posNum!=0)
        {
            Position previousPosition = trip.getPositions().get(posNum -1);
            double distanceTraveled = previousPosition.getDifference(currentPosition);
            velocity = distanceTraveled;
        }

        return velocity;
        
        
    }
    

    private void sendInfo(int tripID, int beaglebone, Position position , double velocity) throws MalformedURLException, IOException
    {
        try
        {
            String sendPositionURL = "http://nouraali9.000webhostapp.com/IoTConnectedCar/SavePosition.php";
            String charset = java.nio.charset.StandardCharsets.UTF_8.name();  // Or in Java 7 and later, use the constant: java.nio.charset.StandardCharsets.UTF_8.name()
            String position_Id = Integer.toString(position.getPosID());
            String trip_Id = Integer.toString(position.getTripID());
            String latitude = Double.toString(position.getLatitude());
            String longitude = Double.toString(position.getLongitude());
            String altitude = Double.toString(position.getAltitude());
            String velocityString = Double.toString(velocity);
            
            String query = String.format("position_id=%s&trip_id=%s&latitude=%s&longitude=%s&altitude=%s&velocity=%s",
                    URLEncoder.encode(position_Id, charset),
                    URLEncoder.encode(trip_Id, charset),
                    URLEncoder.encode(latitude, charset),
                    URLEncoder.encode(longitude, charset),
                    URLEncoder.encode(altitude, charset),
                    URLEncoder.encode(velocityString, charset));
            
            
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
