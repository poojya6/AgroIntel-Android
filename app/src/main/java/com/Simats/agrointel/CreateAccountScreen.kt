package com.Simats.agrointel
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateAccountScreen(
    onBackClick: () -> Unit, 
    onSignInClick: () -> Unit,
    onAccountCreated: (String, String, String) -> Unit
) {
    var fullName by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var agreedToTerms by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val auth = FirebaseAuth.getInstance()
    val db = FirebaseFirestore.getInstance()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Create Account",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1A2B3C)
        )

        Text(
            text = "Join AgroIntel today",
            fontSize = 16.sp,
            color = Color(0xFF667085)
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Full Name
        InputField(
            label = "Full Name",
            value = fullName,
            onValueChange = { fullName = it },
            placeholder = "Enter your full name",
            leadingIcon = Icons.Outlined.Person
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Phone Number
        InputField(
            label = "Phone Number",
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            placeholder = "+91 00000 00000",
            leadingIcon = Icons.Outlined.Phone
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Email Address
        InputField(
            label = "Email Address",
            value = email,
            onValueChange = { email = it },
            placeholder = "you@example.com",
            leadingIcon = Icons.Outlined.Email
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Password
        Text(
            text = "Password",
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF1A2B3C),
            modifier = Modifier.padding(bottom = 8.dp)
        )
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Create a strong password", color = Color(0xFF98A2B3)) },
            leadingIcon = { Icon(Icons.Outlined.Lock, contentDescription = null, tint = Color(0xFF98A2B3)) },
            trailingIcon = {
                val icon = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(icon, contentDescription = null, tint = Color(0xFF98A2B3))
                }
            },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF10B942),
                unfocusedBorderColor = Color(0xFFEAECF0),
                focusedContainerColor = Color(0xFFF9FAFB),
                unfocusedContainerColor = Color(0xFFF9FAFB)
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Terms Checkbox
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Checkbox(
                checked = agreedToTerms,
                onCheckedChange = { agreedToTerms = it },
                colors = CheckboxDefaults.colors(checkedColor = Color(0xFF10B942))
            )
            Text(
                text = "I agree to the Terms of Service and Privacy Policy",
                fontSize = 13.sp,
                color = Color(0xFF344054)
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {

                if (fullName.isEmpty() ||
                    phoneNumber.isEmpty() ||
                    email.isEmpty() ||
                    password.isEmpty()) {

                    Toast.makeText(
                        context,
                        "Please fill all fields",
                        Toast.LENGTH_SHORT
                    ).show()

                    return@Button
                }

                if (!agreedToTerms) {

                    Toast.makeText(
                        context,
                        "Please accept Terms & Conditions",
                        Toast.LENGTH_SHORT
                    ).show()

                    return@Button
                }

                auth.createUserWithEmailAndPassword(email, password)

                    .addOnSuccessListener {

                        val uid = auth.currentUser?.uid ?: return@addOnSuccessListener

                        val user = hashMapOf(

                            "uid" to uid,
                            "fullName" to fullName,
                            "phoneNumber" to phoneNumber,
                            "email" to email

                        )

                        db.collection("users")

                            .document(uid)

                            .set(user)

                            .addOnSuccessListener {

                                Toast.makeText(
                                    context,
                                    "Account Created Successfully",
                                    Toast.LENGTH_SHORT
                                ).show()

                                onAccountCreated(
                                    fullName,
                                    email,
                                    phoneNumber
                                )

                            }

                            .addOnFailureListener {

                                Toast.makeText(
                                    context,
                                    it.message,
                                    Toast.LENGTH_LONG
                                ).show()

                            }

                    }

                    .addOnFailureListener {

                        Toast.makeText(
                            context,
                            it.message,
                            Toast.LENGTH_LONG
                        ).show()

                    }

            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF10B942)),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = "Create Account",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Already have an account? ",
                fontSize = 14.sp,
                color = Color(0xFF667085)
            )
            Text(
                text = "Sign In",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF10B942),
                modifier = Modifier.clickable { onSignInClick() }
            )
        }
        
        Spacer(modifier = Modifier.height(20.dp))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    leadingIcon: androidx.compose.ui.graphics.vector.ImageVector
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
            leadingIcon = { Icon(leadingIcon, contentDescription = null, tint = Color(0xFF98A2B3)) },
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
