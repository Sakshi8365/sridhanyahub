package com.agriculture.siridhanya.data.model

import androidx.compose.runtime.Immutable

@Immutable
data class FarmersOrganization(
    val id: String = "",
    val name: String = "", // Farmer Producer Organization (FPO) name
    val location: String = "",
    val district: String = "", // Karnataka district
    val contact: String = "",
    val milletSpecialties: List<String> = emptyList(),
    val imageUrl: String = "",
    val description: String = "",
    val website: String = "",
    val certification: String = "" // Organic, Fair Trade, etc.
)

@Immutable
data class DirectBuyListing(
    val id: String = "",
    val organizationId: String = "",
    val milletType: String = "",
    val pricePerKg: Double = 0.0,
    val availableQuantity: Int = 0, // in kg
    val harvestDate: String = "",
    val quality: String = "", // Grade A, Grade B, etc.
    val minOrderQuantity: Int = 0
)
