// port-lint: source src/lib.rs
package io.github.kotlinmania.testlog

// Copyright (C) 2019-2025 Daniel Mueller <deso@posteo.net>
// SPDX-License-Identifier: (Apache-2.0 OR MIT)

/**
 * Run a test body after initializing logging or tracing support.
 *
 * The upstream crate exposes the crate-root `test` item as an attribute
 * that rewrites a test function so logging and tracing initialization
 * happen before the body executes. Kotlin Multiplatform has no portable
 * procedural-attribute mechanism, so the port keeps the same crate-root
 * function name and makes the transformation explicit: keep the regular
 * Kotlin test annotation on the test function and wrap the body with this
 * helper.
 *
 * Example:
 *
 *     @Test
 *     fun itWorks() = test(
 *         initialize = { initializeLogging() },
 *     ) {
 *         info("Checking whether it still works...")
 *         assertEquals(4, 2 + 2)
 *         info("Looks good!")
 *     }
 *
 * A whole test class can use the same shape by routing each test through
 * a shared initializer function. Tests that run inside another builder,
 * such as a coroutine test builder, keep that builder at the outer test
 * boundary and call this helper inside it.
 *
 * The upstream default initializer is supplied by the optional logging and
 * tracing dependencies. Those sibling Kotlin ports are not available to
 * this package yet, so the initializer is an explicit parameter instead
 * of a silent no-op.
 */
public inline fun <T> test(initialize: () -> Unit, body: () -> T): T {
    initialize()
    return body()
}
