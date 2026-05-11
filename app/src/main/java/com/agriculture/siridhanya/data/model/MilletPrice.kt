package com.agriculture.siridhanya.data.model

import androidx.compose.runtime.Immutable

@Immutable
data class MilletPrice(
    val id: String = "",
    val milletType: String = "", // Navane, Sajje, Baragu
    val city: String = "", // Davangere, Bengaluru, etc.
    val currentPrice: Double = 0.0, // Per kg
    val lowPrice: Double = 0.0, // Last 7 days low
    val highPrice: Double = 0.0, // Last 7 days high
    val priceChange: Double = 0.0, // Percentage change
    val timestamp: Long = System.currentTimeMillis(),
    val trend: String = "stable" // up, down, stable
)

@Immutable
data class PriceHistory(
    val id: String = "",
    val milletType: String = "",
    val city: String = "",
    val prices: List<PricePoint> = emptyList()
)

@Immutable
data class PricePoint(
    val price: Double = 0.0,
    val date: String = ""
)
