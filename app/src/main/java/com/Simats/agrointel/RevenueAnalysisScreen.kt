package com.Simats.agrointel

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RevenueAnalysisScreen(
    revenue: Double,
    cost: Double,
    profit: Double,
    yield: Double,
    crop: String,
    land: Double,
    onBackClick: () -> Unit
){
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Revenue Analysis", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
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
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF10B942)),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Total Revenue", color = Color.White.copy(alpha = 0.9f), fontSize = 14.sp)
                    Text(
                        "₹${revenue.toInt()}",
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

            // Revenue Distribution Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Revenue Distribution", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1A2B3C))
                    Spacer(modifier = Modifier.height(32.dp))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        RevenuePieChart()
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Revenue Breakdown Section
            Column(modifier = Modifier.fillMaxWidth()) {
                Text("Revenue Breakdown", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1A2B3C))
                Spacer(modifier = Modifier.height(16.dp))

                BreakdownItem(
                    "Main Crop Sale",
                    "₹${revenue.toInt()}",
                    Color(0xFF10B942),
                    1f
                )
                Spacer(modifier = Modifier.height(12.dp))
                BreakdownItem("By-products", "₹40,000", Color(0xFF86EFAC), 0.08f)
                Spacer(modifier = Modifier.height(12.dp))
                BreakdownItem("Government Subsidy", "₹16,000", Color(0xFFDCFCE7), 0.04f)
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Revenue Per Acre Section
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Revenue Per Acre", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1A2B3C))
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(modifier = Modifier.fillMaxWidth()) {
                        PerAcreCard(
                            Modifier.weight(1f),
                            "Main Yield",
                            "₹${(revenue/land).toInt()}"
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        PerAcreCard(
                            Modifier.weight(1f),
                            "Total/Acre",
                            "₹${(revenue/land).toInt()}"
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
fun RevenuePieChart() {
    Canvas(modifier = Modifier.size(160.dp)) {
        // Simple representation of the ring chart
        drawArc(
            color = Color(0xFFF2F4F7),
            startAngle = 0f,
            sweepAngle = 360f,
            useCenter = false,
            style = Stroke(width = 24.dp.toPx(), cap = StrokeCap.Round)
        )
        drawArc(
            color = Color(0xFF10B942),
            startAngle = -90f,
            sweepAngle = 280f,
            useCenter = false,
            style = Stroke(width = 24.dp.toPx(), cap = StrokeCap.Round)
        )
        drawArc(
            color = Color(0xFF86EFAC),
            startAngle = 190f,
            sweepAngle = 40f,
            useCenter = false,
            style = Stroke(width = 24.dp.toPx(), cap = StrokeCap.Round)
        )
    }
}

@Composable
fun BreakdownItem(title: String, value: String, color: Color, progress: Float) {
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
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(modifier = Modifier.size(12.dp).background(color, shape = CircleShape))
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(title, fontSize = 14.sp, color = Color(0xFF344054))
                }
                Text(value, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1A2B3C))
            }
            Spacer(modifier = Modifier.height(12.dp))
            LinearProgressIndicator(
                progress = progress,
                modifier = Modifier.fillMaxWidth().height(8.dp),
                color = color,
                trackColor = Color(0xFFF2F4F7),
                strokeCap = StrokeCap.Round
            )
        }
    }
}

@Composable
fun PerAcreCard(modifier: Modifier, label: String, value: String) {
    Box(
        modifier = modifier
            .background(Color(0xFFF0FDF4), shape = RoundedCornerShape(12.dp))
            .padding(16.dp)
    ) {
        Column {
            Text(label, fontSize = 12.sp, color = Color(0xFF667085))
            Spacer(modifier = Modifier.height(4.dp))
            Text(value, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1A2B3C))
        }
    }
}
