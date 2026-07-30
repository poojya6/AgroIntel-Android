package com.Simats.agrointel
import com.google.firebase.auth.FirebaseAuth
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.Simats.agrointel.ui.theme.AgroIntelTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseAuth.getInstance().signOut()
        enableEdgeToEdge()
        setContent {
            var showSplash by remember { mutableStateOf(true) }
            var showWelcome by remember { mutableStateOf(false) }
            var onboardingStep by remember { mutableStateOf(0) }
            var showLoginChoice by remember { mutableStateOf(false) }
            var showCreateAccount by remember { mutableStateOf(false) }
            var showSignIn by remember { mutableStateOf(false) }
            var showProfileSetup by remember { mutableStateOf(false) }
            var showDashboard by remember { mutableStateOf(false) }
            var showProfitPrediction by remember { mutableStateOf(false) }
            var showCropRecommendation by remember { mutableStateOf(false) }
            var showTopCrops by remember { mutableStateOf(false) }
            var showMarketTrends by remember { mutableStateOf(false) }
            var showInsights by remember { mutableStateOf(false) }
            var showProfile by remember { mutableStateOf(false) }
            var showSchemes by remember { mutableStateOf(false) }
            var showWeather by remember { mutableStateOf(false) }
            var showRevenueAnalysis by remember { mutableStateOf(false) }
            var showCostBreakdown by remember { mutableStateOf(false) }
            var showSettings by remember { mutableStateOf(false) }
            var userSession by remember { mutableStateOf(UserSession()) }
            val auth = FirebaseAuth.getInstance()
            var recommendedCrop by remember { mutableStateOf("") }
            var predictedProfitPerAcre by remember { mutableStateOf("") }
            var predictedMarketPrice by remember { mutableStateOf("") }
            var predictedYield by remember { mutableStateOf("") }
            var cropName by remember { mutableStateOf("") }
            var landAreaValue by remember { mutableStateOf(0.0) }

            var totalRevenue by remember { mutableStateOf(0.0) }
            var totalCost by remember { mutableStateOf(0.0) }
            var totalProfit by remember { mutableStateOf(0.0) }
            var totalYield by remember { mutableStateOf(0.0) }

            var cropType by remember { mutableStateOf("") }
            var landArea by remember { mutableStateOf("") }
            var predictionCost by remember { mutableStateOf(0.0) }
            LaunchedEffect(Unit) {

                delay(2000)
                showSplash = false
                showWelcome = true
            }

            AgroIntelTheme {
                when {
                    showSplash -> SplashScreenContent()
                    showWelcome -> WelcomeScreen(
                        onGetStartedClick = {
                            showWelcome = false
                            onboardingStep = 1
                        }
                    )
                    onboardingStep > 0 -> OnboardingScreen(
                        step = onboardingStep,
                        onNextClick = {
                            if (onboardingStep < 3) {
                                onboardingStep++
                            } else {
                                onboardingStep = 0
                                showLoginChoice = true
                            }
                        },
                        onBackClick = {
                            if (onboardingStep > 1) {
                                onboardingStep--
                            } else {
                                onboardingStep = 0
                                showWelcome = true
                            }
                        }
                    )
                    showLoginChoice -> LoginChoiceScreen(
                        onCreateAccountClick = {
                            showLoginChoice = false
                            showCreateAccount = true
                        },
                        onSignInClick = {
                            showLoginChoice = false
                            showSignIn = true
                        }
                    )
                    showSignIn -> SignInScreen(
                        onBackClick = {
                            showSignIn = false
                            showLoginChoice = true
                        },
                        onSignUpClick = {
                            showSignIn = false
                            showCreateAccount = true
                        },
                        onSignInSuccess = {
                            showSignIn = false
                            showDashboard = true
                        }
                    )
                    showCreateAccount -> CreateAccountScreen(
                        onBackClick = {
                            showCreateAccount = false
                            showLoginChoice = true
                        },
                        onSignInClick = {
                            showCreateAccount = false
                            showSignIn = true
                        },
                        onAccountCreated = { name, email, phone ->
                            userSession = userSession.copy(
                                fullName = name,
                                email = email,
                                phoneNumber = phone
                            )
                            showCreateAccount = false
                            showProfileSetup = true
                        }
                    )
                    showProfileSetup -> ProfileSetupScreen(
                        onCompleteClick = { farm, land, st, dist, vill ->
                            userSession = userSession.copy(
                                farmName = farm,
                                totalLand = land,
                                state = st,
                                district = dist,
                                village = vill
                            )
                            showProfileSetup = false
                            println(userSession.fullName)
                            showDashboard = true
                        }
                    )
                    showDashboard -> DashboardScreen(
                        userSession = userSession,
                        onPredictProfitClick = { showDashboard = false; showProfitPrediction = true },
                        onCropRecommendationClick = { showDashboard = false; showCropRecommendation = true },
                        onMarketTrendsClick = { showDashboard = false; showMarketTrends = true },
                        onInsightsClick = { showDashboard = false; showInsights = true },
                        onProfileClick = { showDashboard = false; showProfile = true },
                        onSchemesClick = { showDashboard = false; showSchemes = true },
                        onWeatherClick = { showDashboard = false; showWeather = true },
                        onRevenueAnalysisClick = { showDashboard = false; showRevenueAnalysis = true },
                        onSettingsClick = { showDashboard = false; showSettings = true },
                        onLogoutClick = {
                            showDashboard = false
                            showSplash = true
                        }
                    )
                    showProfitPrediction -> ProfitPredictionScreen(
                        onBackClick = { showProfitPrediction = false; showDashboard = true },
                        onGetRecommendationsClick = { showProfitPrediction = false; showCropRecommendation = true },
                        onRevenueAnalysisClick = { showProfitPrediction = false; showRevenueAnalysis = true },
                        onCostAnalysisClick = { showProfitPrediction = false; showCostBreakdown = true },
                        onYieldEstimationClick = { showProfitPrediction = false; showCropRecommendation = true }
                    )
                    showCropRecommendation -> CropRecommendationScreen(
                        onBackClick = {
                            showCropRecommendation = false
                            showDashboard = true
                        },
                        onViewTopCropsClick = {
                            showCropRecommendation = false
                            showTopCrops = true
                        }
                    )
                    showTopCrops -> TopCropsScreen(
                        onBackClick = { showTopCrops = false; showCropRecommendation = true }
                    )
                    showMarketTrends -> MarketTrendsScreen(
                        onBackClick = { showMarketTrends = false; showDashboard = true }
                    )
                    showInsights -> AIInsightsScreen(
                        onBackClick = { showInsights = false; showDashboard = true },
                        onHomeClick = { showInsights = false; showDashboard = true },
                        onProfileClick = { showInsights = false; showProfile = true }
                    )
                    showProfile -> ProfileScreen(
                        userSession = userSession,
                        onBackClick = { showProfile = false; showDashboard = true },
                        onHomeClick = { showProfile = false; showDashboard = true },
                        onInsightsClick = { showProfile = false; showInsights = true },
                        onEditProfileClick = { showProfile = false; showProfileSetup = true },
                        onFarmDetailsClick = { showProfile = false; showProfileSetup = true },
                        onSettingsClick = { showProfile = false; showSettings = true },
                        onLogoutClick = {

                            FirebaseAuth.getInstance().signOut()

                            showDashboard = false
                            showSplash = true

                        }
                    )
                    showSchemes -> GovernmentSchemesScreen(
                        onBackClick = { showSchemes = false; showDashboard = true }
                    )
                    showWeather -> WeatherScreen(
                        onBackClick = { showWeather = false; showDashboard = true }
                    )
                    showRevenueAnalysis -> RevenueAnalysisScreen(
                        revenue = PredictionRepository.revenue,
                        cost = PredictionRepository.cost,
                        profit = PredictionRepository.profit,
                        yield = PredictionRepository.yield,
                        crop = PredictionRepository.crop,
                        land = landArea.toDoubleOrNull() ?: 0.0,
                        onBackClick = {
                            showRevenueAnalysis = false
                            showProfitPrediction = true
                        }
                    )

                    showCostBreakdown -> CostBreakdownScreen(
                        cost = predictionCost,
                        crop = cropType,
                        land = landArea.toDoubleOrNull() ?: 0.0,
                        onBackClick = {
                            showCostBreakdown = false
                            showProfitPrediction = true
                        }
                    )
                    showSettings -> SettingsScreen(
                        onBackClick = { showSettings = false; showDashboard = true },
                        onEditProfileClick = { showSettings = false; showProfileSetup = true }
                    )
                    else -> Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        Greeting(
                            name = "Android",
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AgroIntelTheme {
        Greeting("Android")
    }
}