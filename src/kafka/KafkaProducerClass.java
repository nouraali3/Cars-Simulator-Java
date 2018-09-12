
package kafka;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import java.util.Properties;

public class KafkaProducerClass {
    
    
    //NOTE:- when sending a msg from the producer and the topic or server ip/post is wrong,
    //no error will be released in netbeans, but the msg won't reach the consumer
    private final static String TOPIC = "car-data";
    private final static String BOOTSTRAP_SERVERS ="localhost:9092";
    
    private static Producer<Long, String> createProducer() 
    {
        //properties of the kafka producer
        Properties props = new Properties();
        // servers to which the producer will talk to . NOTE that these servers were previously created by us
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,BOOTSTRAP_SERVERS);
        
        props.put(ProducerConfig.CLIENT_ID_CONFIG, "KafkaExampleProducer");
        //message key(id) in our example is long.
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class.getName());
        ////message value(body) in our example is string.
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        return new KafkaProducer<>(props);
    }
    
    
    
    static void runProducer1(final int sendMessageCount) throws Exception 
    {
      final Producer<Long, String> producer = createProducer();
      long time = System.currentTimeMillis();
      try 
      {
       
            final ProducerRecord<Long, String> record = new ProducerRecord<>(TOPIC, time,"Hello Mom " + time);
            RecordMetadata metadata = producer.send(record).get();
            long elapsedTime = System.currentTimeMillis() - time;
            System.out.printf("sent record(key=%s value=%s) " + "meta(partition=%d, offset=%d) time=%d\n",
                              record.key(), record.value(), metadata.partition(),metadata.offset(), elapsedTime);
        
      } 
      finally 
      {
        producer.flush();
        producer.close();
      }
    }
    
    static void runProducer(String msg) throws Exception 
    {
      final Producer<Long, String> producer = createProducer();
      long time = System.currentTimeMillis();
      try 
      {
        // producer send a record to the defined topic with key = time and value = msg
        final ProducerRecord<Long, String> record = new ProducerRecord<>(TOPIC, time, msg);
        RecordMetadata metadata = producer.send(record).get();
        long elapsedTime = System.currentTimeMillis() - time;
        System.out.printf("sent record(key=%s value=%s) " + "meta(partition=%d, offset=%d) time=%d\n",
                          record.key(), record.value(), metadata.partition(),metadata.offset(), elapsedTime);
        
      } 
      finally 
      {
        producer.flush();
        producer.close();
      }
    }
    
//    public static void main(String... args) throws Exception 
//    {
//        if (args.length == 0) 
//        {
//            runProducer1(5);
//        }
//        else
//        {
//            runProducer1(Integer.parseInt(args[0]));
//        }
//    }
    
    
}
