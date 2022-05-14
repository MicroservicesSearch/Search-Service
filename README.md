# Search-service 


## Configuration

To run this application you need these docker containers running: 

* <font size="3">**docker run -d --name elasticsearch -p 9200:9200 -e "discovery.type=single-node" elasticsearch:7.6.2** - elasticsearch


* **docker run -d -p 8500:8500 -p 8600:8600/udp --name=badger consul agent -server -ui -node=server-1 -bootstrap-expect=1 -client='0.0.0.0'** - consul


* **docker run -d --name rabbitmq2 -p 15672:15672 -p 5672:5672 rabbitmq:3-management** - spring amqp</font>

***You also need to add spring cloud discovery for consul in Spring application settings or in consul** 



## Endpoints


We have 3 Endpoints: 

**save** - self-explanatory - localhost:8080/(posts or users)/

**findById** - self-explanatory - localhost:8080/(posts or users)/{id}

**search** - Searches the database with your request search term - localhost:8080/(posts or users )/search


## JSON

save 

Example - localhost:8080/posts/

`{
"id":"1",
"userId":"lukas@gmail.com",
"text":"Hello Hej",
"parentId":"123",
"created":"2015-01-01"
}`

Search

Example - localhost:8080/posts/search

`{
"fields":["userId"],
"searchTerm":"lukas@gmail.com"
}`




