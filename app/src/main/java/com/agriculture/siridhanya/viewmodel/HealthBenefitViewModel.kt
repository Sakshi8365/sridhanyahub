package com.agriculture.siridhanya.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agriculture.siridhanya.data.model.HealthBenefit
import com.agriculture.siridhanya.data.repository.HealthBenefitRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HealthBenefitViewModel : ViewModel() {
    private val repository = HealthBenefitRepository()

    private val _healthBenefits = MutableStateFlow<List<HealthBenefit>>(emptyList())
    val healthBenefits: StateFlow<List<HealthBenefit>> = _healthBenefits.asStateFlow()

    private val _selectedMilletType = MutableStateFlow("Navane")
    val selectedMilletType: StateFlow<String> = _selectedMilletType.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        loadAllHealthBenefits()
    }

    fun loadAllHealthBenefits() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                repository.getAllHealthBenefits().collect { benefits ->
                    _healthBenefits.value = benefits
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
                repository.getHealthBenefitsByMilletType(milletType).collect { benefits ->
                    _healthBenefits.value = benefits
                    _isLoading.value = false
                }
            } catch (e: Exception) {
                _isLoading.value = false
            }
        }
    }

    val milletTypes = listOf("Navane", "Sajje", "Baragu", "Jowar")
}
