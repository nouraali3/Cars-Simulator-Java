# CarSimulator

Instructions

Before running the program for the first time:-
1-Start zookeeper on default port 2181
2-Start kafka server on default localhost:port 9092
3-Create a topic called car-data with replication factor =1 and partitions =2

Before running the program if not the first time:-
1-Start zookeeper on default port 2181
2-Start kafka server on default localhost:port 9092


When a trip button is clicked, a thread will be created that will start a scheduled task.
Scheduked task will send the trip info every second.

