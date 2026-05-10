// port-lint: source src/lib.rs
package io.github.kotlinmania.testlog

// Copyright (C) 2019-2025 Daniel Mueller <deso@posteo.net>
// SPDX-License-Identifier: (Apache-2.0 OR MIT)

// A package providing a replacement `@test`-style attribute that initializes
// logging and/or tracing infrastructure before running tests.
//
// Upstream Rust exposes a procedural macro `#[test_log::test]`. Kotlin has no
// procedural-macro mechanism, so this package does not introduce a new
// test-annotation API. Callers wire logging into their `@BeforeTest` setup
// directly, or wrap each test body with the appropriate initializer.
//
// Upstream usage examples — translated to Kotlin-facing shape for reference,
// not as a redirection target inside this package:
//
//   // Specify on a per-test basis.
//   class WorksTest {
//       @BeforeTest fun setup() { /* initialize logging/tracing here */ }
//
//       @Test
//       fun itWorks() {
//           info { "Checking whether it still works..." }
//           assertEquals(4, 2 + 2)
//           info { "Looks good!" }
//       }
//   }
//
// The Rust crate also supported stacking `#[test_log::test]` on top of other
// test attributes such as `#[tokio::test]` for async test bodies. Kotlin's
// `runTest { ... }` from `kotlinx-coroutines-test` plays the equivalent role;
// a logging initializer is just a regular call inside the suspending block.

// Tracking file for upstream `src/lib.rs`. The upstream crate root is composed
// entirely of `pub use` re-exports; per the workspace rule on re-exports
// (CLAUDE.md `## Re-exports from upstream `mod.rs` files`), no Kotlin
// `typealias` is introduced. Callers reach the upstream symbol directly via
// explicit `import <path> as <name>` aliasing when a name match is desired.

// Upstream `pub use` lines:
//   `pub use test_log_macros::test;`
//       — the procedural macro. Has no Kotlin analog and no peer port (the
//         upstream `test-log-macros` crate has no `*-kotlin` sibling). Callers
//         initialize logging from `@BeforeTest` or a wrapper function instead
//         of relying on attribute rewriting. When a Kotlin port of
//         `test-log-macros` is created it will live at
//         `io.github.kotlinmania.testlogmacros`; callers import from there
//         directly rather than through a re-export here.
//   `#[cfg(feature = "trace")] pub use tracing_subscriber;`
//       — gated on the `trace` Cargo feature upstream. The Kotlin counterpart
//         lives in `tracing-subscriber-kotlin`
//         (`io.github.kotlinmania.tracingsubscriber`); callers import its
//         symbols directly rather than through a re-export here.
//   `#[cfg(feature = "log")] pub use env_logger;`
//       — gated on the `log` Cargo feature upstream. The Kotlin counterpart
//         lives in `env-logger-kotlin` (`io.github.kotlinmania.envlogger`);
//         callers import its symbols directly rather than through a re-export
//         here.

// Callers migrated:
//   (none — workspace audit confirmed zero kotlinmania repos import any
//    symbol exported from `test_log` at the time this tracking file was
//    written. RUST_CALLERS.md records zero cross-repo inbound `use` sites
//    against `test-log`.)

// Projected callers (Rust):
//   workspace_dep_graph.json shows zero kotlinmania repos importing
//   `test_log::test`, `test_log::tracing_subscriber`, or `test_log::env_logger`
//   from upstream Rust. Future Kotlin ports of any downstream consumer should
//   target the upstream symbol directly:
//     - the not-yet-existing `io.github.kotlinmania.testlogmacros` for `test`
//     - `io.github.kotlinmania.tracingsubscriber` for `tracing_subscriber`
//     - `io.github.kotlinmania.envlogger` for `env_logger`
