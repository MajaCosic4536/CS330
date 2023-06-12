package com.example.cs330_p01.ApiData

import com.example.cs330_p01.database.WeatherCode
import com.example.cs330_p01.database.WeatherData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Call
import retrofit2.awaitResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {
    @GET("v1/forecast")
    open fun getWeather(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("hourly") hourly: String?,
       // @Query("weathercode") weatherCode: Boolean,
        @Query("current_weather") currentWeather: Boolean,
        @Query("timezone") timezone: String
    ): Call<WeatherData?>?
}
