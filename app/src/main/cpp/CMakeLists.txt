cmake_minimum_required(VERSION 3.22.1)

project("awoecore")

# Link C++ NDK Media Codecs and Hardware Buffers natively
find_library(log-lib log)
find_library(android-lib android)
find_library(mediandk-lib mediandk)

add_library(
        mediapipeline_ndk
        SHARED
        JniBridge.cpp
        MediaPipelineEngine.cpp)

target_link_libraries(
        mediapipeline_ndk
        ${log-lib}
        ${android-lib}
        ${mediandk-lib}
)
