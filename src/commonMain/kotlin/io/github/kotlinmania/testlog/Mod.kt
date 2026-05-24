// port-lint: source src/lib.rs
package io.github.kotlinmania.testlog

// Copyright (C) 2019-2025 Daniel Mueller <deso@posteo.net>
// SPDX-License-Identifier: (Apache-2.0 OR MIT)

// A package providing a replacement test wrapper that initializes logging
// or tracing infrastructure before running tests.
//
// The upstream crate exposes a crate-root item named test that decorates a
// test function and wraps its body with a logging initializer. Kotlin has
// no procedural-attribute mechanism, so the callable Kotlin translation is
// the explicit wrapper in Test.kt. Callers keep the regular Kotlin test
// annotation on the function and call test with their logging initializer
// around the body.

// Tracking file for the remaining upstream crate-root re-exports. Per the
// workspace rule on re-exports, no Kotlin typealias is introduced. Callers
// reach the real sibling package directly when that package exists.

// Re-exports recorded by the upstream crate root:
//
//   - The test symbol from the test-log-macros companion crate is exported
//     unconditionally under the same name. The companion crate is only the
//     procedural-attribute implementation; Kotlin callers use the wrapper
//     in Test.kt for the same initialize-then-run behavior.
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
//   upstream test helper, the upstream tracing-subscriber re-export, or the
//   upstream env-logger re-export. Future Kotlin ports of any downstream
//   consumer should target the real Kotlin location directly:
//     - io.github.kotlinmania.testlog.test for the test wrapper,
//     - io.github.kotlinmania.tracingsubscriber for tracing-subscriber,
//     - io.github.kotlinmania.envlogger for env-logger.
