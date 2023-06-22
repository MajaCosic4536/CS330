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

fun getMyColorList(): ArrayList<String> {
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
        "Neutrals",
        "Grey",
        "Black",
        "White"
    )
    val list = ArrayList<String>()
    for (i in colorList){
        list.add(i)
    }

    return list
}

fun getSortingCategory(): ArrayList<String> {
    val categorylist = listOf(
        "All",
        "Shirts",
        "Pants",
        //"Blouse",
        //"T-Shirts",
        "Skirts",
        "Dresses",
        "Shorts",
        "Sweaters",
        "Jackets",
        //"Blazers",
        "Socks",
        "Accessories",
        "Shoes"
    )
    val list = ArrayList<String>()
    for (i in categorylist){
        list.add(i)
    }

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
    var code: String,
    var name: String,
    var itemType: String,
    var category: String,
    var oneOrMoreColors: Boolean,
    var color: String
) {}

fun addStarterClothingItems(dbClothes: DBClothes) {
    dbClothes.insertClothingItem(
        ClothingItem(
            "clothingitem1",
            "Beige sweater",
            "Sweaters",
            "Casual",
            false,
            "Neutrals"
        )
    )

    dbClothes.insertClothingItem(
        ClothingItem(
            "clothingitem2",
            "Long sleeve crop-top",
            "Shirts",
            "Elegant",
            false,
            "Neutrals"
        )
    )

    dbClothes.insertClothingItem(
        ClothingItem(
            "clothingitem3",
            "Patched cardigan",
            "Sweaters",
            "Casual",
            false,
            "White"
        )
    )

    dbClothes.insertClothingItem(
        ClothingItem(
            "clothingitem4",
            "Black platform boots",
            "Shoes",
            "Casual",
            false,
            "Black"
        )
    )

    dbClothes.insertClothingItem(
        ClothingItem(
            "clothingitem5",
            "White doc martens",
            "Shoes",
            "Casual",
            false,
            "White"
        )
    )

    dbClothes.insertClothingItem(
        ClothingItem(
            "clothingitem6",
            "Olive green shirt",
            "Shirts",
            "Casual",
            false,
            "Green"
        )
    )

    dbClothes.insertClothingItem(
        ClothingItem(
            "clothingitem7",
            "Cloud necklaces",
            "Accessories",
            "Casual",
            true,
            "Rainbow"
        )
    )


    dbClothes.insertClothingItem(
        ClothingItem(
            "clothingitem8",
            "Dark blue jeans",
            "Pants",
            "Casual",
            false,
            "Dark blue"
        )
    )

    dbClothes.insertClothingItem(
        ClothingItem(
            "clothingitem9",
            "Green cat sweater",
            "Sweaters",
            "Casual",
            true,
            "Green/White"
        )
    )

    dbClothes.insertClothingItem(
        ClothingItem(
            "clothingitem10",
            "Cat tote",
            "Accessories",
            "Casual/Party",
            true,
            "Black/White"
        )
    )

    dbClothes.insertClothingItem(
        ClothingItem(
            "clothingitem11",
            "Strawberry earrings",
            "Accessories",
            "Casual/Party",
            false,
            "Red"
        )
    )

    dbClothes.insertClothingItem(
        ClothingItem(
            "clothingitem12",
            "Heart earrings",
            "Accessories",
            "Casual/Party/Elegant",
            false,
            "Grey"
        )
    )

    dbClothes.insertClothingItem(
        ClothingItem(
            "clothingitem13",
            "Pink pladed skirt",
            "Skirts",
            "Casual/Party",
            false,
            "Pink"
        )
    )

    dbClothes.insertClothingItem(
        ClothingItem(
            "clothingitem14",
            "Pink blouse",
            "Blouse",
            "Casual/Party",
            false,
            "Pink"
        )
    )

    dbClothes.insertClothingItem(
        ClothingItem(
            "clothingitem15",
            "White dress",
            "Dresses",
            "Casual/Party/Elegant",
            false,
            "White"
        )
    )
}