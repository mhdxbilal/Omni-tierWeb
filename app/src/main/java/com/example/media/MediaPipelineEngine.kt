package com.example.media

import java.nio.ByteBuffer

/**
 * Native C++ Hardware Buffer Binding for Media Transcoding
 * using zero-copy Direct Byte Buffers.
 */
class MediaPipelineEngine {
    init {
        System.loadLibrary("mediapipeline_ndk")
    }
    
    external fun processDirectBuffer(directBuffer: ByteBuffer, capacity: Long, outputFd: String)
}
