package com.example.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.OnConflictStrategy
import kotlinx.coroutines.flow.Flow

@Database(entities = [SessionEntity::class, SyncLogEntity::class, CacheAllocationEntity::class], version = 1, exportSchema = false)
abstract class VaultDatabase : RoomDatabase() {
    abstract fun sessionDao(): SessionDao
    abstract fun syncLogDao(): SyncLogDao
    abstract fun cacheAllocationDao(): CacheAllocationDao
}

@Dao
interface SessionDao {
    @Query("SELECT * FROM zero_trust_sessions ORDER BY last_sync_timestamp_ms DESC")
    fun getAllSessions(): Flow<List<SessionEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSession(session: SessionEntity)
}

@Dao
interface SyncLogDao {
    @Query("SELECT * FROM offline_sync_log ORDER BY executedAt DESC")
    fun getSyncLogs(): Flow<List<SyncLogEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLog(log: SyncLogEntity)
}
