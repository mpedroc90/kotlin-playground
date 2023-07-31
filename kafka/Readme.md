



## Commands

```bash
# create a topic

docker exec kafka-1 /opt/bitnami/kafka/bin/kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 2 --partitions 2 --topic test

# list topics
docker exec kafka-1 /opt/bitnami/kafka/bin/kafka-topics.sh --list -bootstrap-server localhost:9092

#delete topic 
docker exec kafka-1 /opt/bitnami/kafka/bin/kafka-topics.sh --delete --topic test --bootstrap-server localhost:9092

# Consumer from a topic
docker exec kafka-1 /opt/bitnami/kafka/bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic test --from-beginning
```

