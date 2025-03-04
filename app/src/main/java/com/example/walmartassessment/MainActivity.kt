package com.example.walmartassessment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.walmartassessment.network.CountriesApi
import com.example.walmartassessment.repository.CountryRepository
import com.example.walmartassessment.ui.theme.CountriesScreen
import com.example.walmartassessment.ui.theme.WalmartAssessmentTheme
import com.example.walmartassessment.viewmodel.CountriesViewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val retrofit = Retrofit.Builder()
            .baseUrl(
                "https://gist.githubusercontent.com/peymano-wmt/32dcb892b06648910ddd40406e37fdab/raw/db25946fd77c5873b0303b858e861ce724e0dcd0/"
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(CountriesApi::class.java)

        val repository = CountryRepository(apiService)

        val vmFactory = object: ViewModelProvider.Factory {
            override fun <T: ViewModel> create(modelClass: Class<T>): T {
                return CountriesViewModel(repository) as T
            }
        }

        val viewModel = ViewModelProvider(this, vmFactory) [CountriesViewModel::class.java]
        setContent {
            CountriesScreenObserver(viewModel)
        }
    }
}

@Composable
fun CountriesScreenObserver(viewModel: CountriesViewModel) {
    val uiState by viewModel.uiState.collectAsState()

    CountriesScreen(uiState)
}
