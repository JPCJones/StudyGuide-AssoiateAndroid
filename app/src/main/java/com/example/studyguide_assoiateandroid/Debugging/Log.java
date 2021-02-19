package com.example.studyguide_assoiateandroid.Debugging;

public class Log {

    // info from - https://developer.android.com/studio/debug/am-logcat
    /*
    Log.e(String, String) (error)
    Log.w(String, String) (warning)
    Log.i(String, String) (information)
    Log.d(String, String) (debug)
    Log.v(String, String) (verbose)

************************************************************
Dalvik log
    garbage collection log message - D/dalvikvm(PID): GC_Reason Amount_freed, Heap_stats, External_memory_stats, Pause_time
    What triggered the GC and what kind of collection it is. Reasons that might appear include:
GC_CONCURRENT
A concurrent GC that frees up memory as your heap begins to fill up.
GC_FOR_MALLOC
A GC was caused because your app attempted to allocate memory when your heap was already full, so the system had to stop your app and reclaim memory.
GC_HPROF_DUMP_HEAP
A GC that occurs when you request to create an HPROF file to analyze your heap.
GC_EXPLICIT
An explicit GC, such as when you call gc() (which you should avoid calling and instead trust the GC to run when needed).
GC_EXTERNAL_ALLOC
This happens only on API level 10 and lower (newer versions allocate everything in the Dalvik heap). A GC for externally allocated memory (such as the pixel data stored in native memory or NIO byte buffers).

Amount freed
The amount of memory reclaimed from this GC.
Heap stats
Percentage free of the heap and (number of live objects)/(total heap size).
External memory stats
Externally allocated memory on API level 10 and lower (amount of allocated memory) / (limit at which collection will occur).
Pause time
Larger heaps will have larger pause times. Concurrent pause times show two pauses: one at the beginning of the collection and another near the end.

*********************************************************
ART log
I/art : Explicit concurrent mark sweep GC freed 104710(7MB) AllocSpace objects,
    21(416KB) LOS objects, 33% free, 25MB/38MB, paused 1.230ms total 67.216ms
What triggered the GC and what kind of collection it is. Reasons that might appear include:
Concurrent
A concurrent GC that does not suspend app threads. This GC runs in a background thread and does not prevent allocations.
Alloc
The GC was initiated because your app attempted to allocate memory when your heap was already full. In this case, the garbage collection occurred in the allocating thread.
Explicit
The garbage collection was explicitly requested by an app, for example, by calling gc() or gc(). Like Dalvik, in ART the best practice is that you trust the GC and avoid requesting explicit GCs, if possible. Explicit GCs are discouraged because they block the allocating thread and unnecessarily waste CPU cycles. Explicit GCs could also cause jank (stuttering, juddering, or halting in the app) if they cause other threads to get preempted.
NativeAlloc
The collection was caused by native memory pressure from native allocations such as Bitmaps or RenderScript allocation objects.
CollectorTransition
The collection was caused by a heap transition; this is caused by changing the GC strategy at run time (such as when the app changes between pause perceptible states). Collector transitions consist of copying all the objects from a free-list backed space to a bump pointer space (or visa versa).
This occurs only on low RAM device prior to Android 8.0 when an app changes process states from a pause perceptible state (such as when the app is in the foreground, where the user can preceive a GC pause) to a non pause perceptible state (or visa versa).

HomogeneousSpaceCompact
Homogeneous space compaction is free-list space to free-list space compaction which usually occurs when an app is moved to a pause imperceptible process state. The main reasons for doing this are reducing RAM usage and defragmenting the heap.
DisableMovingGc
This is not a real GC reason, but a note that collection was blocked due to use of GetPrimitiveArrayCritical. while concurrent heap compaction is occurring. In general, the use of GetPrimitiveArrayCritical is strongly discouraged due to its restrictions on moving collectors.
HeapTrim
This is not a GC reason, but a note that collection was blocked until a heap trim finished.



     */
}
