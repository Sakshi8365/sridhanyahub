package com.agriculture.siridhanya.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.agriculture.siridhanya.data.model.Recipe
import com.agriculture.siridhanya.viewmodel.RecipeViewModel

@Composable
fun RecipeLabScreen(viewModel: RecipeViewModel) {
    val recipes by viewModel.recipes.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()
    val savedRecipes by viewModel.savedRecipes.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFAFAF8))
            .padding(16.dp)
    ) {
        // Header
        Text(
            "📖 Recipe Lab",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF558B2F),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(
            "Traditional Kannada Millet Recipes",
            fontSize = 14.sp,
            color = Color(0xFF757575),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Search Bar
        SearchBar(
            query = searchQuery,
            onQueryChange = { viewModel.search(it) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp)
        )

        // Millet Type Filter
        RecipeMilletFilterChips { milletType ->
            viewModel.filterByMilletType(milletType)
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Recipe List
        if (isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = Color(0xFF558B2F))
            }
        } else if (recipes.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("No recipes found. Try a different search!", color = Color(0xFF757575))
            }
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(recipes) { recipe ->
                    RecipeCard(
                        recipe = recipe,
                        isSaved = savedRecipes.any { it.recipeId == recipe.id },
                        onSave = { viewModel.saveRecipe("currentUserId", recipe) },
                        onUnsave = { viewModel.unsaveRecipe("currentUserId", recipe.id) }
                    )
                }
            }
        }
    }
}

@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = query,
        onValueChange = onQueryChange,
        modifier = modifier
            .height(48.dp)
            .fillMaxWidth(),
        placeholder = { Text("Search recipes...", fontSize = 12.sp) },
        leadingIcon = {
            Icon(
                Icons.Filled.Search,
                contentDescription = "Search",
                tint = Color(0xFF558B2F)
            )
        },
        shape = RoundedCornerShape(12.dp),
        singleLine = true,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color(0xFF558B2F),
            unfocusedBorderColor = Color(0xFFD7CCC8)
        )
    )
}

@Composable
fun RecipeMilletFilterChips(onMilletSelected: (String) -> Unit) {
    val millets = listOf("All", "Navane", "Sajje", "Baragu", "Jowar")
    
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(millets.size) { index ->
            val millet = millets[index]
            FilterChip(
                selected = false,
                onClick = { onMilletSelected(millet) },
                label = { Text(millet, fontSize = 12.sp) },
                colors = FilterChipDefaults.filterChipColors(
                    containerColor = Color(0xFFD7CCC8)
                )
            )
        }
    }
}

@Composable
fun RecipeCard(
    recipe: Recipe,
    isSaved: Boolean,
    onSave: () -> Unit,
    onUnsave: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Header with recipe name and save button
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        recipe.name,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF558B2F)
                    )
                    Text(
                        recipe.milletType,
                        fontSize = 12.sp,
                        color = Color(0xFF757575)
                    )
                }

                IconButton(
                    onClick = { if (isSaved) onUnsave() else onSave() }
                ) {
                    Icon(
                        imageVector = if (isSaved) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                        contentDescription = "Save Recipe",
                        tint = if (isSaved) Color(0xFFF44336) else Color(0xFF757575),
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            // Description
            Text(
                recipe.description,
                fontSize = 12.sp,
                color = Color(0xFF757575),
                modifier = Modifier.padding(bottom = 12.dp),
                maxLines = 2
            )

            // Quick info chips
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                InfoChip("⏱️ ${recipe.cookingTimeMinutes} min")
                InfoChip("👥 ${recipe.servings}")
                InfoChip("📊 ${recipe.difficulty}")
            }

            // Ingredients preview
            Text(
                "Ingredients (${recipe.ingredients.size})",
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF558B2F),
                modifier = Modifier.padding(bottom = 8.dp)
            )

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(minOf(3, recipe.ingredients.size)) { index ->
                    Text(
                        recipe.ingredients[index],
                        fontSize = 10.sp,
                        color = Color(0xFF757575),
                        modifier = Modifier
                            .background(Color(0xFFFAFAF8), RoundedCornerShape(4.dp))
                            .padding(4.dp)
                    )
                }
                if (recipe.ingredients.size > 3) {
                    item {
                        Text(
                            "+${recipe.ingredients.size - 3}",
                            fontSize = 10.sp,
                            color = Color(0xFF558B2F),
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .background(Color(0xFFE8F5E9), RoundedCornerShape(4.dp))
                                .padding(4.dp)
                        )
                    }
                }
            }

            // View Details Button
            Button(
                onClick = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF558B2F)
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("View Recipe", color = Color.White, fontSize = 12.sp)
            }
        }
    }
}

@Composable
fun InfoChip(text: String) {
    Text(
        text,
        fontSize = 10.sp,
        color = Color(0xFF757575),
        modifier = Modifier
            .background(Color(0xFFE8F5E9), RoundedCornerShape(6.dp))
            .padding(horizontal = 8.dp, vertical = 4.dp)
    )
}
