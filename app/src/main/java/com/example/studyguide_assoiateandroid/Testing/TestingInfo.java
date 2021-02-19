package com.example.studyguide_assoiateandroid.Testing;

public class TestingInfo {
    /*
    info from - https://developer.android.com/training/testing/fundamentals?authuser=1

    real vs fake objects

 use real if...
The object is a data object.
The object cannot function unless it communicates with the real object version of a dependency. A good example is an event callback handler.
It's hard to replicate the object's communication with a dependency. A good example is a SQL database handler, where an in-memory database provides more robust tests than fakes of database results.

use fake if...
Long operations, such as processing a large file.
Non-hermetic actions, such as connecting to an arbitrary open port.
Hard-to-create configurations.

test size break down
70 percent small, 20 percent medium, and 10 percent large.

Use the AndroidX Test APIs whenever possible so that your unit tests can run on a device or emulator.
For tests that always run on a JVM-powered development machine, you can use ******Robolectric.******** - supports Component lifeCycles, Event loops, All resources

If you need a test to execute on the main thread, annotate it using @UiThreadTest.
     */
}
