package com.Simats.agrointel

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.NavigateNext
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
fun ProfitPredictionScreen(
    onBackClick: () -> Unit, 
    onGetRecommendationsClick: () -> Unit,
    onRevenueAnalysisClick: () -> Unit,
    onCostAnalysisClick: () -> Unit,
    onYieldEstimationClick: () -> Unit
) {
    var cropType by remember { mutableStateOf("") }
    var landArea by remember { mutableStateOf("") }
    var season by remember { mutableStateOf("") }
    var expectedYield by remember { mutableStateOf("") }
    var cultivationCost by remember { mutableStateOf("") }
    var marketPrice by remember { mutableStateOf("") }
    
    var showResult by remember { mutableStateOf(false) }
    var predictionRevenue by remember { mutableStateOf(0.0) }
    var predictionCost by remember { mutableStateOf(0.0) }
    var predictionProfit by remember { mutableStateOf(0.0) }
    var predictionYield by remember { mutableStateOf(0.0) }
    var recommendedCrop by remember { mutableStateOf("") }
    var profitPerAcre by remember { mutableStateOf(0) }

    if (showResult) {
        PredictionResultContent(
            revenue = predictionRevenue,
            cost = predictionCost,
            profit = predictionProfit,
            yield = predictionYield,
            onBackClick = { showResult = false },
            onGetRecommendationsClick = onGetRecommendationsClick,
            onRevenueAnalysisClick = onRevenueAnalysisClick,
            onCostAnalysisClick = onCostAnalysisClick,
            onYieldEstimationClick = onYieldEstimationClick
        )
    } else {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Profit Prediction", fontSize = 20.sp, fontWeight = FontWeight.Bold) },
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
                    .background(Color.White)
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp)
            ) {
                // Header Banner
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFE0F7E9)),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .background(Color.White, shape = RoundedCornerShape(12.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Calculate,
                                contentDescription = null,
                                tint = Color(0xFF10B942),
                                modifier = Modifier.size(32.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Column {
                            Text(
                                "Calculate Crop Profitability",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF1A2B3C)
                            )
                            Text(
                                "Enter your crop details for AI-powered predictions",
                                fontSize = 12.sp,
                                color = Color(0xFF667085)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Form Fields
                PredictionDropdownField(
                    label = "Crop Type",
                    selectedValue = cropType,
                    onValueChange = { cropType = it },
                    options = listOf("Wheat", "Rice", "Maize", "Cotton", "Mustard")
                )

                Spacer(modifier = Modifier.height(16.dp))

                PredictionInputField(
                    label = "Land Area (Acres)",
                    value = landArea,
                    onValueChange = { landArea = it }
                )

                Spacer(modifier = Modifier.height(16.dp))

                PredictionDropdownField(
                    label = "Season",
                    selectedValue = season,
                    onValueChange = { season = it },
                    options = listOf("Rabi", "Kharif", "Zaid")
                )

                Spacer(modifier = Modifier.height(16.dp))

                PredictionInputField(
                    label = "Expected Yield (Quintal/Acre)",
                    value = expectedYield,
                    onValueChange = { expectedYield = it }
                )

                Spacer(modifier = Modifier.height(16.dp))

                PredictionInputField(
                    label = "Estimated Cultivation Cost (₹/Acre)",
                    value = cultivationCost,
                    onValueChange = { cultivationCost = it }
                )

                Spacer(modifier = Modifier.height(16.dp))

                PredictionInputField(
                    label = "Market Price (₹/Quintal)",
                    value = marketPrice,
                    onValueChange = { marketPrice = it }
                )

                Spacer(modifier = Modifier.height(32.dp))

                Button(
                    onClick = {

                        val land = landArea.toDoubleOrNull() ?: 0.0
                        val yield = expectedYield.toDoubleOrNull() ?: 0.0
                        val cost = cultivationCost.toDoubleOrNull() ?: 0.0
                        val price = marketPrice.toDoubleOrNull() ?: 0.0

                        val totalYield = land * yield
                        val totalRevenue = totalYield * price
                        val totalCost = land * cost
                        val totalProfit = totalRevenue - totalCost
                        val state = "Punjab"   // Later replace with userSession.state

                        recommendedCrop = when {
                            state == "Punjab" && season == "Rabi" -> "Wheat"
                            state == "Punjab" && season == "Kharif" -> "Rice"

                            state == "Tamil Nadu" && season == "Rabi" -> "Groundnut"
                            state == "Tamil Nadu" && season == "Kharif" -> "Cotton"

                            state == "Andhra Pradesh" && season == "Rabi" -> "Chilli"
                            state == "Andhra Pradesh" && season == "Kharif" -> "Groundnut"

                            state == "Karnataka" && season == "Rabi" -> "Ragi"
                            state == "Karnataka" && season == "Kharif" -> "Sugarcane"

                            else -> "Maize"
                        }

                        profitPerAcre =
                            if (land > 0)
                                (totalProfit / land).toInt()
                            else
                                0
                        predictionRevenue = totalRevenue
                        predictionCost = totalCost
                        predictionProfit = totalProfit
                        predictionYield = totalYield

                        PredictionRepository.savePrediction(
                            crop = cropType,
                            land = landArea,
                            season = season,
                            revenue = totalRevenue,
                            cost = totalCost,
                            profit = totalProfit,
                            yield = totalYield
                        )

                        showResult = true

                    },

                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF10B942)),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = "Calculate Profit",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )
                }
                
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PredictionResultContent(
    revenue: Double,
    cost: Double,
    profit: Double,
    yield: Double,
    onBackClick: () -> Unit,
    onGetRecommendationsClick: () -> Unit,
    onRevenueAnalysisClick: () -> Unit,
    onCostAnalysisClick: () -> Unit,
    onYieldEstimationClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Text(
                        "Profit Prediction Result", 
                        fontSize = 18.sp, 
                        fontWeight = FontWeight.Bold, 
                        color = Color.White,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    ) 
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF10B942))
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
            // Summary Header
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF10B942), shape = RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp))
                    .padding(bottom = 32.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("Total Predicted Profit", color = Color.White.copy(alpha = 0.9f), fontSize = 14.sp)
                    Text(
                        text = "₹${profit.toInt()}",
                        color = Color.White,
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Predicted using your input",
                        color = Color.White.copy(alpha = 0.8f),
                        fontSize = 12.sp
                    )
                }
            }

            // Stats Grid
            Column(modifier = Modifier.padding(16.dp)) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    PredictionStatCard(
                        Modifier.weight(1f),
                        "Total Revenue",
                        "₹${revenue.toInt()}",
                        Icons.Default.AttachMoney,
                        Color(0xFF2563EB),
                        Color(0xFFE0E7FF)
                    )

                    PredictionStatCard(
                        Modifier.weight(1f),
                        "Total Cost",
                        "₹${cost.toInt()}",
                        Icons.Default.CurrencyRupee,
                        Color(0xFFEF4444),
                        Color(0xFFFEE2E2)
                    )

                    val margin =
                        if (revenue > 0)
                            ((profit / revenue) * 100).toInt()
                        else
                            0

                    PredictionStatCard(
                        Modifier.weight(1f),
                        "Profit Margin",
                        "$margin%",
                        Icons.Default.TrendingUp,
                        Color(0xFF10B942),
                        Color(0xFFE0F7E9)
                    )

                    PredictionStatCard(
                        Modifier.weight(1f),
                        "Total Yield",
                        "${yield.toInt()} Qt",
                        Icons.Default.WorkspacePremium,
                        Color(0xFFD4A017),
                        Color(0xFFFFF7E6)
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // High Profit Banner
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFF0FDF4)),
                    shape = RoundedCornerShape(12.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFDCFCE7))
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Check, contentDescription = null, tint = Color(0xFF10B942), modifier = Modifier.size(18.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Highly Profitable", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color(0xFF10B942))
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            "Based on current market conditions and expected yield, this crop shows excellent profit potential.",
                            fontSize = 13.sp,
                            color = Color(0xFF344054),
                            lineHeight = 18.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))
                Text("Detailed Analysis", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1A2B3C))
                Spacer(modifier = Modifier.height(12.dp))

                AnalysisRow("Revenue Breakdown", onClick = onRevenueAnalysisClick)
                AnalysisRow("Cost Analysis", onClick = onCostAnalysisClick)
                AnalysisRow("Yield Estimation", onClick = onYieldEstimationClick)

                Spacer(modifier = Modifier.height(32.dp))

                Button(
                    onClick = onGetRecommendationsClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF10B942)),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Get Crop Recommendations", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                }
                
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}

@Composable
fun PredictionStatCard(modifier: Modifier, label: String, value: String, icon: ImageVector, iconColor: Color, iconBg: Color) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .background(iconBg, shape = RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(icon, contentDescription = null, tint = iconColor, modifier = Modifier.size(18.dp))
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(label, fontSize = 11.sp, color = Color(0xFF667085))
            Text(value, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1A2B3C))
        }
    }
}

@Composable
fun AnalysisRow(title: String, onClick: () -> Unit = {}) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(12.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFEAECF0))
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(title, modifier = Modifier.weight(1f), fontSize = 14.sp, fontWeight = FontWeight.Medium, color = Color(0xFF1A2B3C))
            Icon(Icons.AutoMirrored.Filled.NavigateNext, contentDescription = null, tint = Color(0xFF98A2B3))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PredictionDropdownField(
    label: String,
    selectedValue: String,
    onValueChange: (String) -> Unit,
    options: List<String>
) {
    var expanded by remember { mutableStateOf(false) }

    Column {
        Text(
            text = label,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF1A2B3C),
            modifier = Modifier.padding(bottom = 8.dp)
        )
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            OutlinedTextField(
                value = selectedValue,
                onValueChange = { },
                modifier = Modifier.fillMaxWidth().menuAnchor(ExposedDropdownMenuAnchorType.PrimaryNotEditable),
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                shape = RoundedCornerShape(12.dp),
                readOnly = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF10B942),
                    unfocusedBorderColor = Color(0xFFEAECF0),
                    focusedContainerColor = Color(0xFFF9FAFB),
                    unfocusedContainerColor = Color(0xFFF9FAFB)
                )
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                options.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option) },
                        onClick = {
                            onValueChange(option)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun PredictionInputField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    isDropdown: Boolean = false
) {
    Column {
        Text(
            text = label,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF1A2B3C),
            modifier = Modifier.padding(bottom = 8.dp)
        )
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                if (isDropdown) {
                    Icon(Icons.Default.ArrowDropDown, contentDescription = null)
                }
            },
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF10B942),
                unfocusedBorderColor = Color(0xFFEAECF0),
                focusedContainerColor = Color(0xFFF9FAFB),
                unfocusedContainerColor = Color(0xFFF9FAFB)
            ),
            readOnly = isDropdown
        )
    }
}
