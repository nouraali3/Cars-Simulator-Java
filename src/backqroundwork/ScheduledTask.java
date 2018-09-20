
package backqroundwork;



import pojos.Position;
import pojos.Trip;
import java.util.TimerTask;
import java.io.UnsupportedEncodingException;
import kafka.KafkaOperations;


public class ScheduledTask extends TimerTask
{    
    private Trip trip;
    private int positionIndex = 0;
    
    
    public void setTripInformation(Trip trip)
    {
        this.trip = trip;
    }
    
    @Override
    public void run() 
    {   
        if(positionIndex < trip.getPositions().size())
            sendInfo(positionIndex++);
        else
            return;
    }

//    private void sendInfo(int positionNum)
//    {    
//        double maxSpeed = 435.31 , minSpeed= 0;
//        
//        double velocity = calculateVelocity(positionNum);
////        double fuel = calculateFuel(positionNum);
//        System.out.println("ScheduledTask , trip is "+trip.getTripID() + " record number is " +(recordNumber-1) + " velocity is "+ velocity);
//        if (velocity < maxSpeed && velocity > minSpeed)
//        {
//            System.out.println(" send record whose "+" record number is " +(recordNumber-1) + " velocity is "+ velocity);
//            try 
//            {          
//                new KafkaOperations().sendRecordInfo(recordNumber , trip.getPositions().get(positionNum).getPosID(),trip.getTripID() , trip.getBeaglebone() , trip.getPositions().get(positionNum) , velocity );
//            } 
//            catch (UnsupportedEncodingException ex) 
//            {
//                System.err.println("Scheduled Task:- error in sending msg, error is "+ex);
//            }
//        }
//        return;
//    }
//    
//    private double calculateVelocity(int posNum)
//    {
//        Position currentPosition = trip.getPositions().get(posNum);
//        double velocity = 0.0;
//        if(posNum!=0)
//        {
//            Position previousPosition = trip.getPositions().get(posNum -1);
//            double distance_m = previousPosition.getGeoDistance(currentPosition);
//            double time_s = (currentPosition.getTimeStamp().getTime() - previousPosition.getTimeStamp().getTime()) / 1000.0;
//            double speed_mps = distance_m / time_s;
//            double speed_kph = (speed_mps * 3600.0) / 1000.0;
//            
//            velocity = speed_mps;
//            
//        }
//        return velocity;
//    }
    
    
    private void sendInfo(int positionNum)
    {    
        double maxSpeed = 435.31 , minSpeed= 0;
        Position currentPosition = trip.getPositions().get(positionNum);
        
        long recordNumber = positionNum+1;
        if (currentPosition.getSpeed() <= maxSpeed && currentPosition.getSpeed() >= minSpeed)
        {
            System.out.println(" send record whose info is "+currentPosition.toString());
            try 
            {
                new KafkaOperations().sendRecordInfo(recordNumber, currentPosition);
            } 
            catch (UnsupportedEncodingException ex) 
            {
                System.err.println("Scheduled Task error in sending record, error is "+ex);;
            }
        }
        return;
    }
    
}
