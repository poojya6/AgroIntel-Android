package com.Simats.agrointel

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CostBreakdownScreen(
    cost: Double,
    crop: String,
    land: Double,
    onBackClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Cost Breakdown", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color(0xFFF9FAFB))
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            // Header Card - Red
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFE11D48)), // Bright Red
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Total Cultivation Cost", color = Color.White.copy(alpha = 0.9f), fontSize = 14.sp)
                    Text(
                        "₹${cost.toInt()}",
                        color = Color.White,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        "For $land acres of $crop",
                        color = Color.White.copy(alpha = 0.8f),
                        fontSize = 12.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Cost Distribution Chart Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Cost Distribution", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1A2B3C))
                    Spacer(modifier = Modifier.height(24.dp))
                    
                    CostBarChart()
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Detailed Cost Analysis
            Text("Detailed Cost Analysis", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1A2B3C))
            Spacer(modifier = Modifier.height(16.dp))

            CostBreakdownItem("Seeds", "₹20,000", 0.10f, "10.0% of total cost")
            Spacer(modifier = Modifier.height(12.dp))
            CostBreakdownItem("Fertilizer", "₹60,000", 0.30f, "30.0% of total cost")
            Spacer(modifier = Modifier.height(12.dp))
            CostBreakdownItem("Labor", "₹50,000", 0.25f, "25.0% of total cost")
            Spacer(modifier = Modifier.height(12.dp))
            CostBreakdownItem("Water", "₹30,000", 0.15f, "15.0% of total cost")
            Spacer(modifier = Modifier.height(12.dp))
            CostBreakdownItem("Equipment", "₹25,000", 0.125f, "12.5% of total cost")
            Spacer(modifier = Modifier.height(12.dp))
            CostBreakdownItem("Others", "₹15,000", 0.075f, "7.5% of total cost")

            Spacer(modifier = Modifier.height(24.dp))

            // Cost Per Acre
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Cost Per Acre", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1A2B3C))
                    Spacer(modifier = Modifier.height(16.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFFFFF1F2), shape = RoundedCornerShape(12.dp))
                            .padding(20.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text("Average Cost Per Acre", fontSize = 13.sp, color = Color(0xFF667085))
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                "₹${(cost/land).toInt()}",
                                fontSize = 28.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFFE11D48)
                            )
                        }
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
fun CostBarChart() {
    val data = listOf(
        "Seeds" to 20000f,
        "Fertilizer" to 60000f,
        "Labor" to 50000f,
        "Water" to 30000f,
        "Equipment" to 25000f,
        "Others" to 15000f
    )
    val maxCost = 60000f

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ) {
        data.forEach { (label, value) ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom,
                modifier = Modifier.weight(1f)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .fillMaxHeight(value / maxCost)
                        .background(Color(0xFFEF4444), shape = RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp))
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = label,
                    fontSize = 10.sp,
                    color = Color(0xFF667085),
                    maxLines = 1,
                    modifier = Modifier.padding(horizontal = 2.dp)
                )
            }
        }
    }
}

@Composable
fun CostBreakdownItem(title: String, value: String, progress: Float, percentage: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(title, fontSize = 14.sp, color = Color(0xFF344054))
                Text(value, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1A2B3C))
            }
            Spacer(modifier = Modifier.height(8.dp))
            LinearProgressIndicator(
                progress = { progress },
                modifier = Modifier.fillMaxWidth().height(6.dp),
                color = Color(0xFFEF4444),
                trackColor = Color(0xFFF2F4F7),
                strokeCap = StrokeCap.Round
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(percentage, fontSize = 10.sp, color = Color(0xFF98A2B3))
        }
    }
}
