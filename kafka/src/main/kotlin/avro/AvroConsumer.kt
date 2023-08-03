package avro

import org.apache.avro.generic.GenericRecord
import org.apache.kafka.clients.consumer.KafkaConsumer
import java.io.FileReader
import java.time.Duration
import java.util.*


fun loadAvroConsumeConfig() = Properties().apply {
    load(FileReader("src/main/resources/avro/consumer.properties"))
}

fun main() {


    val consumer = KafkaConsumer<String, GenericRecord>(loadAvroConsumeConfig())

    consumer.subscribe(listOf("test-avro"))

    consumer.use {
        while (true) {
            val consumerRecords = consumer.poll(Duration.ofMillis(500))
            consumerRecords.forEach { consumerRecord -> println(consumerRecord)  }
        }
    }
}


