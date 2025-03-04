package com.example.walmartassessment.network

import com.example.walmartassessment.model.Country
import retrofit2.http.GET

interface CountriesApi {

    @GET("countries.json")
    suspend fun getCountries(): List<Country>

}