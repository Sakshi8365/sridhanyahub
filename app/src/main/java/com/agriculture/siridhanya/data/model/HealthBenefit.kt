package com.agriculture.siridhanya.data.model

import androidx.compose.runtime.Immutable

@Immutable
data class HealthBenefit(
    val id: String = "",
    val milletType: String = "", // Navane, Sajje, Baragu
    val title: String = "", // e.g., "Why Navane is good for Diabetics"
    val description: String = "",
    val benefits: List<String> = emptyList(),
    val scientificFacts: List<String> = emptyList(),
    val imageUrl: String = "",
    val readingTimeMinutes: Int = 0
)

@Immutable
data class NutritionFacts(
    val milletType: String = "",
    val protein: Double = 0.0,
    val carbohydrates: Double = 0.0,
    val fat: Double = 0.0,
    val fiber: Double = 0.0,
    val calories: Int = 0,
    val minerals: List<String> = emptyList(),
    val vitamins: List<String> = emptyList()
)
