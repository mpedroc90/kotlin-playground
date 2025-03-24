package com.example.demo

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import kotlin.test.Test


    @SpringBootTest(
        classes = arrayOf(DemoApplication::class),
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
    class KotlinTestingDemoApplicationIntegrationTest {

        @Autowired
        lateinit var restTemplate: TestRestTemplate


        @Test
        fun whenGetCalled_thenShouldBadReqeust() {
            val result = restTemplate.getForEntity("/api/bankAccount?id=2", BankAccount::class.java);

            assertNotNull(result)
            assertEquals(HttpStatus.BAD_REQUEST, result?.statusCode)
        }
    }
