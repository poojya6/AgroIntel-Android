package com.Simats.agrointel

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class DailyForecast(
    val day: String,
    val icon: ImageVector,
    val condition: String,
    val temp: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherScreen(onBackClick: () -> Unit) {
    val forecast = listOf(
        DailyForecast("Mon", Icons.Default.WbSunny, "Sunny", "32°"),
        DailyForecast("Tue", Icons.Default.Cloud, "Cloudy", "30°"),
        DailyForecast("Wed", Icons.Default.WaterDrop, "Rainy", "28°"),
        DailyForecast("Thu", Icons.Default.Cloud, "Cloudy", "29°"),
        DailyForecast("Fri", Icons.Default.WbSunny, "Sunny", "31°")
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Weather Overview", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.White) },
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
                .background(Color(0xFF2563EB))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Ludhiana, Punjab", color = Color.White.copy(alpha = 0.8f), fontSize = 16.sp)
                Spacer(modifier = Modifier.height(16.dp))
                Icon(
                    Icons.Default.WbSunny,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(80.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text("28°C", color = Color.White, fontSize = 64.sp, fontWeight = FontWeight.Bold)
                Text("Partly Cloudy", color = Color.White, fontSize = 20.sp)
                Text("Feels like 30°C", color = Color.White.copy(alpha = 0.8f), fontSize = 14.sp)
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                WeatherDetailCard(Modifier.weight(1f), "Wind", "12 km/h", Icons.Default.Air)
                WeatherDetailCard(Modifier.weight(1f), "Humidity", "65%", Icons.Default.WaterDrop)
                WeatherDetailCard(Modifier.weight(1f), "Rain", "20%", Icons.Default.Cloud)
            }

            Spacer(modifier = Modifier.height(24.dp))

            // White rounded bottom section
            Card(
                modifier = Modifier.fillMaxSize(),
                shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    item {
                        Text(
                            "5-Day Forecast",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF1A2B3C)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                    items(forecast) { item ->
                        ForecastRow(item)
                    }
                }
            }
        }
    }
}

@Composable
fun WeatherDetailCard(modifier: Modifier, label: String, value: String, icon: ImageVector) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.15f)),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Icon(icon, contentDescription = null, tint = Color.White, modifier = Modifier.size(20.dp))
            Spacer(modifier = Modifier.height(8.dp))
            Text(label, color = Color.White.copy(alpha = 0.7f), fontSize = 12.sp)
            Text(value, color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun ForecastRow(item: DailyForecast) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF9FAFB), shape = RoundedCornerShape(12.dp))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(item.day, modifier = Modifier.width(60.dp), fontWeight = FontWeight.Bold, color = Color(0xFF1A2B3C))
        Row(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(item.icon, contentDescription = null, tint = Color(0xFF2563EB), modifier = Modifier.size(20.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text(item.condition, fontSize = 14.sp, color = Color(0xFF667085))
        }
        Text(item.temp, fontWeight = FontWeight.Bold, color = Color(0xFF1A2B3C))
    }
}
