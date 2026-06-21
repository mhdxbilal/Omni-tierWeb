package com.example.download

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import java.io.RandomAccessFile
import java.net.HttpURLConnection
import java.net.URL
import java.nio.channels.FileChannel

data class DownloadChunk(val rangeStart: Long, val rangeEnd: Long, val url: String, val filePath: String)

/**
 * Parallel Segment Download Core natively slicing downloads into multiple threads
 */
class LegendaryDownloadService {

    private val executionScope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    private val parallelSegments = 8

    fun startParallelDownload(targetUrl: String, destinationPath: String, totalSizeBytes: Long) {
        val segmentSize = totalSizeBytes / parallelSegments
        val chunkChannel = Channel<DownloadChunk>(parallelSegments)

        executionScope.launch {
            // Producer
            for (i in 0 until parallelSegments) {
                val start = i * segmentSize
                val end = if (i == parallelSegments - 1) totalSizeBytes else (start + segmentSize - 1)
                chunkChannel.send(DownloadChunk(start, end, targetUrl, destinationPath))
            }
            chunkChannel.close()
        }

        // Consumers (8 Concurrent Coroutine Handlers processing strictly via FileChannel)
        for (i in 0 until parallelSegments) {
            executionScope.launch {
                for (chunk in chunkChannel) {
                    processDownloadChunk(chunk)
                }
            }
        }
    }

    private suspend fun processDownloadChunk(chunk: DownloadChunk) = withContext(Dispatchers.IO) {
        val url = URL(chunk.url)
        val connection = url.openConnection() as HttpURLConnection
        connection.setRequestProperty("Range", "bytes=\${chunk.rangeStart}-\${chunk.rangeEnd}")
        
        connection.inputStream.use { input ->
            RandomAccessFile(chunk.filePath, "rw").use { file ->
                val channel: FileChannel = file.channel
                channel.position(chunk.rangeStart)
                
                val buffer = java.nio.ByteBuffer.allocateDirect(8192)
                val byteArray = ByteArray(8192)
                var bytesRead: Int
                
                while (input.read(byteArray).also { bytesRead = it } != -1) {
                    buffer.clear()
                    buffer.put(byteArray, 0, bytesRead)
                    buffer.flip()
                    channel.write(buffer)
                }
            }
        }
    }
}
