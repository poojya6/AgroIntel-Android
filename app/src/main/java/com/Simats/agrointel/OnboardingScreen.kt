package com.Simats.agrointel

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OnboardingScreen(step: Int, onNextClick: () -> Unit, onBackClick: () -> Unit) {
    val title = when (step) {
        1 -> "Predict Crop Profitability"
        2 -> "Smart Crop Recommendations"
        3 -> "Market Trends & Analytics"
        else -> ""
    }
    
    val subtitle = when (step) {
        1 -> "Get accurate profit predictions based on state, district, crop type, season, and market conditions"
        2 -> "Receive AI-based recommendations for the most profitable crops suited to your land and region"
        3 -> "Track live market prices, historical trends, and make data-driven farming decisions"
        else -> ""
    }
    
    val iconRes = when (step) {
        1 -> R.drawable.ic_bar_chart
        2 -> R.drawable.ic_sprout
        3 -> R.drawable.ic_market_trends
        else -> R.drawable.ic_bar_chart
    }
    
    val iconColor = when (step) {
        1 -> Color(0xFF10B942)
        2 -> Color(0xFFD4A017)
        3 -> Color(0xFF2563EB)
        else -> Color(0xFF10B942)
    }
    
    val iconBgColor = when (step) {
        1 -> Color(0xFFE0F7E9)
        2 -> Color(0xFFFFF7E6)
        3 -> Color(0xFFE0E7FF)
        else -> Color(0xFFE0F7E9)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(80.dp))

        // Icon Container
        Box(
            modifier = Modifier
                .size(200.dp)
                .background(iconBgColor, shape = RoundedCornerShape(32.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = null,
                modifier = Modifier.size(100.dp),
                tint = iconColor
            )
        }

        Spacer(modifier = Modifier.height(48.dp))

        Text(
            text = title,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1A2B3C),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = subtitle,
            fontSize = 15.sp,
            color = Color(0xFF667085),
            textAlign = TextAlign.Center,
            lineHeight = 22.sp,
            modifier = Modifier.padding(horizontal = 12.dp)
        )

        Spacer(modifier = Modifier.weight(1f))

        // Page Indicator
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(3) { i ->
                val isActive = i + 1 == step
                Box(
                    modifier = Modifier
                        .width(if (isActive) 24.dp else 8.dp)
                        .height(8.dp)
                        .background(
                            if (isActive) Color(0xFF10B942) else Color(0xFFD0D5DD),
                            shape = RoundedCornerShape(4.dp)
                        )
                )
            }
        }

        Spacer(modifier = Modifier.height(40.dp))

        // Buttons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(
                onClick = onBackClick,
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE4E7EC)),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "Back",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF1A2B3C)
                )
            }

            Button(
                onClick = onNextClick,
                modifier = Modifier
                    .weight(1.5f) // Wider for "Get Started"
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF10B942)),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = if (step == 3) "Get Started" else "Next",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
            }
        }
    }
}
