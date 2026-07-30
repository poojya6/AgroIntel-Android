package com.Simats.agrointel

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable

    fun CropRecommendationScreen(
onBackClick: () -> Unit,
onViewTopCropsClick: () -> Unit
)
    {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Best Crop Recommendation", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFD4A017))
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color(0xFFF9FAFB))
                .verticalScroll(rememberScrollState())
        ) {
            // Branded Header
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFD4A017), shape = RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp))
                    .padding(bottom = 32.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(
                        modifier = Modifier
                            .size(80.dp)
                            .background(Color.White.copy(alpha = 0.2f), shape = CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.MilitaryTech,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(48.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        "AI-Powered Recommendation",
                        color = Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
            val cropName = "Wheat"
            val profitPerAcre = "69000"
            val marketPrice = "₹2650/Q"
            val yield = "45 Q/acre"

            // Profit Banner
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .offset(y = (-20).dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF10B942)),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "₹$profitPerAcre/acre",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    textAlign = TextAlign.Center
                )
            }

            // Why section
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        "Why $cropName is Best for You",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1A2B3C)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    WhyItem(
                        icon = Icons.Default.TrendingUp,
                        title = "High Profit Margin",
                        desc = "58% profit margin, significantly above average",
                        iconColor = Color(0xFF10B942),
                        bgColor = Color(0xFFE0F7E9)
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    WhyItem(
                        icon = Icons.Outlined.StarBorder,
                        title = "Ideal Soil Match",
                        desc = "Perfect match with your alluvial soil type",
                        iconColor = Color(0xFF2563EB),
                        bgColor = Color(0xFFE0E7FF)
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    WhyItem(
                        icon = Icons.Default.WorkspacePremium,
                        title = "Low Risk",
                        desc = "Stable market demand and government support",
                        iconColor = Color(0xFFD4A017),
                        bgColor = Color(0xFFFFF7E6)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Key Benefits
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                Text(
                    "Key Benefits",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1A2B3C)
                )
                Spacer(modifier = Modifier.height(12.dp))
                
                Row(modifier = Modifier.fillMaxWidth()) {
                    BenefitCard(
                        modifier = Modifier.weight(1f),
                        label = "Market Price",
                        value = marketPrice,
                        color = Color(0xFF10B942),
                        bgColor = Color(0xFFE0F7E9)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    BenefitCard(
                        modifier = Modifier.weight(1f),
                        label = "Yield",
                        value = yield,
                        color = Color(0xFF2563EB),
                        bgColor = Color(0xFFE0E7FF)
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
                Row(modifier = Modifier.fillMaxWidth()) {
                    BenefitCard(
                        modifier = Modifier.weight(1f),
                        label = "Duration",
                        value = "120-150 days",
                        color = Color(0xFFD4A017),
                        bgColor = Color(0xFFFFF7E6)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    BenefitCard(
                        modifier = Modifier.weight(1f),
                        label = "Risk Level",
                        value = "Low",
                        color = Color(0xFF9333EA),
                        bgColor = Color(0xFFF3E8FF)
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Buttons
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Button(
                    onClick = onViewTopCropsClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF10B942)),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("View Top 3 Profitable Crops", fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.height(12.dp))
                OutlinedButton(
                    onClick = { },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFEAECF0)),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Explore Alternatives", color = Color(0xFF1A2B3C), fontWeight = FontWeight.Medium)
                }
            }
            
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
fun WhyItem(icon: ImageVector, title: String, desc: String, iconColor: Color, bgColor: Color) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .size(36.dp)
                .background(bgColor, shape = RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(icon, contentDescription = null, tint = iconColor, modifier = Modifier.size(20.dp))
        }
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(title, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1A2B3C))
            Text(desc, fontSize = 12.sp, color = Color(0xFF667085))
        }
    }
}

@Composable
fun BenefitCard(modifier: Modifier = Modifier, label: String, value: String, color: Color, bgColor: Color) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = bgColor.copy(alpha = 0.5f)),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(label, fontSize = 12.sp, color = Color(0xFF667085))
            Text(value, fontSize = 15.sp, fontWeight = FontWeight.Bold, color = color)
        }
    }
}
