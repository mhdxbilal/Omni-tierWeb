package com.example.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Entity(tableName = "cache_allocations")
data class CacheAllocationEntity(
    @PrimaryKey val sectorHexRef: String,
    val allocatedBytes: Long,
    val state: String = "LOCKED"
)

@Dao
interface CacheAllocationDao {
    @Query("SELECT * FROM cache_allocations WHERE sectorHexRef = :hexRef LIMIT 1")
    suspend fun getSector(hexRef: String): CacheAllocationEntity?

    @Query("SELECT * FROM cache_allocations ORDER BY allocatedBytes DESC")
    fun monitorCacheSectors(): Flow<List<CacheAllocationEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateSectorState(allocation: CacheAllocationEntity)
    
    // SQLite tracking query representing Hex-level cache tracking logic mappings
    @Query("UPDATE cache_allocations SET state = 'PURGED' WHERE allocatedBytes > :threshold")
    suspend fun purgeLargeSectors(threshold: Long)
}
