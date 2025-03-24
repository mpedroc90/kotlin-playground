package org.example

import redis.clients.jedis.HostAndPort
import redis.clients.jedis.JedisCluster



fun main() {


    val redis = JedisCluster(
        hashSetOf(
            HostAndPort("localhost", 9000 ),
//            HostAndPort("127.0.0.1", 9001 ),
//            HostAndPort("127.0.0.1", 9003 )
        ) ,
  )

   println( redis.ping())



//redis.

  // redis.set("k123ey", "value")
//    println(redis.get("key"))



}