package com.example.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuickPrivacyPanel(onDismiss: () -> Unit) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = Color(0xFF2C2C2E), // Safari Dark Mode Sheet
        dragHandle = { BottomSheetDefaults.DragHandle(color = Color.DarkGray) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
        ) {
            Text("Quick Access Privacy", style = AWOETypography.headlineMedium, color = Color.White)
            Spacer(modifier = Modifier.height(16.dp))
            
            var trackers by remember { mutableStateOf(true) }
            var scripts by remember { mutableStateOf(false) }
            var cookies by remember { mutableStateOf(true) }
            var showMonitor by remember { mutableStateOf(true) }

            Text("Site Overrides", color = Color.Gray, style = MaterialTheme.typography.labelSmall)
            Spacer(modifier = Modifier.height(8.dp))
            
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Text("Block Trackers", color = Color.LightGray)
                Switch(checked = trackers, onCheckedChange = { trackers = it }, colors = SwitchDefaults.colors(checkedTrackColor = Color(0xFF34C759)))
            }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Text("Block Scripts", color = Color.LightGray)
                Switch(checked = scripts, onCheckedChange = { scripts = it }, colors = SwitchDefaults.colors(checkedTrackColor = Color(0xFF34C759)))
            }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Text("Block Cookies", color = Color.LightGray)
                Switch(checked = cookies, onCheckedChange = { cookies = it }, colors = SwitchDefaults.colors(checkedTrackColor = Color(0xFF34C759)))
            }

            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Text("Show Telemetry", color = Color.LightGray)
                Switch(checked = showMonitor, onCheckedChange = { showMonitor = it }, colors = SwitchDefaults.colors(checkedTrackColor = Color(0xFF0A84FF)))
            }

            if (showMonitor) {
                Spacer(modifier = Modifier.height(16.dp))
                RechartsMonitor()
            }
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
fun RechartsMonitor() {
    var dataPoints by remember { mutableStateOf(List(20) { Random.nextFloat() * 100 }) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(500)
            dataPoints = dataPoints.drop(1) + (Random.nextFloat() * 100)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFF1C1C1E))
            .padding(16.dp)
    ) {
        Text("Real-Time Telemetry", color = Color(0xFF0A84FF), style = MaterialTheme.typography.labelSmall)
        Spacer(modifier = Modifier.height(8.dp))
        
        Canvas(modifier = Modifier.fillMaxWidth().height(100.dp)) {
            val width = size.width
            val height = size.height
            val stepX = width / (dataPoints.size - 1)
            
            val path = Path()
            dataPoints.forEachIndexed { index, value ->
                val x = index * stepX
                val y = height - (value / 100f * height)
                
                if (index == 0) {
                    path.moveTo(x, y)
                } else {
                    path.lineTo(x, y)
                }
            }
            
            drawPath(
                path = path,
                color = Color(0xFF0A84FF),
                style = Stroke(width = 4f)
            )
        }
    }
}
