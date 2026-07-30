package com.Simats.agrointel

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.NavigateNext
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(onBackClick: () -> Unit, onEditProfileClick: () -> Unit) {
    var notificationsOn by remember { mutableStateOf(true) }
    var darkModeOn by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Settings & Reports", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color(0xFFF9FAFB))
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // Saved Reports Section
            item {
                SectionHeader("Saved Reports")
                Spacer(modifier = Modifier.height(12.dp))
                ReportItem("Wheat Profit Analysis", "Profit Report • 2.4 MB", "May 8, 2026")
                Spacer(modifier = Modifier.height(12.dp))
                ReportItem("Monthly Revenue Apr 2026", "Revenue Report • 1.8 MB", "May 1, 2026")
                Spacer(modifier = Modifier.height(12.dp))
                ReportItem("Crop Comparison Report", "Comparison • 3.2 MB", "Apr 25, 2026")
            }

            // Account Settings Section
            item {
                SectionHeader("Account Settings")
                Spacer(modifier = Modifier.height(12.dp))
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Column {
                        SettingsRow(Icons.Outlined.Person, "Edit Profile", onClick = onEditProfileClick)
                        HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp), color = Color(0xFFF2F4F7))
                        SettingsRow(Icons.Outlined.Lock, "Change Password")
                        HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp), color = Color(0xFFF2F4F7))
                        SettingsToggleRow(Icons.Outlined.Notifications, "Notifications", notificationsOn) { notificationsOn = it }
                    }
                }
            }

            // Preferences Section
            item {
                SectionHeader("Preferences")
                Spacer(modifier = Modifier.height(12.dp))
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Column {
                        SettingsRow(Icons.Outlined.Language, "Language", trailingText = "English")
                        HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp), color = Color(0xFFF2F4F7))
                        SettingsToggleRow(Icons.Outlined.DarkMode, "Dark Mode", darkModeOn) { darkModeOn = it }
                    }
                }
            }

            // Support Section
            item {
                SectionHeader("Support")
                Spacer(modifier = Modifier.height(12.dp))
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Column {
                        SettingsRow(Icons.Outlined.HelpOutline, "Help & Support")
                        HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp), color = Color(0xFFF2F4F7))
                        SettingsRow(Icons.Outlined.Description, "Terms & Privacy")
                    }
                }
            }
            
            item { Spacer(modifier = Modifier.height(16.dp)) }
        }
    }
}

@Composable
fun SectionHeader(title: String) {
    Text(
        text = title,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF1A2B3C)
    )
}

@Composable
fun ReportItem(title: String, subtitle: String, date: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(Color(0xFFE0F7E9), shape = RoundedCornerShape(8.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.Description, contentDescription = null, tint = Color(0xFF10B942))
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(title, fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1A2B3C))
                    Text(subtitle, fontSize = 12.sp, color = Color(0xFF667085))
                }
                IconButton(
                    onClick = { },
                    modifier = Modifier
                        .background(Color(0xFFF2F4F7), shape = CircleShape)
                        .size(32.dp)
                ) {
                    Icon(Icons.Default.Download, contentDescription = "Download", modifier = Modifier.size(16.dp), tint = Color(0xFF10B942))
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(date, fontSize = 10.sp, color = Color(0xFF98A2B3))
        }
    }
}

@Composable
fun SettingsRow(icon: ImageVector, title: String, trailingText: String? = null, onClick: () -> Unit = {}) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, contentDescription = null, tint = Color(0xFF667085), modifier = Modifier.size(20.dp))
        Spacer(modifier = Modifier.width(16.dp))
        Text(title, modifier = Modifier.weight(1f), fontSize = 14.sp, color = Color(0xFF1A2B3C))
        if (trailingText != null) {
            Text(trailingText, fontSize = 12.sp, color = Color(0xFF667085))
            Spacer(modifier = Modifier.width(4.dp))
        }
        Icon(Icons.AutoMirrored.Filled.NavigateNext, contentDescription = null, tint = Color(0xFFD0D5DD), modifier = Modifier.size(16.dp))
    }
}

@Composable
fun SettingsToggleRow(icon: ImageVector, title: String, checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, contentDescription = null, tint = Color(0xFF667085), modifier = Modifier.size(20.dp))
        Spacer(modifier = Modifier.width(16.dp))
        Text(title, modifier = Modifier.weight(1f), fontSize = 14.sp, color = Color(0xFF1A2B3C))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(if (checked) "On" else "Off", fontSize = 12.sp, color = Color(0xFF667085))
            Spacer(modifier = Modifier.width(8.dp))
            Switch(
                checked = checked,
                onCheckedChange = onCheckedChange,
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.White,
                    checkedTrackColor = Color(0xFF10B942),
                    uncheckedThumbColor = Color.White,
                    uncheckedTrackColor = Color(0xFFD0D5DD),
                    uncheckedBorderColor = Color.Transparent
                )
            )
        }
    }
}
