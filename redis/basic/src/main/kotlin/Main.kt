package org.example.basic

import redis.clients.jedis.JedisPool
import java.util.HashMap


fun main() {
    val pool =  JedisPool("localhost", 6379)

    pool.resource.use {  redis ->
        redis.set("foo", "bar")
        println(redis.get("foo"))

    }

    pool.resource.use {redis ->
        val hash = HashMap<String , String>()
        hash["name"] = "John"
        hash["surname"] = "Smith"
        hash["company"] = "Redis"
        hash["age"] = "29"
        redis.hset("user-session:123", hash)
        println(redis.hgetAll("user-session:123"))
    }
}