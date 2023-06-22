package com.example.cs330_p01.common

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.ImageBitmap
import com.example.cs330_p01.R
import com.example.cs330_p01.database.ClothingItem
import com.example.cs330_p01.database.DBClothes
import kotlin.random.Random
import kotlin.random.nextInt

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

class SelectedOptions() {
    companion object {
        var category = ""
        var OneOrMore = false
        var singleColor = ""
        var colors = ""
        fun setToNone() {
            category = ""
            OneOrMore = false
            singleColor = ""
            colors = ""
        }
    }
}

fun ShirtsList(dbClothes: DBClothes): ArrayList<ClothingItem> {
    val list = ArrayList<ClothingItem>()

    for (item in dbClothes.getClothingItems()) {
        if (item.itemType == "Shirts") {
            list.add(item)
        }
    }
    return list
}

fun PantsList(dbClothes: DBClothes): ArrayList<ClothingItem> {
    val list = ArrayList<ClothingItem>()

    for (item in dbClothes.getClothingItems()) {
        if (item.itemType == "Pants") {
            list.add(item)
        }
    }
    return list
}

fun BlousesList(dbClothes: DBClothes): ArrayList<ClothingItem> {
    val list = ArrayList<ClothingItem>()

    for (item in dbClothes.getClothingItems()) {
        if (item.itemType == "Blouse") {
            list.add(item)
        }
    }
    return list
}

fun TshirtsList(dbClothes: DBClothes): ArrayList<ClothingItem> {
    val list = ArrayList<ClothingItem>()

    for (item in dbClothes.getClothingItems()) {
        if (item.itemType == "T-Shirts") {
            list.add(item)
        }
    }
    return list
}

fun SkirtsList(dbClothes: DBClothes): ArrayList<ClothingItem> {
    val list = ArrayList<ClothingItem>()

    for (item in dbClothes.getClothingItems()) {
        if (item.itemType == "Skirts") {
            list.add(item)
        }
    }
    return list
}

fun DressesList(dbClothes: DBClothes): ArrayList<ClothingItem> {
    val list = ArrayList<ClothingItem>()

    for (item in dbClothes.getClothingItems()) {
        if (item.itemType == "Dresses") {
            list.add(item)
        }
    }
    return list
}

fun ShortsList(dbClothes: DBClothes): ArrayList<ClothingItem> {
    val list = ArrayList<ClothingItem>()

    for (item in dbClothes.getClothingItems()) {
        if (item.itemType == "Shorts") {
            list.add(item)
        }
    }
    return list
}

fun SweatersList(dbClothes: DBClothes): ArrayList<ClothingItem> {
    val list = ArrayList<ClothingItem>()

    for (item in dbClothes.getClothingItems()) {
        if (item.itemType == "Sweaters") {
            list.add(item)
        }
    }
    return list
}

fun JacketsList(dbClothes: DBClothes): ArrayList<ClothingItem> {
    val list = ArrayList<ClothingItem>()

    for (item in dbClothes.getClothingItems()) {
        if (item.itemType == "Jackets") {
            list.add(item)
        }
    }
    return list
}

fun BlazersList(dbClothes: DBClothes): ArrayList<ClothingItem> {
    val list = ArrayList<ClothingItem>()

    for (item in dbClothes.getClothingItems()) {
        if (item.itemType == "Blazers") {
            list.add(item)
        }
    }
    return list
}

fun SocksList(dbClothes: DBClothes): ArrayList<ClothingItem> {
    val list = ArrayList<ClothingItem>()

    for (item in dbClothes.getClothingItems()) {
        if (item.itemType == "Socks") {
            list.add(item)
        }
    }
    return list
}

fun AccessoriesList(dbClothes: DBClothes): ArrayList<ClothingItem> {
    val list = ArrayList<ClothingItem>()

    for (item in dbClothes.getClothingItems()) {
        if (item.itemType == "Accessories") {
            list.add(item)
        }
    }
    return list
}

fun ShoesList(dbClothes: DBClothes): ArrayList<ClothingItem> {
    val list = ArrayList<ClothingItem>()

    for (item in dbClothes.getClothingItems()) {
        if (item.itemType == "Shoes") {
            list.add(item)
        }
    }
    return list
}

fun GetRecomendedOutfit(dbClothes: DBClothes, currentTemp: Double): ArrayList<ClothingItem> {
    var list = ArrayList<ClothingItem>()
    var index = 0
    val category=SelectedOptions.category
    val oneormore=SelectedOptions.OneOrMore
    val singlecolor=SelectedOptions.singleColor

    //list = getByTemp(dbClothes, currentTemp)
    if (category == "" && SelectedOptions.OneOrMore == false) {
        list = getByTemp(dbClothes, currentTemp,category)
    } else if (category != "" && SelectedOptions.OneOrMore == false){
//       do {
//            list = getByTemp(dbClothes, currentTemp)
//        }
        while ((getByTemp(dbClothes,currentTemp,category)[0].category.contains(SelectedOptions.category) || getByTemp(dbClothes,currentTemp,category)[0].category.equals("")) &&
            (getByTemp(dbClothes, currentTemp,category)[1].category.contains(SelectedOptions.category) || getByTemp(dbClothes, currentTemp,category)[1].category.equals("")) &&
            (getByTemp(dbClothes, currentTemp,category)[2].category.contains(SelectedOptions.category) || getByTemp(dbClothes, currentTemp,category)[2].category.equals("")) &&
            (getByTemp(dbClothes, currentTemp,category)[3].category.contains(SelectedOptions.category) || getByTemp(dbClothes, currentTemp,category)[3].category.equals("")) //&&
//            (getByTemp(dbClothes, currentTemp)[4].category.contains(SelectedOptions.category) || getByTemp(dbClothes, currentTemp)[4].category.equals("")) &&
//            (getByTemp(dbClothes, currentTemp)[5].category.contains(SelectedOptions.category) || getByTemp(dbClothes, currentTemp)[5].category.equals("")) &&
//            (getByTemp(dbClothes, currentTemp)[6].category.contains(SelectedOptions.category) || getByTemp(dbClothes, currentTemp)[6].category.equals("")) &&
//            (getByTemp(dbClothes, currentTemp)[7].category.contains(SelectedOptions.category) || getByTemp(dbClothes, currentTemp)[7].category.equals(""))
        ){
           list = getByTemp(dbClothes, currentTemp,category)
       }
    }else if (category != "" && SelectedOptions.OneOrMore == true){
//        do {
//            list = getByTemp(dbClothes, currentTemp)
//        }
        while (((getByTemp(dbClothes,currentTemp,category)[0].category.contains(SelectedOptions.category) || getByTemp(dbClothes,currentTemp,category)[0].category.equals("")) &&
            (getByTemp(dbClothes, currentTemp,category)[1].category.contains(SelectedOptions.category) || getByTemp(dbClothes, currentTemp,category)[1].category.equals("")) &&
            (getByTemp(dbClothes, currentTemp,category)[2].category.contains(SelectedOptions.category) || getByTemp(dbClothes, currentTemp,category)[2].category.equals("")) &&
            (getByTemp(dbClothes, currentTemp,category)[3].category.contains(SelectedOptions.category) || getByTemp(dbClothes, currentTemp,category)[3].category.equals("")) &&
//            (getByTemp(dbClothes, currentTemp)[4].category.contains(SelectedOptions.category) || getByTemp(dbClothes, currentTemp)[4].category.equals("")) &&
//            (getByTemp(dbClothes, currentTemp)[5].category.contains(SelectedOptions.category) || getByTemp(dbClothes, currentTemp)[5].category.equals("")) &&
//            (getByTemp(dbClothes, currentTemp)[6].category.contains(SelectedOptions.category) || getByTemp(dbClothes, currentTemp)[6].category.equals("")) &&
//            (getByTemp(dbClothes, currentTemp)[7].category.contains(SelectedOptions.category) || getByTemp(dbClothes, currentTemp)[7].category.equals(""))) &&
//            ((getByTemp(dbClothes,currentTemp)[0].color.contains(SelectedOptions.singleColor) || getByTemp(dbClothes,currentTemp)[0].color.equals(""))
                    //&&
                    (getByTemp(dbClothes, currentTemp,category)[1].color.contains(SelectedOptions.singleColor) || getByTemp(dbClothes, currentTemp,category)[1].color.equals("")) &&
                    (getByTemp(dbClothes, currentTemp,category)[2].color.contains(SelectedOptions.singleColor) || getByTemp(dbClothes, currentTemp,category)[2].color.equals("")) &&
                    (getByTemp(dbClothes, currentTemp,category)[3].color.contains(SelectedOptions.singleColor) || getByTemp(dbClothes, currentTemp,category)[3].color.equals(""))// &&
                   // (getByTemp(dbClothes, currentTemp)[4].color.contains(SelectedOptions.singleColor) || getByTemp(dbClothes, currentTemp)[4].color.equals("")) //&&
//                    (getByTemp(dbClothes, currentTemp)[5].color.contains(SelectedOptions.singleColor) || getByTemp(dbClothes, currentTemp)[5].color.equals("")) &&
//                    (getByTemp(dbClothes, currentTemp)[6].color.contains(SelectedOptions.singleColor) || getByTemp(dbClothes, currentTemp)[6].color.equals("")) &&
//                    (getByTemp(dbClothes, currentTemp)[7].color.contains(SelectedOptions.singleColor) || getByTemp(dbClothes, currentTemp)[7].color.equals(""))
                  )) {
            list = getByTemp(dbClothes, currentTemp,category)
        }}
        else if (category == "" && SelectedOptions.OneOrMore == true){
//        do {
//            list = getByTemp(dbClothes, currentTemp)
//        }
            while (((getByTemp(dbClothes,currentTemp,category)[0].color.contains(SelectedOptions.singleColor) || getByTemp(dbClothes,currentTemp,category)[0].color.equals("")) &&
                        (getByTemp(dbClothes, currentTemp,category)[1].color.contains(SelectedOptions.singleColor) || getByTemp(dbClothes, currentTemp,category)[1].color.equals("")) &&
                        (getByTemp(dbClothes, currentTemp,category)[2].color.contains(SelectedOptions.singleColor) || getByTemp(dbClothes, currentTemp,category)[2].color.equals("")) &&
                        (getByTemp(dbClothes, currentTemp,category)[3].color.contains(SelectedOptions.singleColor) || getByTemp(dbClothes, currentTemp,category)[3].color.equals(""))
                        )){
            list = getByTemp(dbClothes, currentTemp,category)
        }
    }


    val removedDubelsList = list.distinct()
    val newlist = ArrayList<ClothingItem>()
    for (item in removedDubelsList) {
        if (item.code != "") {
            newlist.add(item)
        }
    }

    return newlist
}

fun getByTemp(dbClothes: DBClothes, currentTemp: Double,category: String): ArrayList<ClothingItem> {
    val list = ArrayList<ClothingItem>()

    var item01 = ClothingItem("", "", "", "", false, "")
    var item02 = ClothingItem("", "", "", "", false, "")
    var item03 = ClothingItem("", "", "", "", false, "")
    var item04 = ClothingItem("", "", "", "", false, "")
    var item05 = ClothingItem("", "", "", "", false, "")
    var item06 = ClothingItem("", "", "", "", false, "")
    var item07 = ClothingItem("", "", "", "", false, "")
    var item08 = ClothingItem("", "", "", "", false, "")

    val shirtList = ShirtsList(dbClothes)
    val pantsList = PantsList(dbClothes)
    //  val blousesList = BlousesList(dbClothes)
    //  val tshirtsList = TshirtsList(dbClothes)
    val skirtList = SkirtsList(dbClothes)
    val dressesList = DressesList(dbClothes)
    val shortsList = ShortsList(dbClothes)
    val sweatersList = SweatersList(dbClothes)
    val jacketsList = JacketsList(dbClothes)
    //  val blazersList = BlazersList(dbClothes)
    val socksList = SocksList(dbClothes)
    val shoesList = ShoesList(dbClothes)
    val accList = AccessoriesList(dbClothes)

    var n = Random.nextInt(1..3)
    var m = Random.nextInt(1..3)

    if (currentTemp >= 26) {
        if (dressesList.isEmpty()) {
            item01 = shirtList.get(Random.nextInt(0..shirtList.size - 1))
            if (shortsList.isEmpty()) {
                item02 = skirtList.get(Random.nextInt(0..skirtList.size - 1))
            } else if (skirtList.isEmpty()) {
                item02 = shortsList.get(Random.nextInt(0..shortsList.size - 1))
            } else {
                if (n % 2 == 0) {
                    item02 = skirtList.get(Random.nextInt(0..skirtList.size - 1))
                } else {
                    item02 = shortsList.get(Random.nextInt(0..shortsList.size - 1))
                }
            }
        } else if (shirtList.isEmpty() || skirtList.isEmpty() && shortsList.isEmpty()) {
            item01 = dressesList.get(Random.nextInt(0..dressesList.size - 1))
            item02 = ClothingItem("", "", "", "", false, "")
        } else {
            if (n % 2 == 0) {
                item01 = shirtList.get(Random.nextInt(0..shirtList.size - 1))
                if (shortsList.isEmpty()) {
                    item02 = skirtList.get(Random.nextInt(0..skirtList.size - 1))
                } else if (skirtList.isEmpty()) {
                    item02 = shortsList.get(Random.nextInt(0..shortsList.size - 1))
                } else {
                    if (m % 2 == 0) {
                        item02 = skirtList.get(Random.nextInt(0..skirtList.size - 1))
                    } else {
                        item02 = shortsList.get(Random.nextInt(0..shortsList.size - 1))
                    }
                }
            } else {
                item01 = dressesList.get(Random.nextInt(0..dressesList.size - 1))
                item02 = ClothingItem("", "", "", "", false, "")
            }
        }
        if (shoesList.isNotEmpty())
            item03 = shoesList.get(Random.nextInt(0..shoesList.size - 1))
        if (accList.isNotEmpty()) {
            item04 = accList.get(Random.nextInt(0..accList.size - 1))
            item05 = accList.get(Random.nextInt(0..accList.size - 1))
            item06 = accList.get(Random.nextInt(0..accList.size - 1))
        }
    } else if (currentTemp >= 20) {
        if (dressesList.isEmpty()) {
            item01 = shirtList.get(Random.nextInt(0..shirtList.size - 1))
            if (shortsList.isEmpty()) {
                if (n % 2 == 0) {
                    item02 = skirtList.get(Random.nextInt(0..skirtList.size - 1))
                } else {
                    item02 = pantsList.get(Random.nextInt(0..pantsList.size - 1))
                }
            } else if (skirtList.isEmpty()) {
                if (n % 2 == 0) {
                    item02 = shortsList.get(Random.nextInt(0..shortsList.size - 1))
                } else {
                    item02 = pantsList.get(Random.nextInt(0..pantsList.size - 1))
                }
            } else if (pantsList.isEmpty()) {
                if (n % 2 == 0) {
                    item02 = shortsList.get(Random.nextInt(0..shortsList.size - 1))
                } else {
                    item02 = skirtList.get(Random.nextInt(0..skirtList.size - 1))
                }
            } else {
                if (n == 1) {
                    item02 = skirtList.get(Random.nextInt(0..skirtList.size - 1))
                } else if (n == 2) {
                    item02 = shortsList.get(Random.nextInt(0..shortsList.size - 1))
                } else {
                    item02 = pantsList.get(Random.nextInt(0..pantsList.size - 1))
                }
            }
        } else if (shirtList.isEmpty() || skirtList.isEmpty() && shortsList.isEmpty() && pantsList.isEmpty()) {
            item01 = dressesList.get(Random.nextInt(0..dressesList.size - 1))
            item02 = ClothingItem("", "", "", "", false, "")
        } else {
            if (n % 2 != 0) {
                item01 = shirtList.get(Random.nextInt(0..shirtList.size - 1))
                if (shortsList.isEmpty()) {
                    if (m % 2 == 0) {
                        item02 = skirtList.get(Random.nextInt(0..skirtList.size - 1))
                    } else {
                        item02 = pantsList.get(Random.nextInt(0..pantsList.size - 1))
                    }
                } else if (skirtList.isEmpty()) {
                    if (m % 2 == 0) {
                        item02 = shortsList.get(Random.nextInt(0..shortsList.size - 1))
                    } else {
                        item02 = pantsList.get(Random.nextInt(0..pantsList.size - 1))
                    }
                } else if (pantsList.isEmpty()) {
                    if (m % 2 == 0) {
                        item02 = shortsList.get(Random.nextInt(0..shortsList.size - 1))
                    } else {
                        item02 = skirtList.get(Random.nextInt(0..skirtList.size - 1))
                    }
                } else {
                    if (m == 1) {
                        if (skirtList.isNotEmpty())
                            item02 = skirtList.get(Random.nextInt(0..skirtList.size - 1))
                    } else if (m == 2) {
                        if (shortsList.isNotEmpty())
                            item02 = shortsList.get(Random.nextInt(0..shortsList.size - 1))
                    } else {
                        if (pantsList.isNotEmpty())
                            item02 = pantsList.get(Random.nextInt(0..pantsList.size - 1))
                    }
                }
            } else {
                item01 = dressesList.get(Random.nextInt(0..dressesList.size - 1))
                item02 = ClothingItem("", "", "", "", false, "")
            }
        }
        if (socksList.isNotEmpty())
            item03 = socksList.get(Random.nextInt(0..socksList.size - 1))
        if (shoesList.isNotEmpty())
            item04 = shoesList.get(Random.nextInt(0..shoesList.size - 1))
        if (accList.isNotEmpty()) {
            item05 = accList.get(Random.nextInt(0..accList.size - 1))
            item06 = accList.get(Random.nextInt(0..accList.size - 1))
        }
    } else if (currentTemp >= 15) {
        if (dressesList.isEmpty()) {
            item01 = shirtList.get(Random.nextInt(0..shirtList.size - 1))
            if (skirtList.isEmpty()) {
                item02 = pantsList.get(Random.nextInt(0..pantsList.size - 1))
            } else if (pantsList.isEmpty()) {
                item02 = skirtList.get(Random.nextInt(0..skirtList.size - 1))
            } else {
                if (n % 2 == 0) {
                    item02 = skirtList.get(Random.nextInt(0..skirtList.size - 1))
                } else {
                    item02 = pantsList.get(Random.nextInt(0..pantsList.size - 1))
                }
            }
        } else if (shirtList.isEmpty() || skirtList.isEmpty() && pantsList.isEmpty()) {
            item01 = dressesList.get(Random.nextInt(0..dressesList.size - 1))
            item02 = ClothingItem("", "", "", "", false, "")
        } else {
            if (n % 2 != 0) {
                item01 = shirtList.get(Random.nextInt(0..shirtList.size - 1))
                if (skirtList.isEmpty()) {
                    item02 = pantsList.get(Random.nextInt(0..pantsList.size - 1))
                } else if (pantsList.isEmpty()) {
                    item02 = skirtList.get(Random.nextInt(0..skirtList.size - 1))
                } else {
                    if (m % 2 == 0) {
                        item02 = skirtList.get(Random.nextInt(0..skirtList.size - 1))
                    } else {
                        item02 = pantsList.get(Random.nextInt(0..pantsList.size - 1))
                    }
                }
            } else {
                item01 = dressesList.get(Random.nextInt(0..dressesList.size - 1))
                item02 = ClothingItem("", "", "", "", false, "")
            }
        }
        if (sweatersList.isNotEmpty())
            item03 = sweatersList.get(Random.nextInt(0..sweatersList.size - 1))
        if (socksList.isNotEmpty())
            item04 = socksList.get(Random.nextInt(0..socksList.size - 1))
        if (shoesList.isNotEmpty())
            item05 = shoesList.get(Random.nextInt(0..shoesList.size - 1))
        if (accList.isNotEmpty()) {
            item06 = accList.get(Random.nextInt(0..accList.size - 1))
            item07 = accList.get(Random.nextInt(0..accList.size - 1))
        }
    } else if (currentTemp >= 10) {
        if (shirtList.isNotEmpty()) {
            item01 = shirtList.get(Random.nextInt(0..shirtList.size - 1))
            if (skirtList.isEmpty()) {
                item02 = pantsList.get(Random.nextInt(0..pantsList.size - 1))
            } else if (pantsList.isEmpty()) {
                item02 = skirtList.get(Random.nextInt(0..skirtList.size - 1))
            } else {
                if (n % 2 == 0) {
                    item02 = skirtList.get(Random.nextInt(0..skirtList.size - 1))
                } else {
                    item02 = pantsList.get(Random.nextInt(0..pantsList.size - 1))
                }
            }
        }

        if (sweatersList.isNotEmpty())
            item03 = sweatersList.get(Random.nextInt(0..sweatersList.size - 1))
        if (socksList.isNotEmpty())
            item04 = socksList.get(Random.nextInt(0..socksList.size - 1))
        if (shoesList.isNotEmpty())
            item05 = shoesList.get(Random.nextInt(0..shoesList.size - 1))
        if (jacketsList.isNotEmpty())
            item06 = jacketsList.get(Random.nextInt(0..jacketsList.size - 1))
        if (accList.isNotEmpty())
            item07 = accList.get(Random.nextInt(0..accList.size - 1))
    } else {
        if (shirtList.isNotEmpty()) {
            item01 = shirtList.get(Random.nextInt(0..shirtList.size - 1))
            if (pantsList.isNotEmpty()) {
                item02 = pantsList.get(Random.nextInt(0..pantsList.size - 1))
            }
        }

        if (sweatersList.isNotEmpty())
            item03 = sweatersList.get(Random.nextInt(0..sweatersList.size - 1))
        if (socksList.isNotEmpty())
            item04 = socksList.get(Random.nextInt(0..socksList.size - 1))
        if (shoesList.isNotEmpty())
            item05 = shoesList.get(Random.nextInt(0..shoesList.size - 1))
        if (jacketsList.isNotEmpty())
            item06 = jacketsList.get(Random.nextInt(0..jacketsList.size - 1))
        if (accList.isNotEmpty())
            item07 = accList.get(Random.nextInt(0..accList.size - 1))
        item08 = accList.get(Random.nextInt(0..accList.size - 1))
    }
    list.add(item01)
    list.add(item02)
    list.add(item03)
    if(category!="") {
        if (item04.category == category)
            list.add(item04)
        if (item05.category == category)
            list.add(item05)
        if (item06.category == category)
            list.add(item06)
        if (item07.category == category)
            list.add(item07)
        if (item08.category == category)
            list.add(item08)
    }else{
            list.add(item04)
            list.add(item05)
            list.add(item06)
            list.add(item07)
            list.add(item08)
    }
    return list
}