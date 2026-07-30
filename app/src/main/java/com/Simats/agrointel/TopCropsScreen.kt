package com.Simats.agrointel

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class CropProfitInfo(
    val name: String,
    val season: String,
    val profit: String,
    val margin: String,
    val yield: String,
    val price: String,
    val risk: String,
    val riskColor: Color,
    val iconRes: Int,
    val rankColor: Color
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopCropsScreen(onBackClick: () -> Unit) {
    val crops = listOf(
        CropProfitInfo(
            "Wheat", "Rabi Season", "₹69,000", "58%", "45 Q/acre", "₹2,650/Q", "Low", 
            Color(0xFF10B942), R.drawable.ic_wheat, Color(0xFFFBBF24)
        ),
        CropProfitInfo(
            "Potato", "Rabi Season", "₹62,000", "52%", "200 Q/acre", "₹450/Q", "Medium", 
            Color(0xFFD4A017), R.drawable.ic_potato, Color(0xFF94A3B8)
        ),
        CropProfitInfo(
            "Mustard", "Rabi Season", "₹58,000", "48%", "18 Q/acre", "₹5,500/Q", "Low", 
            Color(0xFF10B942), R.drawable.ic_mustard, Color(0xFFF97316)
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Top 3 Profitable Crops", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
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
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                // Profile Context Banner
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF7E6)),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Default.Star, 
                            contentDescription = null, 
                            tint = Color(0xFFD4A017),
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(
                                "Based on Your Farm Profile",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF1A2B3C)
                            )
                            Text(
                                "Ludhiana, Punjab • 4 Acres • Rabi Season",
                                fontSize = 11.sp,
                                color = Color(0xFF667085)
                            )
                        }
                    }
                }
            }

            itemsIndexed(crops) { index, crop ->
                CropRankCard(rank = index + 1, crop = crop)
            }

            item {
                // Farming Tip
                Card(
                    modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFE0E7FF)),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        Icon(
                            Icons.Default.Info, 
                            contentDescription = null, 
                            tint = Color(0xFF2563EB),
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(
                                "Farming Tip: Crop Rotation",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF1A2B3C)
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                "Avoid planting the same crop in the same field consecutive years. Rotate with legumes like pulses to naturally restore nitrogen to your soil.",
                                fontSize = 12.sp,
                                color = Color(0xFF344054),
                                lineHeight = 18.sp
                            )
                        }
                    }
                }
            }
            
            item { Spacer(modifier = Modifier.height(16.dp)) }
        }
    }
}

@Composable
fun CropRankCard(rank: Int, crop: CropProfitInfo) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        border = if (rank == 1) androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF10B942)) else null
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Rank Badge
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .background(crop.rankColor, shape = CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        "#$rank", 
                        color = Color.White, 
                        fontSize = 14.sp, 
                        fontWeight = FontWeight.Bold
                    )
                }
                
                Spacer(modifier = Modifier.width(12.dp))
                
                Image(
                    painter = painterResource(id = crop.iconRes),
                    contentDescription = null,
                    modifier = Modifier.size(32.dp)
                )
                
                Spacer(modifier = Modifier.width(12.dp))
                
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        crop.name,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1A2B3C)
                    )
                    Text(
                        crop.season,
                        fontSize = 12.sp,
                        color = Color(0xFF667085)
                    )
                }
                
                if (rank == 1) {
                    Icon(
                        Icons.Default.Star, 
                        contentDescription = null, 
                        tint = Color(0xFFFBBF24),
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(20.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text("Expected Profit/Acre", fontSize = 13.sp, color = Color(0xFF667085))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            Icons.Default.TrendingUp, 
                            contentDescription = null, 
                            tint = Color(0xFF10B942),
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            "${crop.margin} margin", 
                            fontSize = 14.sp, 
                            color = Color(0xFF10B942),
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
                Text(
                    crop.profit,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF10B942)
                )
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                InfoBadge(modifier = Modifier.weight(1f), label = "Yield", value = crop.yield, color = Color(0xFF2563EB), bgColor = Color(0xFFE0E7FF))
                InfoBadge(modifier = Modifier.weight(1f), label = "Price", value = crop.price, color = Color(0xFF10B942), bgColor = Color(0xFFE0F7E9))
                InfoBadge(modifier = Modifier.weight(1f), label = "Risk", value = crop.risk, color = crop.riskColor, bgColor = if (crop.risk == "Medium") Color(0xFFFFF7E6) else Color(0xFFE0F7E9))
            }
        }
    }
}

@Composable
fun InfoBadge(modifier: Modifier = Modifier, label: String, value: String, color: Color, bgColor: Color) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = bgColor),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 4.dp).fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(label, fontSize = 10.sp, color = Color(0xFF667085))
            Text(value, fontSize = 11.sp, fontWeight = FontWeight.Bold, color = color)
        }
    }
}
