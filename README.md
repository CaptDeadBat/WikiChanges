# WikiChanges
Graphical representation of wiki changes stream by language code


To run follow below steps:

1. Start all Kafka resources:
to do this run cmd inside the "kafka" folder inside "non java resources" folder, (this folder contains all kafka resources) and run the following commands:

i. Start kafka zookeeper
    		
    	bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties
    		
    
ii. Start kafka broker
    		```
	  	bin\windows\kafka-server-start.bat .\config\server.properties
   		 ```
  
 iii. View all topics on the broker
    		```
		bin\windows\kafka-topics --list --bootstrap-server localhost:9093
    		```
  
 iv. Start kafka console consumer
   		 ```sh
  		bin\windows\kafka-console-consumer.bat --bootstrap-server localhost:9093 --topic topicname -property print.key=true
    		```
  	for each topic change the "topicname" and remove "-property print.key=true" if not needed 
