# Microservice using Spring Boot & Spring Cloud

Implementing -
  - Eureka Discovery, 
  - API Gateway,
  - Hystrix, 
  - Config Server, 
  - Centralized Logging with ELK Stack 

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
Log File - 
logstash.conf - 

Execute Logstash with the .conf file with a -f

STEP 3 -
In Kibana, Create Index pattern i.e. sandy from Management > Index Pattern > Create Index Pattern by checking on sandy*
Then Discover by filtering for sandy
Hit Services & Reload Kibana to see hit count rises, also expand to see JSON/Tabular Format

Spring App --> log --> L(Data Processing) --> E(Storage) --> K(Visualize)

Postman WS - https://github.com/sandzotym/PostmanWS/blob/main/Microservice.postman_collection.json 
