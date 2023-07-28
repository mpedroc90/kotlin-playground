import java.io.FileReader
import java.util.Properties
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.clients.producer.RecordMetadata


fun loadProducerConfig() = Properties().apply {
        load(FileReader("src/main/resources/producer.properties"))
}


fun main() {

    val producer = KafkaProducer<String, String>(loadProducerConfig())

    repeat(10) {
        producer.send(
            ProducerRecord("test", "hola$it" )
        ) { m: RecordMetadata, e: Exception? ->
            when (e) {
                // no exception, good to go!
                null -> println("Produced record to topic ${m.topic()} partition [${m.partition()}] @ offset ${m.offset()}")
                // print stacktrace in case of exception
                else -> e.printStackTrace()
            }
        }
    }

    producer.flush()
    producer.close()
}


