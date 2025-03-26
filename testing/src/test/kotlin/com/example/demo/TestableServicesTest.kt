package com.example.demo

import io.mockk.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class TestableServicesTest {


    /**
     * When class instance is mock, we need to provide the behavior of the method we are going to use
     * otherwise a `io.mockk.MockKException: no answer found for:...`  will be thrown.
     * This could be very handy when it is wanted to do a white box text because we are force to provide
     * all the behavior for dependencies
     * */
    @Test
    fun givenServiceMock_whenCallingMockedMethod_thenCorrectlyVerified() {
        // given
        val service = mockk<TestableService>()
        every { service.getDataFromDb("Expected Param") } returns "Expected Output"

        // when
        val result = service.getDataFromDb("Expected Param")


        // then
        verify { service.getDataFromDb("Expected Param") }
        assertEquals("Expected Output", result)
    }


    /**
     * Spy allows to call the method code unless it is stub, it also allows to verify the behavior.
     * It also tracks all behaviors implemented by the class
     */
    @Test
    fun givenServiceSpy_whenMockingOnlyOneMethod_thenOtherMethodsShouldBehaveAsOriginalObject() {
        // given
        val service = spyk<TestableService>()
        every { service.getDataFromDb("kk") } returns "Mocked Output"

        // when checking mocked method
        val withoutStubbingTheMethod = service.getDataFromDb("Any Param")
        val stubbingMethod = service.getDataFromDb("kk")

        // then
        assertEquals("FAKE RESPONSE", withoutStubbingTheMethod)
        assertEquals("Mocked Output", stubbingMethod)



        // when checking not mocked method
        val secondResult = service.doSomethingElse("Any Param")

        // then
        assertEquals("I don't want to!", secondResult)

        verifyAll() {
            service.getDataFromDb("Any Param")
            service.getDataFromDb("kk")
            service.doSomethingElse("Any Param")
        }
    }

    /** Relaxed mockk allows provides a returning default value  for all methods or the target class unless
     *  it is stubbed. Different from spy that when the method is not stubbed it calls the method code.
     *  */
    @Test
    fun givenRelaxedMock_whenCallingNotMockedMethod_thenReturnDefaultValue() {
        // given
        val service = mockk<TestableService>(relaxed = true)

        every { service.getDataFromDb(any()) } returns  "a"

        // when
        val result = service.getDataFromDb("Any Param")

        val result1 = service.doSomethingElse("Any Param")

        // then
        assertEquals("a", result)
        assertEquals("", result1)

        verify {
            service.getDataFromDb("Any Param")
        }
    }
}