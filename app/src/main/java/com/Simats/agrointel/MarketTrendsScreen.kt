package com.Simats.agrointel

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.TrendingDown
import androidx.compose.material.icons.automirrored.filled.TrendingUp
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Timeline
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class CropPriceInfo(
    val name: String,
    val price: String,
    val change: String,
    val time: String,
    val volume: String,
    val isRising: Boolean
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MarketTrendsScreen(onBackClick: () -> Unit) {
    val cropPrices = listOf(
        CropPriceInfo("Wheat", "₹2,650", "+12%", "2 mins ago", "High", true),
        CropPriceInfo("Rice", "₹3,200", "+8%", "5 mins ago", "Medium", true),
        CropPriceInfo("Cotton", "₹5,800", "+2%", "1 min ago", "High", true),
        CropPriceInfo("Maize", "₹1,950", "+5%", "3 mins ago", "Low", true),
        CropPriceInfo("Mustard", "₹5,500", "+10%", "4 mins ago", "Medium", true)
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Live Market Trends", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF2563EB))
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color(0xFFF9FAFB))
        ) {
            // Market Status Banner
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF2563EB), shape = RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp))
                    .padding(16.dp)
            ) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.15f)),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .background(Color.White.copy(alpha = 0.2f), shape = CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.Default.Timeline, contentDescription = null, tint = Color.White)
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text("Market Status", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                            Text("Live Updates", color = Color.White.copy(alpha = 0.8f), fontSize = 12.sp)
                        }
                        Box(
                            modifier = Modifier
                                .background(Color(0xFF10B942), shape = RoundedCornerShape(12.dp))
                                .padding(horizontal = 8.dp, vertical = 4.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(modifier = Modifier.size(6.dp).background(Color.White, shape = CircleShape))
                                Spacer(modifier = Modifier.width(4.dp))
                                Text("Active", color = Color.White, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    // Overview Section
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text("Market Overview", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1A2B3C))
                                TextButton(onClick = { }) {
                                    Text("Refresh", fontSize = 12.sp, color = Color(0xFF2563EB))
                                }
                            }
                            Row(modifier = Modifier.fillMaxWidth()) {
                                OverviewMiniCard(
                                    modifier = Modifier.weight(1f),
                                    label = "Rising",
                                    value = "5 Crops",
                                    color = Color(0xFF10B942),
                                    bgColor = Color(0xFFE0F7E9),
                                    icon = Icons.AutoMirrored.Filled.TrendingUp
                                )
                            }
                        }
                    }
                }

                item {
                    Text("Live Prices", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1A2B3C))
                }

                items(cropPrices) { crop ->
                    PriceItemCard(crop)
                }
                
                item { Spacer(modifier = Modifier.height(16.dp)) }
            }
        }
    }
}

@Composable
fun OverviewMiniCard(modifier: Modifier, label: String, value: String, color: Color, bgColor: Color, icon: androidx.compose.ui.graphics.vector.ImageVector) {
    Box(
        modifier = modifier
            .background(bgColor, shape = RoundedCornerShape(12.dp))
            .padding(12.dp)
    ) {
        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(icon, contentDescription = null, tint = color, modifier = Modifier.size(14.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text(label, fontSize = 12.sp, color = Color(0xFF667085))
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(value, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = color)
        }
    }
}

@Composable
fun PriceItemCard(crop: CropPriceInfo) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(crop.name, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1A2B3C))
                Text(crop.time, fontSize = 12.sp, color = Color(0xFF667085))
                Spacer(modifier = Modifier.height(12.dp))
                Text("Trading Volume", fontSize = 12.sp, color = Color(0xFF667085))
            }
            Column(horizontalAlignment = Alignment.End) {
                Text(crop.price, fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1A2B3C))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        if (crop.isRising) Icons.AutoMirrored.Filled.TrendingUp else Icons.AutoMirrored.Filled.TrendingDown,
                        contentDescription = null,
                        tint = if (crop.isRising) Color(0xFF10B942) else Color(0xFFEF4444),
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        crop.change,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = if (crop.isRising) Color(0xFF10B942) else Color(0xFFEF4444)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Box(
                    modifier = Modifier
                        .background(
                            when(crop.volume) {
                                "High" -> Color(0xFFE0F7E9)
                                "Medium" -> Color(0xFFFFF7E6)
                                else -> Color(0xFFF2F4F7)
                            },
                            shape = RoundedCornerShape(12.dp)
                        )
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        crop.volume,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = when(crop.volume) {
                            "High" -> Color(0xFF10B942)
                            "Medium" -> Color(0xFFD4A017)
                            else -> Color(0xFF667085)
                        }
                    )
                }
            }
        }
    }
}
