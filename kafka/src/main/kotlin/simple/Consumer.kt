package simple

import org.apache.kafka.clients.consumer.KafkaConsumer
import java.io.FileReader
import java.util.Properties
import java.time.Duration

fun loadConsumerConfig() = Properties().apply {
    load(FileReader("src/main/resources/simple/consumer.properties"))
}


fun main() {

    val consumer = KafkaConsumer<String, String>(loadConsumerConfig())

    consumer.subscribe(listOf("test"))

    consumer.use { consumer ->
        while (true) {
            val consumerRecords = consumer.poll(Duration.ofMillis(500))
            consumerRecords.forEach { consumerRecord -> println(consumerRecord)  }
            consumer.commitSync()
        }
    }
}
