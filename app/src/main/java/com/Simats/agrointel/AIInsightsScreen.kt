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
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class InsightItem(
    val title: String,
    val description: String,
    val icon: ImageVector,
    val iconColor: Color,
    val iconBg: Color,
    val priority: String? = null,
    val borderColor: Color = Color(0xFFEAECF0)
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AIInsightsScreen(
    onBackClick: () -> Unit,
    onHomeClick: () -> Unit,
    onProfileClick: () -> Unit
) {
    val insights = listOf(
        InsightItem(
            "Market Opportunity",
            "Wheat prices expected to rise by 8% in next 2 months",
            Icons.Default.TrendingUp,
            Color(0xFF10B942),
            Color(0xFFE0F7E9),
            "High",
            Color(0xFF10B942)
        ),
        InsightItem(
            "Planting Recommendation",
            "Optimal planting window: Nov 15 - Nov 30 for maximum yield",
            Icons.Default.TrackChanges,
            Color(0xFF2563EB),
            Color(0xFFE0E7FF),
            "High",
            Color(0xFF10B942)
        ),
        InsightItem(
            "Cost Optimization",
            "Switch to organic fertilizer can reduce costs by 15%",
            Icons.Default.Lightbulb,
            Color(0xFFD4A017),
            Color(0xFFFFF7E6),
            borderColor = Color(0xFFD4A017)
        ),
        InsightItem(
            "Weather Alert",
            "Above-average rainfall predicted - adjust irrigation schedule",
            Icons.Default.Psychology,
            Color(0xFF9333EA),
            Color(0xFFF3E8FF),
            borderColor = Color(0xFF2563EB)
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("AI Insights", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF9333EA))
            )
        },
        bottomBar = {
            NavigationBar(containerColor = Color.White) {
                NavigationBarItem(
                    selected = false,
                    onClick = onHomeClick,
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text("Home") }
                )
                NavigationBarItem(
                    selected = true,
                    onClick = { },
                    icon = { Icon(Icons.Default.TrendingUp, contentDescription = "Insights") },
                    label = { Text("Insights") },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color(0xFF10B942),
                        selectedTextColor = Color(0xFF10B942),
                        indicatorColor = Color(0xFFE0F7E9)
                    )
                )
                NavigationBarItem(
                    selected = false,
                    onClick = onProfileClick,
                    icon = { Icon(Icons.Default.Settings, contentDescription = "Profile") },
                    label = { Text("Profile") }
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color(0xFFF9FAFB))
        ) {
            // Header Banner
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF9333EA), shape = RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp))
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
                            Icon(Icons.Default.Psychology, contentDescription = null, tint = Color.White)
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Column {
                            Text("Personalized Insights", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                            Text("Based on your farm data", color = Color.White.copy(alpha = 0.8f), fontSize = 12.sp)
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
                    Row(modifier = Modifier.fillMaxWidth()) {
                        InsightStatCard(modifier = Modifier.weight(1f), label = "Insights Generated", value = "24")
                        Spacer(modifier = Modifier.width(16.dp))
                        InsightStatCard(modifier = Modifier.weight(1f), label = "Accuracy Rate", value = "94%", valueColor = Color(0xFF10B942))
                    }
                }

                item {
                    Text("Latest Insights", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1A2B3C))
                }

                items(insights) { insight ->
                    InsightCard(insight)
                }
                
                item { Spacer(modifier = Modifier.height(16.dp)) }
            }
        }
    }
}

@Composable
fun InsightStatCard(modifier: Modifier, label: String, value: String, valueColor: Color = Color(0xFF1A2B3C)) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(label, fontSize = 11.sp, color = Color(0xFF667085))
            Spacer(modifier = Modifier.height(4.dp))
            Text(value, fontSize = 24.sp, fontWeight = FontWeight.Bold, color = valueColor)
        }
    }
}

@Composable
fun InsightCard(insight: InsightItem) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth().height(IntrinsicSize.Max)) {
            // Left Colored Stripe
            Box(
                modifier = Modifier
                    .width(4.dp)
                    .fillMaxHeight()
                    .background(insight.borderColor)
            )

            Column(modifier = Modifier.padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .background(insight.iconBg, shape = RoundedCornerShape(8.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(insight.icon, contentDescription = null, tint = insight.iconColor, modifier = Modifier.size(20.dp))
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(insight.title, fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1A2B3C))
                            if (insight.priority != null) {
                                Box(
                                    modifier = Modifier
                                        .background(Color(0xFFFEE2E2), shape = RoundedCornerShape(8.dp))
                                        .padding(horizontal = 6.dp, vertical = 2.dp)
                                ) {
                                    Text(insight.priority, fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color(0xFFEF4444))
                                }
                            }
                        }
                        Text(insight.description, fontSize = 12.sp, color = Color(0xFF667085))
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    "View Details →",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF9333EA)
                )
            }
        }
    }
}
