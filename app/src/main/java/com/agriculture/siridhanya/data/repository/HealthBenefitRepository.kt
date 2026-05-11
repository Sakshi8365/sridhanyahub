package com.agriculture.siridhanya.data.repository

import com.agriculture.siridhanya.data.model.HealthBenefit
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class HealthBenefitRepository {
    private val database = FirebaseDatabase.getInstance()
    private val healthBenefitsRef = database.getReference("healthBenefits")

    fun getAllHealthBenefits(): Flow<List<HealthBenefit>> = callbackFlow {
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val benefits = snapshot.children.mapNotNull { child ->
                    child.getValue(HealthBenefit::class.java)
                }
                trySend(benefits)
            }

            override fun onCancelled(error: DatabaseError) {
                close(error.toException())
            }
        }

        healthBenefitsRef.addValueEventListener(listener)

        awaitClose {
            healthBenefitsRef.removeEventListener(listener)
        }
    }

    fun getHealthBenefitsByMilletType(milletType: String): Flow<List<HealthBenefit>> = callbackFlow {
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val benefits = snapshot.children
                    .mapNotNull { it.getValue(HealthBenefit::class.java) }
                    .filter { it.milletType.equals(milletType, ignoreCase = true) }
                trySend(benefits)
            }

            override fun onCancelled(error: DatabaseError) {
                close(error.toException())
            }
        }

        healthBenefitsRef.addValueEventListener(listener)

        awaitClose {
            healthBenefitsRef.removeEventListener(listener)
        }
    }

    suspend fun addHealthBenefit(benefit: HealthBenefit) {
        val id = healthBenefitsRef.push().key ?: return
        healthBenefitsRef.child(id).setValue(benefit.copy(id = id))
    }
}
