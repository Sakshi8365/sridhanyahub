package com.agriculture.siridhanya.data.repository

import com.agriculture.siridhanya.data.model.DirectBuyListing
import com.agriculture.siridhanya.data.model.FarmersOrganization
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class DirectBuyRepository {
    private val database = FirebaseDatabase.getInstance()
    private val organizationsRef = database.getReference("organizations")
    private val listingsRef = database.getReference("listings")

    fun getAllOrganizations(): Flow<List<FarmersOrganization>> = callbackFlow {
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val orgs = snapshot.children.mapNotNull { child ->
                    child.getValue(FarmersOrganization::class.java)
                }
                trySend(orgs)
            }

            override fun onCancelled(error: DatabaseError) {
                close(error.toException())
            }
        }

        organizationsRef.addValueEventListener(listener)

        awaitClose {
            organizationsRef.removeEventListener(listener)
        }
    }

    fun getOrganizationsByDistrict(district: String): Flow<List<FarmersOrganization>> = callbackFlow {
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val orgs = snapshot.children
                    .mapNotNull { it.getValue(FarmersOrganization::class.java) }
                    .filter { it.district.equals(district, ignoreCase = true) }
                trySend(orgs)
            }

            override fun onCancelled(error: DatabaseError) {
                close(error.toException())
            }
        }

        organizationsRef.addValueEventListener(listener)

        awaitClose {
            organizationsRef.removeEventListener(listener)
        }
    }

    fun getListings(): Flow<List<DirectBuyListing>> = callbackFlow {
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val listings = snapshot.children.mapNotNull { child ->
                    child.getValue(DirectBuyListing::class.java)
                }
                trySend(listings)
            }

            override fun onCancelled(error: DatabaseError) {
                close(error.toException())
            }
        }

        listingsRef.addValueEventListener(listener)

        awaitClose {
            listingsRef.removeEventListener(listener)
        }
    }

    fun getListingsByMilletType(milletType: String): Flow<List<DirectBuyListing>> = callbackFlow {
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val listings = snapshot.children
                    .mapNotNull { it.getValue(DirectBuyListing::class.java) }
                    .filter { it.milletType.equals(milletType, ignoreCase = true) }
                trySend(listings)
            }

            override fun onCancelled(error: DatabaseError) {
                close(error.toException())
            }
        }

        listingsRef.addValueEventListener(listener)

        awaitClose {
            listingsRef.removeEventListener(listener)
        }
    }

    suspend fun addOrganization(org: FarmersOrganization) {
        val id = organizationsRef.push().key ?: return
        organizationsRef.child(id).setValue(org.copy(id = id))
    }

    suspend fun addListing(listing: DirectBuyListing) {
        val id = listingsRef.push().key ?: return
        listingsRef.child(id).setValue(listing.copy(id = id))
    }
}
