package com.agriculture.siridhanya.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agriculture.siridhanya.data.model.Recipe
import com.agriculture.siridhanya.data.model.SavedRecipe
import com.agriculture.siridhanya.data.repository.RecipeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RecipeViewModel : ViewModel() {
    private val repository = RecipeRepository()

    private val _recipes = MutableStateFlow<List<Recipe>>(emptyList())
    val recipes: StateFlow<List<Recipe>> = _recipes.asStateFlow()

    private val _savedRecipes = MutableStateFlow<List<SavedRecipe>>(emptyList())
    val savedRecipes: StateFlow<List<SavedRecipe>> = _savedRecipes.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _selectedMilletType = MutableStateFlow("All")
    val selectedMilletType: StateFlow<String> = _selectedMilletType.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        loadAllRecipes()
    }

    fun loadAllRecipes() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                repository.getAllRecipes().collect { recipeList ->
                    _recipes.value = recipeList
                    _isLoading.value = false
                }
            } catch (e: Exception) {
                _isLoading.value = false
            }
        }
    }

    fun filterByMilletType(milletType: String) {
        _selectedMilletType.value = milletType
        viewModelScope.launch {
            _isLoading.value = true
            try {
                repository.getRecipesByMilletType(milletType).collect { recipeList ->
                    _recipes.value = recipeList
                    _isLoading.value = false
                }
            } catch (e: Exception) {
                _isLoading.value = false
            }
        }
    }

    fun search(query: String) {
        _searchQuery.value = query
        viewModelScope.launch {
            _isLoading.value = true
            try {
                repository.searchRecipes(query).collect { recipeList ->
                    _recipes.value = recipeList
                    _isLoading.value = false
                }
            } catch (e: Exception) {
                _isLoading.value = false
            }
        }
    }

    fun saveRecipe(userId: String, recipe: Recipe) {
        viewModelScope.launch {
            try {
                repository.saveRecipe(userId, recipe)
                loadSavedRecipes(userId)
            } catch (e: Exception) {
                // Handle error
            }
        }
    }

    fun unsaveRecipe(userId: String, recipeId: String) {
        viewModelScope.launch {
            try {
                repository.unsaveRecipe(userId, recipeId)
                loadSavedRecipes(userId)
            } catch (e: Exception) {
                // Handle error
            }
        }
    }

    fun loadSavedRecipes(userId: String) {
        viewModelScope.launch {
            try {
                repository.getSavedRecipes(userId).collect { savedList ->
                    _savedRecipes.value = savedList
                }
            } catch (e: Exception) {
                // Handle error
            }
        }
    }

    fun isRecipeSaved(recipeId: String): Boolean {
        return _savedRecipes.value.any { it.recipeId == recipeId }
    }
}
