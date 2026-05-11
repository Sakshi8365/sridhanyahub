package com.agriculture.siridhanya.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agriculture.siridhanya.data.model.MilletPrice
import com.agriculture.siridhanya.data.repository.PriceRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PriceViewModel : ViewModel() {
    private val repository = PriceRepository()

    private val _prices = MutableStateFlow<List<MilletPrice>>(emptyList())
    val prices: StateFlow<List<MilletPrice>> = _prices.asStateFlow()

    private val _selectedCity = MutableStateFlow("Bengaluru")
    val selectedCity: StateFlow<String> = _selectedCity.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        loadPrices()
    }

    fun loadPrices() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                repository.getMilletPrices().collect { priceList ->
                    _prices.value = priceList
                    _isLoading.value = false
                }
            } catch (e: Exception) {
                _isLoading.value = false
            }
        }
    }

    fun filterByCity(city: String) {
        _selectedCity.value = city
        viewModelScope.launch {
            _isLoading.value = true
            try {
                repository.getPricesByCity(city).collect { priceList ->
                    _prices.value = priceList
                    _isLoading.value = false
                }
            } catch (e: Exception) {
                _isLoading.value = false
            }
        }
    }

    fun filterByMilletType(milletType: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                repository.getPricesByMilletType(milletType).collect { priceList ->
                    _prices.value = priceList
                    _isLoading.value = false
                }
            } catch (e: Exception) {
                _isLoading.value = false
            }
        }
    }

    fun getTrendIcon(trend: String): String {
        return when (trend) {
            "up" -> "📈"
            "down" -> "📉"
            else -> "➡️"
        }
    }

    fun getTrendColor(trend: String): String {
        return when (trend) {
            "up" -> "#4CAF50" // Green
            "down" -> "#F44336" // Red
            else -> "#FF9800" // Orange
        }
    }
}
