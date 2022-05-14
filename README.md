# Search-service 


## Configuration

To run this application you need these docker containers running: 

* <font size="3">**docker run -d --name elasticsearch -p 9200:9200 -e "discovery.type=single-node" elasticsearch:7.6.2** - elasticsearch


* **docker run -d -p 8500:8500 -p 8600:8600/udp --name=badger consul agent -server -ui -node=server-1 -bootstrap-expect=1 -client='0.0.0.0'** - consul


* **docker run -d --name rabbitmq2 -p 15672:15672 -p 5672:5672 rabbitmq:3-management** - spring amqp</font>

***You also need to add spring cloud discovery for consul in Spring application settings or in consul** 



## Endpoints



