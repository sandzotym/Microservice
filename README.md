# Microservice using Spring Boot & Spring Cloud

---------------------------------------------------------
Implementing -
  - Eureka Discovery
  - API Gateway
  - Hystrix
  - Config Server
  - Centralized Logging with ELK Stack 
  - Distributed tracing with Spring Cloud Sleuth & Zipkin

![Screenshot 2021-12-23 at 11 27 27 AM](https://user-images.githubusercontent.com/93154062/147195342-f02f79d2-30a4-487b-9ffe-a5d6250cbbf6.png)

------------------------------------------------------------------------------------------------------------------------------------
(E)lastic Search - NoSQL DB based on Lucene Search Engine, helps to store inputs/logs. localhost:9200 & localhost:9200/_cat/indices.

(L)ogstash - Log Pipeline that accepts inputs/logs from various sources & exports data to various targets.

(K)ibana - Visualization UI Layer, helps developer to monitor App Logs. localhost:5600.

STEP 1 - 
After executing the E&K, Execute in Kibana > Dev Tools

PUT /sandy
{ 
  "settings" : {
    "index" : {
      "number_of_shards" : 3,
      "number_of_replicas" : 2
     }
   }
}

POST /sandy/default/
{
  "name" : "event processing",
  "instructor" : {
    "firstname" : "sandy",
    "lastname" : "microservice"
   }
}

STEP 2 -

Log File - https://github.com/sandzotym/Microservice/blob/main/microservice.log

logstashTest.conf - https://github.com/sandzotym/Microservice/blob/main/logstashTest.conf

Execute Logstash with the .conf file with a -f

STEP 3 -
In Kibana, Create Index pattern i.e. sandy from Management > Index Pattern > Create Index Pattern by checking on sandy*.
Then Discover by filtering for sandy.
Hit Services & Reload Kibana to see hit count rises, also expand to see JSON/Tabular Format.

Spring App --> log --> L(Data Processing) --> E(Storage) --> K(Visualize)

-----------------------------------------------------------------------------------------------------------------------------------------------------------------
Zipkin & Sleuth - For Multiple Microservices (specially same microservice running on 2 different ports) calling internally each others, the circuit breaker would not be much effective as to find the fault we have to go through all Microservices & their flow to find the root cause. 

Example -
<img width="1079" alt="Screenshot 2021-12-23 at 11 26 24 AM" src="https://user-images.githubusercontent.com/93154062/147195318-9306c2f0-b6f9-4317-afc0-4f1ecad9b6b2.png">

Sleuth generates Metadata for each request which comprises of 4 elements -
  - Service Name (Microservice Instance Name)
  - TraceID (Unique ID that remains same throughout Microservice for particular Request)
  - SpanID (Unique ID per Microservice)
  - Export Flag

--------------------------------------------------------------------------------------------------
Postman WS - https://github.com/sandzotym/PostmanWS/blob/main/Microservice.postman_collection.json 
