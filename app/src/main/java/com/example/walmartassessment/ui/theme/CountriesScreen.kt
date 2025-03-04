package com.example.walmartassessment.ui.theme

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.walmartassessment.model.Country
import com.example.walmartassessment.viewmodel.CountriesUIState

@Composable
fun CountriesScreen(uiState: CountriesUIState) {
    when (uiState) {
        is CountriesUIState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is CountriesUIState.Error -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Error: ${uiState.message}")
            }
        }

        is CountriesUIState.Success -> {
            LazyColumn {
                items(uiState.countries) { country ->
                    CountryItem(country)

                }
            }
        }
    }
}

@Composable
fun CountryItem(country: Country) {
    Column(
        modifier = Modifier.fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(text = "${country.name}, ${country.region},  ${country.code}")

        Spacer(modifier = Modifier.height(4.dp))

        Text(text = country.capital ?: "")

        Divider(modifier = Modifier.padding(vertical = 8.dp))
    }


}
