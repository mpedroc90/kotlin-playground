package avro

import org.apache.avro.Schema
import org.apache.avro.generic.GenericData
import org.apache.avro.generic.GenericRecord
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.clients.producer.RecordMetadata
import java.io.File
import java.io.FileReader
import java.util.*
import kotlin.random.Random


fun loadAvroProducerConfig() = Properties().apply {
    load(FileReader("src/main/resources/avro/producer.properties"))
}

fun loadSchema(): Schema = Schema.Parser().parse(File("src/main/kotlin/avro/avro.schema.avsc"))


fun main() {

    val producer = KafkaProducer<String, Any>(loadAvroProducerConfig() )

    val schema = loadSchema()

    repeat(10) {
        val avroRecord: GenericRecord = GenericData.Record(schema).apply {
                put("item", "item ${Random.nextInt()}" )
                put("total_cost", Random.nextDouble())
                put("customer_id", UUID.randomUUID().toString())

        }

        producer.send(
            ProducerRecord("test-avro", avroRecord)
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


