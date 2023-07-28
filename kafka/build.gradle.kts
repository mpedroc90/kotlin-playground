import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.8.21"
    application
}

group = "org.mpedroc90.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()

    maven(url = "https://packages.confluent.io/maven/")
}


val kafkaApiVersion = "3.3.1"

dependencies {
    testImplementation(kotlin("test"))
    implementation("org.apache.kafka:kafka-clients:$kafkaApiVersion")
    implementation("io.confluent:kafka-json-serializer:5.0.1")
    implementation("org.slf4j:slf4j-api:1.7.6")
    implementation("org.slf4j:slf4j-log4j12:1.7.6")
    implementation("com.fasterxml.jackson.core:jackson-databind:[2.8.11.1,)")
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