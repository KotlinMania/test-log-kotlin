package io.github.kotlinmania.testlog

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

internal class TestTest {
    @Test
    fun testWrapperUnitReturn() {
        var initialized = false
        var executed = false
        test(
            initialize = { initialized = true },
        ) {
            executed = true
        }
        assertTrue(initialized)
        assertTrue(executed)
    }

    @Test
    fun runsInitializerBeforeBody() {
        val events = mutableListOf<String>()

        val result =
            test(
                initialize = { events.add("initialize") },
            ) {
                events.add("body")
                4
            }

        assertEquals(listOf("initialize", "body"), events)
        assertEquals(4, result)
    }

    @Test
    fun propagatesBodyFailureAfterInitializing() {
        val events = mutableListOf<String>()

        assertFailsWith<IllegalStateException> {
            test(
                initialize = { events.add("initialize") },
            ) {
                events.add("body")
                throw IllegalStateException("body failed")
            }
        }

        assertEquals(listOf("initialize", "body"), events)
    }

    @Test
    fun propagatesInitializerFailureWithoutRunningBody() {
        val events = mutableListOf<String>()

        assertFailsWith<IllegalStateException> {
            test(
                initialize = {
                    events.add("initialize")
                    throw IllegalStateException("initialize failed")
                },
            ) {
                events.add("body")
            }
        }

        assertEquals(listOf("initialize"), events)
    }
}
