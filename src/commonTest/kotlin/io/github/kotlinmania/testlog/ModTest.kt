// port-lint: tests test-log/src/lib.rs
package io.github.kotlinmania.testlog

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ModTest {
    @Test
    fun testCrateMetadata() {
        assertEquals("test-log", TestLog.CRATE_NAME)
    }

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
}
