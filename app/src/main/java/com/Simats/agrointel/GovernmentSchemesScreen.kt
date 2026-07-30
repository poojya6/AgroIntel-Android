package com.Simats.agrointel

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.MilitaryTech
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class SchemeInfo(
    val title: String,
    val subtitle: String,
    val tag: String,
    val benefit: String,
    val description: String,
    val eligibility: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GovernmentSchemesScreen(onBackClick: () -> Unit) {
    val schemes = listOf(
        SchemeInfo(
            "PM-KISAN", "Pradhan Mantri Kisan Samman Nidhi", "Income Support",
            "₹6,000/year", "Direct income support to all farmers", "All landholding farmers"
        ),
        SchemeInfo(
            "Crop Insurance", "Pradhan Mantri Fasal Bima Yojana", "Risk Management",
            "Up to 90% coverage", "Comprehensive crop insurance against natural calamities", "All farmers with insurable crops"
        ),
        SchemeInfo(
            "Soil Health Card", "National Mission for Sustainable Agriculture", "Soil Health",
            "Free soil testing", "Provides soil nutrient analysis and recommendations", "All farmers"
        ),
        SchemeInfo(
            "MSP", "Minimum Support Price", "Price Support",
            "Guaranteed price", "Minimum price guarantee for 23 crops", "Farmers selling to govt procurement"
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Government Schemes", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF5D3FD3)) // Purple color from screenshot
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color(0xFFF9FAFB))
        ) {
            // Purple Header Banner
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF5D3FD3), shape = RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp))
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
                            Icon(Icons.Default.MilitaryTech, contentDescription = null, tint = Color.White)
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Column {
                            Text("Available Schemes", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                            Text("5 schemes applicable for you", color = Color.White.copy(alpha = 0.8f), fontSize = 12.sp)
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
                    Text("Quick Stats", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1A2B3C))
                    Spacer(modifier = Modifier.height(12.dp))
                    Row(modifier = Modifier.fillMaxWidth()) {
                        StatMiniCard(Modifier.weight(1f), "Active Schemes", "5", Color(0xFF10B942), Color(0xFFE0F7E9))
                        Spacer(modifier = Modifier.width(16.dp))
                        StatMiniCard(Modifier.weight(1f), "Total Benefits", "₹6K+", Color(0xFF2563EB), Color(0xFFE0E7FF))
                    }
                }

                item {
                    Text("Available Schemes", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1A2B3C))
                }

                items(schemes) { scheme ->
                    SchemeCard(scheme)
                }

                item {
                    // How to Apply Section
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF7E6)),
                        shape = RoundedCornerShape(12.dp),
                        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFFFE4BC))
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text("📋", fontSize = 18.sp)
                                Spacer(modifier = Modifier.width(8.dp))
                                Text("How to Apply", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1A2B3C))
                            }
                            Spacer(modifier = Modifier.height(12.dp))
                            val steps = listOf(
                                "1. Visit your nearest CSC or Jan Seva Kendra",
                                "2. Carry Aadhaar card, land records, and bank details",
                                "3. Fill the application form",
                                "4. Submit required documents",
                                "5. Track status online"
                            )
                            steps.forEach { step ->
                                Text(
                                    text = step,
                                    fontSize = 13.sp,
                                    color = Color(0xFFD4A017),
                                    modifier = Modifier.padding(vertical = 2.dp)
                                )
                            }
                        }
                    }
                }
                
                item { Spacer(modifier = Modifier.height(16.dp)) }
            }
        }
    }
}

@Composable
fun StatMiniCard(modifier: Modifier, label: String, value: String, color: Color, bgColor: Color) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = bgColor),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(label, fontSize = 11.sp, color = Color(0xFF667085))
            Spacer(modifier = Modifier.height(4.dp))
            Text(value, fontSize = 22.sp, fontWeight = FontWeight.Bold, color = color)
        }
    }
}

@Composable
fun SchemeCard(scheme: SchemeInfo) {
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
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(scheme.title, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1A2B3C))
                    Spacer(modifier = Modifier.width(8.dp))
                    Box(
                        modifier = Modifier
                            .background(Color(0xFFE0F7E9), shape = RoundedCornerShape(12.dp))
                            .padding(horizontal = 8.dp, vertical = 2.dp)
                    ) {
                        Text("Active", color = Color(0xFF10B942), fontSize = 10.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
            Text(scheme.subtitle, fontSize = 12.sp, color = Color(0xFF667085))
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Box(
                modifier = Modifier
                    .background(Color(0xFFF3E8FF), shape = RoundedCornerShape(8.dp))
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Text(scheme.tag, color = Color(0xFF9333EA), fontSize = 11.sp, fontWeight = FontWeight.Medium)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFE0F7E9).copy(alpha = 0.5f)),
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Text("Benefit", fontSize = 11.sp, color = Color(0xFF667085))
                    Text(scheme.benefit, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color(0xFF10B942))
                }
            }

            Spacer(modifier = Modifier.height(12.dp))
            Text(scheme.description, fontSize = 13.sp, color = Color(0xFF1A2B3C))
            
            Spacer(modifier = Modifier.height(12.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.CheckCircle, contentDescription = null, tint = Color(0xFF10B942), modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text("Eligibility", fontSize = 11.sp, color = Color(0xFF667085))
                    Text(scheme.eligibility, fontSize = 12.sp, color = Color(0xFF1A2B3C))
                }
            }
        }
    }
}
