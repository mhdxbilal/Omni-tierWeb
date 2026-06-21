#include <jni.h>

// Forward declaration from MediaPipelineEngine.cpp
void executeZeroCopyTranscodePipeline(JNIEnv* env, jobject directBuffer, jlong capacity, jstring outputFileDescriptor);

extern "C" JNIEXPORT void JNICALL
Java_com_example_media_MediaPipelineEngine_processDirectBuffer(
        JNIEnv* env,
        jobject /* this */,
        jobject directBuffer,
        jlong capacity,
        jstring outputFileDescriptor) {
    // Perfect native-to-Java interface mappings managing strict memory lifetimes
    executeZeroCopyTranscodePipeline(env, directBuffer, capacity, outputFileDescriptor);
}
