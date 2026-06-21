package com.example.ui

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
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

enum class ActiveWorkspace {
    WEB, STUDIO, SHIELD, QUICK_PRIVACY
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AWOEScaffold(modifier: Modifier = Modifier) {
    var activeWorkspace by remember { mutableStateOf(ActiveWorkspace.WEB) }
    var searchQuery by remember { mutableStateOf("") }
    var isSearchExpanded by remember { mutableStateOf(false) }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = Color(0xFF1C1C1E), // Safari Dark Mode Background
        bottomBar = {
            SafariBottomBar(
                searchQuery = searchQuery,
                onSearchQueryChange = { searchQuery = it },
                isExpanded = isSearchExpanded,
                onExpandedChange = { isSearchExpanded = it },
                onMenuClick = { activeWorkspace = ActiveWorkspace.QUICK_PRIVACY }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            when (activeWorkspace) {
                ActiveWorkspace.WEB -> OmniWebView(
                    onNavigate = { activeWorkspace = it }
                )
                ActiveWorkspace.STUDIO -> MediaEnhancementStudio(
                    onBack = { activeWorkspace = ActiveWorkspace.WEB }
                )
                ActiveWorkspace.SHIELD -> PrivacyArmorUI(
                    onBack = { activeWorkspace = ActiveWorkspace.WEB }
                )
                ActiveWorkspace.QUICK_PRIVACY -> {
                    OmniWebView(onNavigate = { activeWorkspace = it })
                    QuickPrivacyPanel(onDismiss = { activeWorkspace = ActiveWorkspace.WEB })
                }
            }
        }
    }
}

@Composable
fun SafariBottomBar(
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    isExpanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    onMenuClick: () -> Unit
) {
    // Safari iOS style unified bottom bar
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xE61C1C1E)) // Frosted glass effect placeholder
            .padding(vertical = 8.dp, horizontal = 16.dp)
    ) {
        // Floating Address Bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFF2C2C2E))
                .clickable { onExpandedChange(!isExpanded) }
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "aA",
                color = Color.White,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                modifier = Modifier.clickable { onMenuClick() }
            )
            Spacer(modifier = Modifier.width(12.dp))
            if (isExpanded) {
                TextField(
                    value = searchQuery,
                    onValueChange = onSearchQueryChange,
                    modifier = Modifier.weight(1f),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedTextColor = Color.White
                    ),
                    placeholder = { Text("Search or enter address", color = Color.Gray, fontSize = 16.sp) },
                    singleLine = true
                )
            } else {
                Text(
                    text = if (searchQuery.isEmpty()) "Search or enter address" else searchQuery,
                    color = if (searchQuery.isEmpty()) Color.Gray else Color.White,
                    fontSize = 16.sp,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Icon(
                imageVector = Icons.Default.Refresh,
                contentDescription = "Reload",
                tint = Color.White,
                modifier = Modifier.size(20.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Tool bar icons
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back", tint = Color.LightGray, modifier = Modifier.size(24.dp))
            Icon(Icons.AutoMirrored.Filled.ArrowForward, "Forward", tint = Color.DarkGray, modifier = Modifier.size(24.dp))
            Icon(Icons.Default.Share, "Share", tint = Color(0xFF0A84FF), modifier = Modifier.size(24.dp))
            Icon(Icons.Default.FavoriteBorder, "Bookmarks", tint = Color(0xFF0A84FF), modifier = Modifier.size(24.dp))
            Icon(Icons.AutoMirrored.Filled.List, "Tabs", tint = Color(0xFF0A84FF), modifier = Modifier.size(24.dp))
        }
        Spacer(modifier = Modifier.height(8.dp))
    }
}
