package com.example.walmartassessment.repository

import com.example.walmartassessment.model.Country
import com.example.walmartassessment.network.CountriesApi

class CountryRepository(private val api: CountriesApi) {


    suspend fun fetchCountries(): List<Country> {
        return api.getCountries()
    }


}