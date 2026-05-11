package com.agriculture.siridhanya.data.repository

import com.agriculture.siridhanya.data.model.Recipe
import com.agriculture.siridhanya.data.model.SavedRecipe
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class RecipeRepository {
    private val database = FirebaseDatabase.getInstance()
    private val recipesRef = database.getReference("recipes")
    private val savedRecipesRef = database.getReference("savedRecipes")

    fun getAllRecipes(): Flow<List<Recipe>> = callbackFlow {
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val recipes = snapshot.children.mapNotNull { child ->
                    child.getValue(Recipe::class.java)
                }
                trySend(recipes)
            }

            override fun onCancelled(error: DatabaseError) {
                close(error.toException())
            }
        }

        recipesRef.addValueEventListener(listener)

        awaitClose {
            recipesRef.removeEventListener(listener)
        }
    }

    fun getRecipesByMilletType(milletType: String): Flow<List<Recipe>> = callbackFlow {
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val recipes = snapshot.children
                    .mapNotNull { it.getValue(Recipe::class.java) }
                    .filter { it.milletType.equals(milletType, ignoreCase = true) }
                trySend(recipes)
            }

            override fun onCancelled(error: DatabaseError) {
                close(error.toException())
            }
        }

        recipesRef.addValueEventListener(listener)

        awaitClose {
            recipesRef.removeEventListener(listener)
        }
    }

    fun searchRecipes(query: String): Flow<List<Recipe>> = callbackFlow {
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val recipes = snapshot.children
                    .mapNotNull { it.getValue(Recipe::class.java) }
                    .filter { recipe ->
                        recipe.name.contains(query, ignoreCase = true) ||
                                recipe.milletType.contains(query, ignoreCase = true) ||
                                recipe.description.contains(query, ignoreCase = true)
                    }
                trySend(recipes)
            }

            override fun onCancelled(error: DatabaseError) {
                close(error.toException())
            }
        }

        recipesRef.addValueEventListener(listener)

        awaitClose {
            recipesRef.removeEventListener(listener)
        }
    }

    fun getSavedRecipes(userId: String): Flow<List<SavedRecipe>> = callbackFlow {
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val savedRecipes = snapshot.children
                    .mapNotNull { it.getValue(SavedRecipe::class.java) }
                trySend(savedRecipes)
            }

            override fun onCancelled(error: DatabaseError) {
                close(error.toException())
            }
        }

        savedRecipesRef.child(userId).addValueEventListener(listener)

        awaitClose {
            savedRecipesRef.child(userId).removeEventListener(listener)
        }
    }

    suspend fun saveRecipe(userId: String, recipe: Recipe) {
        val id = savedRecipesRef.child(userId).push().key ?: return
        val savedRecipe = SavedRecipe(
            recipeId = recipe.id,
            recipe = recipe
        )
        savedRecipesRef.child(userId).child(id).setValue(savedRecipe)
    }

    suspend fun unsaveRecipe(userId: String, recipeId: String) {
        savedRecipesRef.child(userId).orderByChild("recipeId").equalTo(recipeId)
            .get().addOnSuccessListener { snapshot ->
                for (child in snapshot.children) {
                    child.ref.removeValue()
                }
            }
    }

    suspend fun addRecipe(recipe: Recipe) {
        val id = recipesRef.push().key ?: return
        recipesRef.child(id).setValue(recipe.copy(id = id))
    }
}
