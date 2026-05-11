package com.agriculture.siridhanya.data.model

import androidx.compose.runtime.Immutable

@Immutable
data class Recipe(
    val id: String = "",
    val name: String = "", // Kannada recipe name
    val milletType: String = "", // Navane, Sajje, Baragu
    val description: String = "",
    val ingredients: List<String> = emptyList(),
    val instructions: List<String> = emptyList(),
    val cookingTimeMinutes: Int = 0,
    val servings: Int = 0,
    val difficulty: String = "easy", // easy, medium, hard
    val imageUrl: String = "",
    val isFavorite: Boolean = false,
    val nutritionInfo: String = "",
    val healthBenefits: String = "",
    val tips: String = ""
)

@Immutable
data class RecipeCategory(
    val id: String = "",
    val name: String = "",
    val recipes: List<Recipe> = emptyList()
)

@Immutable
data class SavedRecipe(
    val recipeId: String = "",
    val savedAt: Long = System.currentTimeMillis(),
    val recipe: Recipe = Recipe()
)
