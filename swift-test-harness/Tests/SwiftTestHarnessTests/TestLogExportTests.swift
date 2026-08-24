import Testing
import TestLog

// Smoke test for the Kotlin → Swift Export → SPM → swift test pipeline.
@Suite("TestLog Swift Export Smoke Tests")
struct TestLogExportTests {
    @Test("TestLog swift module imported cleanly")
    func testSwiftModuleLoads() {
        #expect(Bool(true))
    }
}
