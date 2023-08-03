package avro

import org.apache.avro.Schema
import java.io.File

fun loadSchema(): Schema = Schema.Parser().parse(File("src/main/kotlin/avro/avro.schema.avsc"))