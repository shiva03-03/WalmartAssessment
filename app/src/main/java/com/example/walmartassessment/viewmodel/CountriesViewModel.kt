package com.example.walmartassessment.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.walmartassessment.model.Country
import com.example.walmartassessment.repository.CountryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


sealed class CountriesUIState {
    object Loading: CountriesUIState()
    data class Success(val countries: List<Country>): CountriesUIState()
    data class Error(val message: String): CountriesUIState()
}

class CountriesViewModel(private val repository: CountryRepository): ViewModel() {

    private val _uiState = MutableStateFlow<CountriesUIState>(CountriesUIState.Loading)
    val uiState: StateFlow<CountriesUIState> = _uiState


    init {
        viewModelScope.launch {
            try {
                val countries = repository.fetchCountries()
                _uiState.value = CountriesUIState.Success(countries)
            }
            catch (e: Exception) {
                _uiState.value = CountriesUIState.Error(e.message ?: "Unknown error")
            }
        }
    }

}