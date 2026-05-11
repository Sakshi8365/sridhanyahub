package com.agriculture.siridhanya.data.model

import androidx.compose.runtime.Immutable

@Immutable
data class User(
    val id: String = "",
    val name: String = "",
    val email: String = "",
    val userType: String = "", // "farmer", "consumer"
    val savedRecipes: List<String> = emptyList(), // Recipe IDs
    val watchedPrices: List<String> = emptyList(), // Millet types or locations
    val createdAt: Long = System.currentTimeMillis()
)
