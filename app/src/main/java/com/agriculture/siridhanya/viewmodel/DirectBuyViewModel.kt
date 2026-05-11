package com.agriculture.siridhanya.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agriculture.siridhanya.data.model.DirectBuyListing
import com.agriculture.siridhanya.data.model.FarmersOrganization
import com.agriculture.siridhanya.data.repository.DirectBuyRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DirectBuyViewModel : ViewModel() {
    private val repository = DirectBuyRepository()

    private val _organizations = MutableStateFlow<List<FarmersOrganization>>(emptyList())
    val organizations: StateFlow<List<FarmersOrganization>> = _organizations.asStateFlow()

    private val _listings = MutableStateFlow<List<DirectBuyListing>>(emptyList())
    val listings: StateFlow<List<DirectBuyListing>> = _listings.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        loadOrganizations()
        loadListings()
    }

    fun loadOrganizations() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                repository.getAllOrganizations().collect { orgs ->
                    _organizations.value = orgs
                    _isLoading.value = false
                }
            } catch (e: Exception) {
                _isLoading.value = false
            }
        }
    }

    fun loadListings() {
        viewModelScope.launch {
            try {
                repository.getListings().collect { list ->
                    _listings.value = list
                }
            } catch (e: Exception) {
                // Handle error
            }
        }
    }

    fun filterByMilletType(milletType: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                repository.getListingsByMilletType(milletType).collect { list ->
                    _listings.value = list
                    _isLoading.value = false
                }
            } catch (e: Exception) {
                _isLoading.value = false
            }
        }
    }

    fun filterByDistrict(district: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                repository.getOrganizationsByDistrict(district).collect { orgs ->
                    _organizations.value = orgs
                    _isLoading.value = false
                }
            } catch (e: Exception) {
                _isLoading.value = false
            }
        }
    }

    val districts = listOf(
        "Davangere", "Bengaluru", "Mysore", "Hubballi",
        "Belgaum", "Tumkur", "Chitradurga", "Madhugiri"
    )

    val milletTypes = listOf("Navane", "Sajje", "Baragu", "Jowar")
}
