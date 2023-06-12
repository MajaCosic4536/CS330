package com.example.cs330_p01.database

import com.google.gson.annotations.SerializedName

class Category(
    var key: Int,
    var name: String
) {
}

class MyColor(
    var key: Int,
    var name: String
) {
}

fun getMyColorList(): List<String> {
    val colorList = listOf(
        "Red",
        "Orange",
        "Yellow",
        "Green",
        "Light blue",
        "Dark blue",
        "Purple",
        "Pink",
        "Magenta",
        "Cyan",
        "Nutrals",
        "Black",
        "White"
    )
    return colorList
}

fun getSortingCategory(): List<String> {
    val list = listOf(
        "All",
        "Shirts",
        "Pants",
        "Blouse",
        "T-Shirts",
        "Skirts",
        "Dresses",
        "Shorts",
        "Sweaters",
        "Jackets",
        "Blazers",
        "Accessories",
        "Shoes"
    )
    return list
}

data class WeatherData(
    val current_weather: CurrentWeather,
    val elevation: Double,
    val generationtime_ms: Double,
    val hourly: Hourly,
    val hourly_units: HourlyUnits,
    val latitude: Double,
    val longitude: Double,
    val timezone: String,
    val timezone_abbreviation: String,
    val utc_offset_seconds: Int
)

data class CurrentWeather(
    val is_day: Int,
    val temperature: Double,
    val time: String,
    val weathercode: Int,
    val winddirection: Double,
    val windspeed: Double
)

data class Hourly(
    val temperature_2m: List<Double>,
    val time: List<String>,
    val weathercode: List<Int>
)

data class HourlyUnits(
    val temperature_2m: String,
    val time: String,
    val weathercode: String
)

data class WeatherCode(
    var code: Int,
    var descripton: String
)

data class ClothingItem(
    var name: String,
    var itemType: String,
    var category: String,
    var oneOrMoreColors: Boolean,
    var color: String
) {}