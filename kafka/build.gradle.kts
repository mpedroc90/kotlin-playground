import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.gradle.utils.loadPropertyFromResources
import java.util.*

buildscript {
    repositories {
        mavenCentral()
        maven {
            url = uri("https://packages.confluent.io/maven/")
        }
        maven {
            url = uri("https://plugins.gradle.org/m2/")
        }
        maven {
            url = uri("https://jitpack.io")
        }
    }
}

plugins {
    kotlin("jvm") version "1.8.21"
    application
    id("com.github.imflog.kafka-schema-registry-gradle-plugin") version "1.11.1"
    id("com.github.davidmc24.gradle.plugin.avro") version "1.3.0"
}

group = "org.mpedroc90.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()

    maven(url = "https://packages.confluent.io/maven/")
    maven(url ="https://plugins.gradle.org/m2/")

}


val kafkaApiVersion = "3.3.1"

dependencies {
    testImplementation(kotlin("test"))
    implementation("org.apache.kafka:kafka-clients:$kafkaApiVersion")
    implementation("io.confluent:kafka-json-serializer:5.0.1")
    implementation("org.slf4j:slf4j-api:1.7.6")
    implementation("org.slf4j:slf4j-log4j12:1.7.6")
    implementation("com.fasterxml.jackson.core:jackson-databind:[2.8.11.1,)")
   // implementation("com.github.imflog:kafka-schema-registry-gradle-plugin:1.11.1")
    implementation("org.apache.avro:avro:1.11.0")
    implementation("io.confluent:kafka-avro-serializer:7.1.1")
}


tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("MainKt")
}

schemaRegistry {
    val props = Properties()
    file("src/main/resources/schema-registry.properties").reader().use{ props.load(it) }
    val srUrl = props.getProperty("schema.registry.url")

//        credentials {
//            // username is the characters up to the ':' in the basic.auth.user.info property
//            username = auth[0]
//            // password is everything after ':' in the basic.auth.user.info property
//            password = auth[1]
//        }

    register {
        subject("test-avro-value", "src/main/kotlin/avro/avro.schema.avsc", "AVRO")
    }

    download {
        // commented out to prevent its download which results in the schema
        // definition json being flattened to a single line which doesn't
        // match the exercise illustration
        // subject('avro-purchase-value', 'src/main/avro', 'purchase')
        // subject("test-purchase-value", "src/main/kotlin", "avro.schema-donwloaded.avsc")
        //subject('proto-purchase-value', 'src/main/proto', 'purchase')
    }

    compatibility {
        subject("test-avro-value", "src/main/kotlin/avro/avro.schema.avsc", "AVRO")
       // subject('proto-purchase-value', 'src/main/proto/purchase.proto', 'PROTOBUF')
    }
}