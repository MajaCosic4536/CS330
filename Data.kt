package com.example.cs330_p01.database

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