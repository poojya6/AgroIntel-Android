package com.Simats.agrointel

import com.google.firebase.firestore.FirebaseFirestore

object PredictionRepository {

    private val db = FirebaseFirestore.getInstance()

    // Store latest prediction locally
    var crop: String = ""
    var land: String = ""
    var season: String = ""

    var revenue: Double = 0.0
    var cost: Double = 0.0
    var profit: Double = 0.0
    var yield: Double = 0.0

    fun savePrediction(
        crop: String,
        land: String,
        season: String,
        revenue: Double,
        cost: Double,
        profit: Double,
        yield: Double
    ) {

        // Save locally
        this.crop = crop
        this.land = land
        this.season = season
        this.revenue = revenue
        this.cost = cost
        this.profit = profit
        this.yield = yield

        // Save to Firestore
        val prediction = hashMapOf(
            "crop" to crop,
            "land" to land,
            "season" to season,
            "revenue" to revenue,
            "cost" to cost,
            "profit" to profit,
            "yield" to yield,
            "timestamp" to System.currentTimeMillis()
        )

        db.collection("profit_predictions")
            .add(prediction)
    }
}