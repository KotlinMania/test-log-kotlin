// port-lint: tests test-log/src/lib.rs
package io.github.kotlinmania.testlog

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

internal class TestTest {
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
