



## Commands

```bash
# create a topic

docker exec kafka-1 /opt/bitnami/kafka/bin/kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic test_name


# list topics
docker exec kafka-1 /opt/bitnami/kafka/bin/kafka-topics.sh --list -bootstrap-server localhost:9092

#delete topic 
docker exec kafka-1 /opt/bitnami/kafka/bin/kafka-topics.sh --delete --topic topic_name --bootstrap-server localhost:9092 
```

