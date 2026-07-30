package com.Simats.agrointel

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.*
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    userSession: UserSession,
    onPredictProfitClick: () -> Unit,
    onCropRecommendationClick: () -> Unit,
    onMarketTrendsClick: () -> Unit,
    onInsightsClick: () -> Unit,
    onProfileClick: () -> Unit,
    onSchemesClick: () -> Unit,
    onWeatherClick: () -> Unit,
    onRevenueAnalysisClick: () -> Unit,
    onSettingsClick: () -> Unit,
    onLogoutClick: () -> Unit
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier.width(300.dp),
                drawerShape = RoundedCornerShape(0.dp),
                drawerContainerColor = Color.White
            ) {
                Column(modifier = Modifier.fillMaxSize()) {
                    // Drawer Header
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFF10B942))
                            .clickable { scope.launch { drawerState.close() }; onProfileClick() }
                            .padding(24.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.Top
                        ) {
                            Column {
                                Box(
                                    modifier = Modifier
                                        .size(64.dp)
                                        .background(Color.White.copy(alpha = 0.2f), shape = CircleShape),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(Icons.Default.Person, contentDescription = null, tint = Color.White, modifier = Modifier.size(40.dp))
                                }
                                Spacer(modifier = Modifier.height(16.dp))
                                Text(userSession.fullName, color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                                Text(userSession.email, color = Color.White.copy(alpha = 0.8f), fontSize = 14.sp)
                            }
                            IconButton(onClick = { scope.launch { drawerState.close() } }) {
                                Icon(Icons.Default.Close, contentDescription = "Close", tint = Color.White)
                            }
                        }
                    }

                    // Drawer Items
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .verticalScroll(rememberScrollState())
                            .padding(vertical = 8.dp)
                    ) {
                        DrawerItem(Icons.Default.Home, "Home Dashboard", onClick = { scope.launch { drawerState.close() } })
                        DrawerItem(Icons.Default.TrendingUp, "Profit Prediction", onClick = { scope.launch { drawerState.close() }; onPredictProfitClick() })
                        DrawerItem(Icons.Default.Park, "Crop Recommendations", onClick = { scope.launch { drawerState.close() }; onCropRecommendationClick() })
                        DrawerItem(Icons.Default.Cloud, "Weather Updates", onClick = { scope.launch { drawerState.close() }; onWeatherClick() })
                        DrawerItem(Icons.Default.BarChart, "Market Trends", onClick = { scope.launch { drawerState.close() }; onMarketTrendsClick() })
                        DrawerItem(Icons.Default.AttachMoney, "Revenue Analysis", onClick = { scope.launch { drawerState.close() }; onRevenueAnalysisClick() })
                        DrawerItem(Icons.Default.Gavel, "Government Schemes", onClick = { scope.launch { drawerState.close() }; onSchemesClick() })

                        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp), color = Color(0xFFF2F4F7))

                        DrawerItem(Icons.Default.PersonOutline, "My Profile", onClick = { scope.launch { drawerState.close() }; onProfileClick() })
                        Box(modifier = Modifier.fillMaxWidth()) {
                            DrawerItem(Icons.Default.NotificationsNone, "Notifications", onClick = { })
                            Box(
                                modifier = Modifier
                                    .align(Alignment.CenterEnd)
                                    .padding(end = 16.dp)
                                    .size(20.dp)
                                    .background(Color.Red, shape = CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Text("3", color = Color.White, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                            }
                        }
                        DrawerItem(Icons.Default.Settings, "Settings", onClick = { scope.launch { drawerState.close() }; onSettingsClick() })
                        DrawerItem(Icons.AutoMirrored.Filled.Logout, "Logout", textColor = Color(0xFFEF4444), iconTint = Color(0xFFEF4444), onClick = { scope.launch { drawerState.close() }; onLogoutClick() })
                    }

                    // Drawer Footer
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("Agrointel v1.0.0", color = Color(0xFF98A2B3), fontSize = 12.sp)
                        Text("© 2026 All rights reserved", color = Color(0xFF98A2B3), fontSize = 12.sp)
                    }
                }
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { 
                        Text(
                            "AgroIntel Dashboard", 
                            fontSize = 20.sp, 
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        ) 
                    },
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menu", tint = Color.White)
                        }
                    },
                    actions = {
                        Box(modifier = Modifier.padding(end = 8.dp)) {
                            IconButton(onClick = { }) {
                                Icon(Icons.Default.NotificationsNone, contentDescription = "Notifications", tint = Color.White)
                            }
                            // Notification badge
                            Box(
                                modifier = Modifier
                                    .align(Alignment.TopEnd)
                                    .padding(top = 8.dp, end = 8.dp)
                                    .size(16.dp)
                                    .background(Color.Red, shape = CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Text("3", color = Color.White, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                            }
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF10B942))
                )
            },
            bottomBar = {
                NavigationBar(
                    containerColor = Color.White,
                    tonalElevation = 8.dp
                ) {
                    NavigationBarItem(
                        selected = true,
                        onClick = { },
                        icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                        label = { Text("Home") },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color(0xFF10B942),
                            selectedTextColor = Color(0xFF10B942),
                            indicatorColor = Color(0xFFE0F7E9)
                        )
                    )
                    NavigationBarItem(
                        selected = false,
                        onClick = onInsightsClick,
                        icon = { Icon(Icons.Default.TrendingUp, contentDescription = "Insights") },
                        label = { Text("Insights") }
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
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .background(Color(0xFFF9FAFB))
            ) {
                item {
                    // Header Profile Card
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFF10B942))
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    ) {
                        Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.15f)),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text("Welcome back,", color = Color.White, fontSize = 14.sp)
                            Text(
                                userSession.fullName, 
                                color = Color.White, 
                                fontSize = 24.sp, 
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                "${userSession.state}, ${userSession.district} • ${userSession.totalLand} Acres", 
                                color = Color.White.copy(alpha = 0.9f), 
                                fontSize = 14.sp
                            )
                        }
                    }
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(16.dp))
                    // Status Grid
                    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                        Row(modifier = Modifier.fillMaxWidth()) {
                            StatusCard(
                            modifier = Modifier.weight(1f),
                            title = "Predicted Profit",
                            value = "₹2.4L",
                            icon = Icons.Default.CurrencyRupee,
                            iconColor = Color(0xFF10B942),
                            iconBg = Color(0xFFE0F7E9),
                            onClick = onPredictProfitClick
                        )
                            Spacer(modifier = Modifier.width(16.dp))
                            StatusCard(
                                modifier = Modifier.weight(1f),
                                title = "Active Crops",
                                value = "3 Crops",
                                icon = Icons.Default.Park,
                                iconColor = Color(0xFFD4A017),
                                iconBg = Color(0xFFFFF7E6),
                                onClick = onCropRecommendationClick
                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Row(modifier = Modifier.fillMaxWidth()) {
                            StatusCard(
                                modifier = Modifier.weight(1f),
                                title = "Weather",
                                value = "28°C",
                                icon = Icons.Default.Cloud,
                                iconColor = Color(0xFF2563EB),
                                iconBg = Color(0xFFE0E7FF),
                                onClick = onWeatherClick
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            StatusCard(
                                modifier = Modifier.weight(1f),
                                title = "Market Status",
                                value = "Good",
                                icon = Icons.Default.AutoGraph,
                                iconColor = Color(0xFF9333EA),
                                iconBg = Color(0xFFF3E8FF),
                                onClick = onMarketTrendsClick
                            )
                        }
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(
                        "Quick Actions",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1A2B3C),
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                }

                item {
                    ActionItem(
                        title = "Predict Profit",
                        subtitle = "Calculate crop profitability",
                        iconRes = R.drawable.ic_trending_up,
                        iconBg = Color(0xFFE0F7E9),
                        onClick = onPredictProfitClick
                    )
                }
                item {
                    ActionItem(
                        title = "Crop Recommendations",
                        subtitle = "Get AI-powered suggestions",
                        iconRes = R.drawable.ic_sprout,
                        iconBg = Color(0xFFFFF7E6),
                        onClick = onCropRecommendationClick
                    )
                }
                item {
                    ActionItem(
                        title = "Market Trends",
                        subtitle = "Live price updates",
                        iconRes = R.drawable.ic_market_trends,
                        iconBg = Color(0xFFE0E7FF),
                        onClick = onMarketTrendsClick
                    )
                }
                
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}

@Composable
fun DrawerItem(
    icon: ImageVector,
    text: String,
    textColor: Color = Color(0xFF1A2B3C),
    iconTint: Color = Color(0xFF667085),
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 24.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, contentDescription = null, tint = iconTint, modifier = Modifier.size(24.dp))
        Spacer(modifier = Modifier.width(16.dp))
        Text(text, fontSize = 16.sp, fontWeight = FontWeight.Medium, color = textColor)
    }
}

@Composable
fun StatusCard(
    modifier: Modifier = Modifier,
    title: String,
    value: String,
    icon: ImageVector,
    iconColor: Color,
    iconBg: Color,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = modifier.clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(iconBg, shape = RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(icon, contentDescription = null, tint = iconColor, modifier = Modifier.size(20.dp))
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(title, color = Color(0xFF667085), fontSize = 12.sp)
            Text(value, color = Color(0xFF1A2B3C), fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun ActionItem(
    title: String,
    subtitle: String,
    iconRes: Int,
    iconBg: Color,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(iconBg, shape = RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = iconRes),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = Color.Unspecified
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1A2B3C)
                )
                Text(
                    text = subtitle,
                    fontSize = 13.sp,
                    color = Color(0xFF667085)
                )
            }
        }
    }
}
