package com.example.cs330_p01.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.ImageBitmap
import com.example.cs330_p01.R
import com.example.cs330_p01.database.ClothingItem

fun ChoseWeatherIcon(code: Int): Int {
    var id = 0
    when (code) {
        0 -> id = R.drawable.sunacak
        1 -> id = R.drawable.sunncloud
        2 -> id = R.drawable.cloudnsun
        3, 45, 48, 71, 73, 76, 77, 85, 86 -> id = R.drawable.cloud
        51, 53, 55, 56, 57, 61, 63, 65, 66, 67, 80, 81 -> id = R.drawable.rainy_cloud
        82, 99 -> id = R.drawable.rainnlightning_cloud
        95, 96 -> id = R.drawable.lightning_cloud
    }
    return id
}

@Composable
fun LoadedImage(imageBitmap: ImageBitmap): ImageBitmap {
    var getImage by remember {
        mutableStateOf<ImageBitmap?>(null)
    }
    getImage = imageBitmap
    return getImage!!
}

fun loadDrawableNames(n: Int): List<String> {
    var list: ArrayList<String> = ArrayList()
    for (i in 1..n) {
        list.add("clothingitem" + i.toString())
    }
    return list
}

class DetailImage() {
    companion object {
        var painterItem: ClothingItem = ClothingItem("", "", "", "", false, "")

        fun setItem(ci: ClothingItem) {
            painterItem = ci
        }

        fun getItem(): ClothingItem {
            return painterItem
        }
    }
}

class EditClothingItem() {
    companion object {
        var clothingItem: ClothingItem = ClothingItem("", "", "", "", false, "")

        fun setCode(code: String) {
            clothingItem.code = code
        }

        fun setName(name: String) {
            clothingItem.name = name
        }

        fun setItemtype(itemtype: String) {
            clothingItem.itemType = itemtype
        }

        fun setCategory(category: String) {
            clothingItem.category = category
        }

        fun setOnOrMore(oneormore: Boolean) {
            clothingItem.oneOrMoreColors = oneormore
        }

        fun setColor(color: String) {
            clothingItem.color = color
        }

        fun getItem(): ClothingItem {
            return clothingItem
        }
    }
}
