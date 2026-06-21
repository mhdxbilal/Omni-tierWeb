package com.example.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.border
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Check

@Composable
fun PrivacyArmorUI(onBack: () -> Unit) {
    var isFamilyShieldActive by remember { mutableStateOf(false) }
    var blurExplicitContent by remember { mutableStateOf(true) }
    var strictDnsSandboxing by remember { mutableStateOf(true) }
    var biometricLockdown by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1C1C1E)) // Dark Safari Theme
            .padding(top = 16.dp, start = 24.dp, end = 24.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = onBack) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text("Privacy Report", style = AWOETypography.headlineMedium, color = Color.White)
        }
        
        Spacer(modifier = Modifier.height(32.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(Color(0xFF2C2C2E))
                .padding(16.dp)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Device-Level Family Shield", style = AWOETypography.bodyLarge, color = Color.White)
                    Switch(
                        checked = isFamilyShieldActive,
                        onCheckedChange = { isFamilyShieldActive = it },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = AmoledBlack,
                            checkedTrackColor = Color(0xFF34C759)
                        )
                    )
                }

                if (isFamilyShieldActive) {
                    Spacer(modifier = Modifier.height(16.dp))
                    HorizontalDivider(color = Color.DarkGray, thickness = 1.dp)
                    Spacer(modifier = Modifier.height(16.dp))

                    ShieldToggleRow("Explicit Content Filtering", blurExplicitContent) { blurExplicitContent = it }
                    Spacer(modifier = Modifier.height(8.dp))
                    ShieldToggleRow("Deep DNS Sandboxing", strictDnsSandboxing) { strictDnsSandboxing = it }
                    Spacer(modifier = Modifier.height(8.dp))
                    ShieldToggleRow("Biometric Settings Lockdown", biometricLockdown) { biometricLockdown = it }
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    HorizontalDivider(color = Color.DarkGray, thickness = 1.dp)
                    Spacer(modifier = Modifier.height(16.dp))

                    var vaultUnlocked by remember { mutableStateOf(false) }
                    if (!vaultUnlocked) {
                        Button(
                            onClick = { vaultUnlocked = true },
                            modifier = Modifier.fillMaxWidth().height(50.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0A84FF)),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text("Unlock Local Data Vault (Biometric)", color = Color.White, style = MaterialTheme.typography.labelLarge)
                        }
                    } else {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(1.dp, Color(0xFF0A84FF), RoundedCornerShape(8.dp))
                                .padding(16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text("Vault Unlocked: Zero-Knowledge Data Accessed", color = Color(0xFF0A84FF), style = MaterialTheme.typography.labelSmall)
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(80.dp)
                            .background(Color(0xFF1C1C1E), shape = RoundedCornerShape(8.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Threat Neutralization Active",
                            color = Color(0xFF34C759),
                            style = MaterialTheme.typography.labelMedium
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ShieldToggleRow(title: String, checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(title, color = Color.LightGray, style = MaterialTheme.typography.bodyMedium)
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = AmoledBlack,
                checkedTrackColor = Color(0xFF0A84FF)
            )
        )
    }
}
