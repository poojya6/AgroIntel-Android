package com.Simats.agrointel

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileSetupScreen(onCompleteClick: (String, String, String, String, String) -> Unit) {
    var farmName by remember { mutableStateOf("") }
    var totalLand by remember { mutableStateOf("") }
    var state by remember { mutableStateOf("") }
    var district by remember { mutableStateOf("") }
    var village by remember { mutableStateOf("") }
    
    var stateExpanded by remember { mutableStateOf(false) }
    var districtExpanded by remember { mutableStateOf(false) }
    
    val stateDistrictMap = mapOf(
        "Andhra Pradesh" to listOf("Visakhapatnam", "Vijayawada", "Guntur", "Nellore", "Kurnool"),
        "Punjab" to listOf("Ludhiana", "Amritsar", "Jalandhar", "Patiala", "Bathinda"),
        "Haryana" to listOf("Gurugram", "Faridabad", "Panipat", "Ambala", "Karnal"),
        "Uttar Pradesh" to listOf("Lucknow", "Kanpur", "Agra", "Varanasi", "Meerut"),
        "Tamil Nadu" to listOf("Chennai", "Coimbatore", "Madurai", "Tiruchirappalli", "Salem"),
        "Karnataka" to listOf("Bengaluru", "Mysuru", "Hubballi", "Mangaluru", "Belagavi")
    )
    val states = stateDistrictMap.keys.toList()
    val districts = if (state.isNotEmpty()) stateDistrictMap[state] ?: emptyList() else emptyList()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Complete Your Profile",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1A2B3C)
        )

        Text(
            text = "Tell us about your farm",
            fontSize = 16.sp,
            color = Color(0xFF667085)
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Profile Image Placeholder
        Box(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .size(100.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFE4E7EC), shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.CameraAlt,
                    contentDescription = null,
                    modifier = Modifier.size(40.dp),
                    tint = Color(0xFF98A2B3)
                )
            }
            
            // Smaller camera icon badge
            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .size(32.dp)
                    .background(Color(0xFF10B942), shape = CircleShape)
                    .padding(6.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.CameraAlt,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Farm Name
        ProfileInputField(
            label = "Farm Name",
            value = farmName,
            onValueChange = { farmName = it },
            placeholder = "e.g. Kumar Agriculture Farm"
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Total Land
        ProfileInputField(
            label = "Total Land (Acres)",
            value = totalLand,
            onValueChange = { totalLand = it },
            placeholder = "e.g. 10"
        )

        Spacer(modifier = Modifier.height(16.dp))

        // State Dropdown
        Text(
            text = "State",
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF1A2B3C),
            modifier = Modifier.padding(bottom = 8.dp)
        )
        ExposedDropdownMenuBox(
            expanded = stateExpanded,
            onExpandedChange = { stateExpanded = !stateExpanded }
        ) {
            OutlinedTextField(
                value = state,
                onValueChange = { },
                modifier = Modifier.fillMaxWidth().menuAnchor(),
                placeholder = { Text("Select State", color = Color(0xFF98A2B3)) },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = stateExpanded) },
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
                expanded = stateExpanded,
                onDismissRequest = { stateExpanded = false }
            ) {
                states.forEach { selectionOption ->
                    DropdownMenuItem(
                        text = { Text(selectionOption) },
                        onClick = {
                            state = selectionOption
                            district = "" // Reset district when state changes
                            stateExpanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // District Dropdown
        Text(
            text = "District",
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF1A2B3C),
            modifier = Modifier.padding(bottom = 8.dp)
        )
        ExposedDropdownMenuBox(
            expanded = districtExpanded,
            onExpandedChange = { districtExpanded = !districtExpanded }
        ) {
            OutlinedTextField(
                value = district,
                onValueChange = { },
                modifier = Modifier.fillMaxWidth().menuAnchor(),
                placeholder = { Text("Select District", color = Color(0xFF98A2B3)) },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = districtExpanded) },
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
                expanded = districtExpanded,
                onDismissRequest = { districtExpanded = false }
            ) {
                districts.forEach { selectionOption ->
                    DropdownMenuItem(
                        text = { Text(selectionOption) },
                        onClick = {
                            district = selectionOption
                            districtExpanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Village/Location
        Text(
            text = "Village/Location",
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF1A2B3C),
            modifier = Modifier.padding(bottom = 8.dp)
        )
        OutlinedTextField(
            value = village,
            onValueChange = { village = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Enter village name", color = Color(0xFF98A2B3)) },
            leadingIcon = { Icon(Icons.Outlined.LocationOn, contentDescription = null, tint = Color(0xFF98A2B3)) },
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF10B942),
                unfocusedBorderColor = Color(0xFFEAECF0),
                focusedContainerColor = Color(0xFFF9FAFB),
                unfocusedContainerColor = Color(0xFFF9FAFB)
            )
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { onCompleteClick(farmName, totalLand, state, district, village) },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF10B942)),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = "Complete Setup",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )
        }
        
        Spacer(modifier = Modifier.height(20.dp))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileInputField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String
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
            placeholder = { Text(placeholder, color = Color(0xFF98A2B3)) },
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF10B942),
                unfocusedBorderColor = Color(0xFFEAECF0),
                focusedContainerColor = Color(0xFFF9FAFB),
                unfocusedContainerColor = Color(0xFFF9FAFB)
            )
        )
    }
}
