package com.example.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OmniWebView(
    modifier: Modifier = Modifier,
    onNavigate: (ActiveWorkspace) -> Unit
) {
    // Safari Start Page Redesign
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF1C1C1E)) // Dark Theme Start Page
            .padding(horizontal = 16.dp, vertical = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Text(
                text = "Start Page",
                color = Color.White,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 32.dp)
            )
        }

        // Favorites Grid
        item {
            FavoritesGrid()
            Spacer(modifier = Modifier.height(32.dp))
        }

        // Privacy Report Card
        item {
            StartPageCard(
                title = "Privacy Report",
                subtitle = "In the last 7 days, Omni Shield has prevented 1,024 trackers from profiling you.",
                icon = Icons.Default.Lock,
                iconTint = Color(0xFF34C759), // Green
                onClick = { onNavigate(ActiveWorkspace.SHIELD) }
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        // Media Studio Card
        item {
            StartPageCard(
                title = "Media Enhancement Studio",
                subtitle = "Access 4K NPU Upcaling, sharpening, and zero-copy media pipeline controls.",
                icon = Icons.Default.PlayArrow,
                iconTint = Color(0xFF0A84FF), // Blue
                onClick = { onNavigate(ActiveWorkspace.STUDIO) }
            )
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
fun FavoritesGrid() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFF2C2C2E))
            .padding(16.dp)
    ) {
        Text("Favorites", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 20.sp)
        Spacer(modifier = Modifier.height(16.dp))
        
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            FavoriteItem("Apple", Color(0xFF000000))
            FavoriteItem("iCloud", Color(0xFF0A84FF))
            FavoriteItem("News", Color(0xFFFF2D55))
            FavoriteItem("Notes", Color(0xFFFFCC00))
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            FavoriteItem("Maps", Color(0xFF34C759))
            Spacer(modifier = Modifier.width(24.dp))
            FavoriteItem("Add", Color(0xFF48484A), isAdd = true)
        }
    }
}

@Composable
fun FavoriteItem(name: String, color: Color, isAdd: Boolean = false) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.width(70.dp)
    ) {
        Box(
            modifier = Modifier
                .size(60.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(color),
            contentAlignment = Alignment.Center
        ) {
            if (isAdd) {
                Icon(Icons.Default.Add, contentDescription = "Add", tint = Color.White, modifier = Modifier.size(30.dp))
            } else {
                Text(name.take(1), color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Medium)
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(name, color = Color.LightGray, fontSize = 12.sp, textAlign = TextAlign.Center, maxLines = 1)
    }
}

@Composable
fun StartPageCard(
    title: String,
    subtitle: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    iconTint: Color,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFF2C2C2E))
            .clickable(onClick = onClick)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(iconTint.copy(alpha = 0.2f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(icon, contentDescription = title, tint = iconTint, modifier = Modifier.size(24.dp))
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(title, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(4.dp))
            Text(subtitle, color = Color.Gray, fontSize = 13.sp)
        }
    }
}
