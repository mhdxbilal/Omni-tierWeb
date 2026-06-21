package com.example.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MediaEnhancementStudio(onBack: () -> Unit) {
    var upscaling by remember { mutableFloatStateOf(75f) }
    var sharpening by remember { mutableFloatStateOf(60f) }
    var blurFix by remember { mutableFloatStateOf(50f) }
    
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
            Text("Media Studio", style = AWOETypography.headlineMedium, color = Color.White)
        }
        
        Spacer(modifier = Modifier.height(32.dp))
        
        Text("4K Video Upscaling Pipeline", style = AWOETypography.bodyLarge, color = Color.White)
        Spacer(modifier = Modifier.height(4.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("${upscaling.toInt()}%", color = Color(0xFF0A84FF), modifier = Modifier.width(48.dp))
            Slider(
                value = upscaling,
                onValueChange = { upscaling = it },
                valueRange = 0f..100f,
                steps = 100,
                colors = SliderDefaults.colors(thumbColor = Color(0xFF0A84FF), activeTrackColor = Color(0xFF0A84FF))
            )
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        var neuralUpscaling by remember { mutableStateOf(true) }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text("Enable NPU Neural Upscaling", style = AWOETypography.bodyLarge, color = Color.White)
                Text("Hardware acceleration for high-definition video", color = Color.Gray, style = MaterialTheme.typography.labelSmall)
            }
            Switch(
                checked = neuralUpscaling,
                onCheckedChange = { neuralUpscaling = it },
                colors = SwitchDefaults.colors(checkedThumbColor = AmoledBlack, checkedTrackColor = Color(0xFF0A84FF))
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text("Aggressive Image Sharpening", style = AWOETypography.bodyLarge, color = Color.White)
        Spacer(modifier = Modifier.height(4.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("${sharpening.toInt()}%", color = Color(0xFF34C759), modifier = Modifier.width(48.dp))
            Slider(
                value = sharpening,
                onValueChange = { sharpening = it },
                valueRange = 0f..100f,
                steps = 100,
                colors = SliderDefaults.colors(thumbColor = Color(0xFF34C759), activeTrackColor = Color(0xFF34C759))
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text("Motion Blur Rectification", style = AWOETypography.bodyLarge, color = Color.White)
        Spacer(modifier = Modifier.height(4.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("${blurFix.toInt()}%", color = Color(0xFFFFCC00), modifier = Modifier.width(48.dp))
            Slider(
                value = blurFix,
                onValueChange = { blurFix = it },
                valueRange = 0f..100f,
                steps = 100,
                colors = SliderDefaults.colors(thumbColor = Color(0xFFFFCC00), activeTrackColor = Color(0xFFFFCC00))
            )
        }
        
        Spacer(modifier = Modifier.height(32.dp))
        Text("Batch Processing Pipeline", style = AWOETypography.bodyLarge, color = Color.White)
        Spacer(modifier = Modifier.height(16.dp))
        
        LazyRow(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            items(5) { index ->
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(
                        modifier = Modifier
                            .size(120.dp, 80.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color(0xFF2C2C2E)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.PlayArrow, contentDescription = null, tint = Color.LightGray)
                        Text(
                            "4K RAW",
                            color = AmoledBlack,
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .padding(4.dp)
                                .background(Color(0xFF0A84FF), RoundedCornerShape(4.dp))
                                .padding(horizontal = 4.dp),
                            style = AWOETypography.labelMedium
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Segment_${index}.mp4", color = Color.Gray, style = MaterialTheme.typography.labelSmall)
                }
            }
        }
        
        Spacer(modifier = Modifier.weight(1f))
        
        Button(
            onClick = { /* Trigger instantaneous 4K media batch-processing */ },
            modifier = Modifier.fillMaxWidth().height(56.dp).padding(bottom = 8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0A84FF)),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text("ENGAGE HYPER-BATCH PROCESSING", color = Color.White, fontWeight = androidx.compose.ui.text.font.FontWeight.Bold)
        }
    }
}
