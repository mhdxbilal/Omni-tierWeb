package com.example.security

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import java.security.KeyStore
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey

/**
 * End-to-End Encrypted Storage Repository
 * Locks identity with a hardware-backed master key.
 */
class VaultRepository {

    private val keyAlias = "AWOE_QUANTUM_MASTER_KEY"
    private val keyStore = KeyStore.getInstance("AndroidKeyStore").apply { load(null) }

    init {
        generateHardwareKey()
    }

    private fun generateHardwareKey() {
        if (!keyStore.containsAlias(keyAlias)) {
            val keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore")
            val keySpec = KeyGenParameterSpec.Builder(
                keyAlias,
                KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
            )
            .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
            .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
            .setKeySize(256)
            // .setUserAuthenticationRequired(true) // Bound to biometric FIDO2 identity
            .build()
            
            keyGenerator.init(keySpec)
            keyGenerator.generateKey()
        }
    }

    fun retrieveQuantumKeyProvider(): SecretKey {
        val secretKeyEntry = keyStore.getEntry(keyAlias, null) as KeyStore.SecretKeyEntry
        return secretKeyEntry.secretKey
    }
}
