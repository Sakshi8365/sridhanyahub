package com.agriculture.siridhanya.data.repository

import com.agriculture.siridhanya.data.model.MilletPrice
import com.agriculture.siridhanya.data.model.PriceHistory
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlin.math.abs

class PriceRepository {
    private val database = FirebaseDatabase.getInstance()
    private val pricesRef = database.getReference("prices")

    fun getMilletPrices(): Flow<List<MilletPrice>> = callbackFlow {
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val prices = snapshot.children.mapNotNull { child ->
                    child.getValue(MilletPrice::class.java)
                }
                trySend(prices)
            }

            override fun onCancelled(error: DatabaseError) {
                close(error.toException())
            }
        }

        pricesRef.addValueEventListener(listener)

        awaitClose {
            pricesRef.removeEventListener(listener)
        }
    }

    fun getPricesByCity(city: String): Flow<List<MilletPrice>> = callbackFlow {
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val prices = snapshot.children
                    .mapNotNull { it.getValue(MilletPrice::class.java) }
                    .filter { it.city.equals(city, ignoreCase = true) }
                trySend(prices)
            }

            override fun onCancelled(error: DatabaseError) {
                close(error.toException())
            }
        }

        pricesRef.addValueEventListener(listener)

        awaitClose {
            pricesRef.removeEventListener(listener)
        }
    }

    fun getPricesByMilletType(milletType: String): Flow<List<MilletPrice>> = callbackFlow {
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val prices = snapshot.children
                    .mapNotNull { it.getValue(MilletPrice::class.java) }
                    .filter { it.milletType.equals(milletType, ignoreCase = true) }
                trySend(prices)
            }

            override fun onCancelled(error: DatabaseError) {
                close(error.toException())
            }
        }

        pricesRef.addValueEventListener(listener)

        awaitClose {
            pricesRef.removeEventListener(listener)
        }
    }

    suspend fun addMilletPrice(price: MilletPrice) {
        val id = pricesRef.push().key ?: return
        pricesRef.child(id).setValue(price.copy(id = id))
    }

    fun updateMilletPrice(price: MilletPrice) {
        pricesRef.child(price.id).setValue(price)
    }

    fun getTrendForPrice(lowPrice: Double, highPrice: Double, currentPrice: Double): String {
        return when {
            currentPrice >= highPrice * 0.95 -> "up"
            currentPrice <= lowPrice * 1.05 -> "down"
            else -> "stable"
        }
    }

    fun calculatePriceChange(oldPrice: Double, newPrice: Double): Double {
        return if (oldPrice == 0.0) 0.0 else ((newPrice - oldPrice) / oldPrice) * 100
    }
}
