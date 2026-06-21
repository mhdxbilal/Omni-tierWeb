package com.example.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo

@Entity(tableName = "zero_trust_sessions")
data class SessionEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val domain: String,
    
    @ColumnInfo(name = "encrypted_token")
    val encryptedToken: String, // Post-Quantum Encrypted String Placeholder
    
    @ColumnInfo(name = "zkp_proof_signature")
    val zkpProofSignature: String, // Zero-Knowledge Proof Identity verification hash
    
    @ColumnInfo(name = "last_sync_timestamp_ms")
    val lastSyncTimestampMs: Long = System.currentTimeMillis(),
    
    @ColumnInfo(name = "sync_status")
    val syncStatus: String = "PENDING_P2P" // Local offline-first operational sync schema
)

@Entity(tableName = "offline_sync_log")
data class SyncLogEntity(
    @PrimaryKey(autoGenerate = true) val syncId: Long = 0,
    val nodeAddress: String,
    val ipfsCid: String,
    val hexSignature: String,
    val executedAt: Long = System.currentTimeMillis()
)
