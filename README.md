# Practica Processing

## Spark Streaming Kafka



### Creación del PRODUCTOR utilizando el método kafka-console-producer.sh



1.- Creamos el topic con zookeeper
```
root@debian:/home/kafka/kafka_2.11-2.4.0# bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic topicjson
Created topic topicjson.
```
2.- Copiamos en bin el archivo .json
```
root@debian:/home/keepcoding/IdeaProjects/TestScalaNuevo# cp personal.json /home/kafka/kafka_2.11-2.4.0/bin
```
3.- Añadimos el archivo personal.json al topic topicjson
```
root@debian:/home/kafka/kafka_2.11-2.4.0# cat bin/personal.json| bin/kafka-console-producer.sh --broker-list localhost:9092 --topic topicjson
>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>root@debian:/home/kafka/kafka_2.11-2.4.0# 
```

### Creación del CONSUMER utilizando el método kafka-console-consumer.sh

```
root@debian:/home/kafka/kafka_2.11-2.4.0# bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic topicjson --from-beginning
```

### Lectura del topic por consola

[Producer-consumer-console.jpg](https://github.com/enkhara/Processing/blob/master/Producer-consumer-console.jpg)


### Creación del CONSUMER con Scala

1.- Filtrar del fichero JSON ""gender != 'Male' AND last_name != 'Bea'""

[resultado-filtrado](resultado_jsonKafkaFiltrado.scala.jpg)

[código consumer.scr](jsonKafkaFiltrado.scala)



