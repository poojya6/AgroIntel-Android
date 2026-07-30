package com.Simats.agrointel
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import com.google.firebase.auth.FirebaseAuth
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
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
fun SignInScreen(onBackClick: () -> Unit, onSignUpClick: () -> Unit, onSignInSuccess: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var rememberMe by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val auth = FirebaseAuth.getInstance()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Welcome Back",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1A2B3C)
        )

        Text(
            text = "Sign in to continue",
            fontSize = 16.sp,
            color = Color(0xFF667085)
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Email Address
        Text(
            text = "Email Address",
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF1A2B3C),
            modifier = Modifier.padding(bottom = 8.dp)
        )
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("farmer@example.com", color = Color(0xFF98A2B3)) },
            leadingIcon = { Icon(Icons.Outlined.Email, contentDescription = null, tint = Color(0xFF98A2B3)) },
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF10B942),
                unfocusedBorderColor = Color(0xFFEAECF0),
                focusedContainerColor = Color(0xFFF9FAFB),
                unfocusedContainerColor = Color(0xFFF9FAFB)
            )
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
            placeholder = { Text("Enter your password", color = Color(0xFF98A2B3)) },
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

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = rememberMe,
                    onCheckedChange = { rememberMe = it },
                    colors = CheckboxDefaults.colors(checkedColor = Color(0xFF10B942))
                )
                Text(text = "Remember me", fontSize = 14.sp, color = Color(0xFF344054))
            }
            Text(
                text = "Forgot Password?",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF10B942),
                modifier = Modifier.clickable {

                    if (email.isEmpty()) {

                        Toast.makeText(
                            context,
                            "Enter your email first",
                            Toast.LENGTH_SHORT
                        ).show()

                    } else {

                        auth.sendPasswordResetEmail(email)
                            .addOnSuccessListener {

                                Toast.makeText(
                                    context,
                                    "Password reset email sent",
                                    Toast.LENGTH_LONG
                                ).show()

                            }
                            .addOnFailureListener {

                                Toast.makeText(
                                    context,
                                    it.message,
                                    Toast.LENGTH_LONG
                                ).show()

                            }

                    }

                }
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {

                if (email.isEmpty() || password.isEmpty()) {

                    Toast.makeText(
                        context,
                        "Please enter email and password",
                        Toast.LENGTH_SHORT
                    ).show()

                } else {

                    auth.signInWithEmailAndPassword(email, password)

                        .addOnSuccessListener {

                            Toast.makeText(
                                context,
                                "Login Successful",
                                Toast.LENGTH_SHORT
                            ).show()

                            onSignInSuccess()

                        }

                        .addOnFailureListener {

                            Toast.makeText(
                                context,
                                it.message ?: "Login Failed",
                                Toast.LENGTH_LONG
                            ).show()

                        }

                }

            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF10B942)),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = "Sign In",
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
                text = "Don't have an account? ",
                fontSize = 14.sp,
                color = Color(0xFF667085)
            )
            Text(
                text = "Sign Up",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF10B942),
                modifier = Modifier.clickable { onSignUpClick() }
            )
        }
        
        Spacer(modifier = Modifier.height(20.dp))
    }
}
