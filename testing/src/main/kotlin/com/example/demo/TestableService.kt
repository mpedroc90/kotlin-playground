package com.example.demo

class TestableService {
    fun getDataFromDb(testParameter: String): String {
        // query database and return matching value
        return "FAKE RESPONSE"
    }

    fun doSomethingElse(testParameter: String): String {
        return "I don't want to!"
    }
}