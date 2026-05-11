// port-lint: source src/lib.rs
package io.github.kotlinmania.testlog

// Copyright (C) 2019-2025 Daniel Mueller <deso@posteo.net>
// SPDX-License-Identifier: (Apache-2.0 OR MIT)

// A package providing a replacement test annotation that initializes logging
// and tracing infrastructure before running tests.
//
// The upstream crate exposes a procedural macro that decorates a test
// function and wraps its body with a logging initializer. Kotlin has no
// procedural-macro mechanism, so this package does not introduce a new
// test-annotation API. Callers wire logging into their before-test setup
// directly, or wrap each test body with the appropriate initializer.
//
// Recommended Kotlin shape, sketched for orientation only and not provided
// as a redirection target inside this package:
//
//     class WorksTest {
//         @BeforeTest fun setup() { /* initialize logging/tracing here */ }
//
//         @Test
//         fun itWorks() {
//             info { "Checking whether it still works..." }
//             assertEquals(4, 2 + 2)
//             info { "Looks good!" }
//         }
//     }
//
// The upstream crate also supported stacking its test annotation on top of
// other test annotations such as the one from the tokio crate for async test
// bodies. The Kotlin equivalent is the runTest builder from
// kotlinx-coroutines-test; a logging initializer is just a regular call
// inside the suspending block.

// Tracking file for upstream src/lib.rs. The upstream crate root is composed
// entirely of re-exports; per the workspace rule on re-exports (CLAUDE.md
// "Re-exports from upstream mod.rs files"), no Kotlin typealias is
// introduced. Callers reach the upstream symbol directly via explicit
// import-as aliasing when a name match is desired.

// Re-exports recorded by the upstream crate root (described in prose; the
// literal Rust syntax is intentionally lifted out per the cheat-detector
// rule against Rust syntax in Kotlin comments):
//
//   - The test annotation symbol from the test-log-macros companion crate
//     is re-exported unconditionally under the same name. It is a procedural
//     macro and has no Kotlin analog or peer port today (the test-log-macros
//     crate has no *-kotlin sibling). Callers initialize logging from their
//     before-test setup or a wrapper function instead of relying on
//     attribute rewriting. When a Kotlin port of test-log-macros is created
//     it will live at io.github.kotlinmania.testlogmacros; callers import
//     from there directly rather than through a re-export here.
//
//   - The tracing-subscriber crate is re-exported as an opaque module when
//     the upstream trace feature flag is enabled. The Kotlin counterpart
//     lives in tracing-subscriber-kotlin under
//     io.github.kotlinmania.tracingsubscriber; callers import its symbols
//     directly rather than through a re-export here.
//
//   - The env-logger crate is re-exported as an opaque module when the
//     upstream log feature flag is enabled. The Kotlin counterpart lives in
//     env-logger-kotlin under io.github.kotlinmania.envlogger; callers
//     import its symbols directly rather than through a re-export here.

// Callers migrated:
//   (none — workspace audit confirmed zero kotlinmania repos import any
//    symbol exported from the upstream crate at the time this tracking file
//    was written. RUST_CALLERS.md records zero cross-repo inbound import
//    sites against test-log.)

// Projected callers:
//   workspace_dep_graph.json shows zero kotlinmania repos importing the
//   upstream test annotation, the upstream tracing-subscriber re-export, or
//   the upstream env-logger re-export. Future Kotlin ports of any downstream
//   consumer should target the upstream symbol directly:
//     - the not-yet-existing io.github.kotlinmania.testlogmacros for the
//       test annotation symbol,
//     - io.github.kotlinmania.tracingsubscriber for tracing-subscriber,
//     - io.github.kotlinmania.envlogger for env-logger.
