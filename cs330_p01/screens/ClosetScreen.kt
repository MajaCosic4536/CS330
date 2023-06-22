package com.example.cs330_p01.screens

import android.content.Context
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.cs330_p01.R
import com.example.cs330_p01.common.AppViewModel
import com.example.cs330_p01.common.DetailImage
import com.example.cs330_p01.common.EmptyScreen
import com.example.cs330_p01.common.LogoNHelpCard
import com.example.cs330_p01.common.NavigationBar
import com.example.cs330_p01.common.loadDrawableNames
import com.example.cs330_p01.database.ClothingItem
import com.example.cs330_p01.database.DBClothes
import com.example.cs330_p01.database.addStarterClothingItems
import com.example.cs330_p01.database.getSortingCategory

@Composable
fun ClosetScreen(viewModel: AppViewModel, paddingValues: PaddingValues) {
    Image(
        painter = painterResource(id = R.drawable.background),
        contentDescription = "Background",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.FillWidth
    )
    ClosetScreenView(viewModel)
    NavigationBar(
        { viewModel.goToHomeScreen() },
        { viewModel.goToPickerScreen() },
        { viewModel.goToAddScreen() },
        { viewModel.goToClosetScreen() }
    )

}

@Composable
fun ClosetScreenView(viewModel: AppViewModel) {
    Column {
        LogoNHelpCard()
        AllClothesCard({
            viewModel.goToSortClotihngItemScreen()
        })
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AllClothesCard(clothDetail: () -> Unit) {
    val context = LocalContext.current
    val dbClothes = DBClothes(context = context)

    var showAll by remember {
        mutableStateOf(true)
    }

    var showShirts by remember {
        mutableStateOf(false)
    }

    var showPants by remember {
        mutableStateOf(false)
    }

//    var showBlouses by remember {
//        mutableStateOf(false)
//    }

//    var showTShirts by remember {
//        mutableStateOf(false)
//    }

    var showSkirts by remember {
        mutableStateOf(false)
    }

    var showDresses by remember {
        mutableStateOf(false)
    }

    var showShorts by remember {
        mutableStateOf(false)
    }

    var showSweaters by remember {
        mutableStateOf(false)
    }

    var showJackets by remember {
        mutableStateOf(false)
    }

//    var showBlazers by remember {
//        mutableStateOf(false)
//    }

    var showSocks by remember {
        mutableStateOf(false)
    }

    var showAccessories by remember {
        mutableStateOf(false)
    }

    var showShoes by remember {
        mutableStateOf(false)
    }

    var showEmpty by remember {
        mutableStateOf(false)
    }
    // addStarterClothingItems(dbClothes)

    var shirts = ArrayList<ClothingItem>()
    var pants = ArrayList<ClothingItem>()
    // var blouses = ArrayList<ClothingItem>()
    // var tshirts = ArrayList<ClothingItem>()
    var skirts = ArrayList<ClothingItem>()
    var dresses = ArrayList<ClothingItem>()
    var shorts = ArrayList<ClothingItem>()
    var sweaters = ArrayList<ClothingItem>()
    var jackets = ArrayList<ClothingItem>()
    // var blazers = ArrayList<ClothingItem>()
    var socks = ArrayList<ClothingItem>()
    var accessories = ArrayList<ClothingItem>()
    var shoes = ArrayList<ClothingItem>()

    for (item in dbClothes.getClothingItems()) {
        when (item.itemType) {
            "Shirts" -> shirts.add(item)
            "Pants" -> pants.add(item)
            // "Blouse" -> blouses.add(item)
            // "T-Shirts" -> tshirts.add(item)
            "Skirts" -> skirts.add(item)
            "Dresses" -> dresses.add(item)
            "Shorts" -> shorts.add(item)
            "Sweaters" -> sweaters.add(item)
            "Jackets" -> jackets.add(item)
            // "Blazers" -> blazers.add(item)
            "Socks" -> socks.add(item)
            "Accessories" -> accessories.add(item)
            "Shoes" -> shoes.add(item)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Card(
            modifier = Modifier
                .padding(start = 16.dp, top = 2.dp, bottom = 90.dp, end = 16.dp)
                .fillMaxSize(),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp
            ), colors = CardDefaults.cardColors(
                MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.8f)
            )
        ) {
            Column() {
                LazyRow(
                    modifier = Modifier.padding(
                        start = 16.dp,
                        top = 2.dp,
                        bottom = 2.dp,
                        end = 16.dp
                    ),
                    horizontalArrangement = Arrangement.spacedBy(1.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    items(getSortingCategory()) { category ->
                        Button(onClick = {
                            if (category == "All") {
                                showAll = true
                                showShirts = false
                                showPants = false
                                //  showBlouses = false
                                // showTShirts = false
                                showSkirts = false
                                showDresses = false
                                showShorts = false
                                showSweaters = false
                                showJackets = false
                                // showBlazers = false
                                showSocks = false
                                showAccessories = false
                                showShoes = false
                            } else if (category == "Shirts") {
                                showAll = false
                                showShirts = true
                                showPants = false
                                // showBlouses = false
                                // showTShirts = false
                                showSkirts = false
                                showDresses = false
                                showShorts = false
                                showSweaters = false
                                showJackets = false
                                // showBlazers = false
                                showSocks = false
                                showAccessories = false
                                showShoes = false
                            } else if (category == "Pants") {
                                showAll = false
                                showShirts = false
                                showPants = true
                                //showBlouses = false
                                // showTShirts = false
                                showSkirts = false
                                showDresses = false
                                showShorts = false
                                showSweaters = false
                                showJackets = false
                                // showBlazers = false
                                showSocks = false
                                showAccessories = false
                                showShoes = false
                            } else if (category == "Blouse") {
                                showAll = false
                                showShirts = false
                                showPants = false
                                //  showBlouses = true
                                // showTShirts = false
                                showSkirts = false
                                showDresses = false
                                showShorts = false
                                showSweaters = false
                                showJackets = false
                                //  showBlazers = false
                                showSocks = false
                                showAccessories = false
                                showShoes = false
                            } else if (category == "T-Shirts") {
                                showAll = false
                                showShirts = false
                                showPants = false
                                //showBlouses = false
                              // showTShirts = true
                                showSkirts = false
                                showDresses = false
                                showShorts = false
                                showSweaters = false
                                showJackets = false
                                //showBlazers = false
                                showSocks = false
                                showAccessories = false
                                showShoes = false
                            } else if (category == "Skirts") {
                                showAll = false
                                showShirts = false
                                showPants = false
//                                showBlouses = false
//                                showTShirts = false
                                showSkirts = true
                                showDresses = false
                                showShorts = false
                                showSweaters = false
                                showJackets = false
//                                showBlazers = false
                                showSocks = false
                                showAccessories = false
                                showShoes = false
                            } else if (category == "Dresses") {
                                showAll = false
                                showShirts = false
                                showPants = false
//                                showBlouses = false
//                                showTShirts = false
                                showSkirts = false
                                showDresses = true
                                showShorts = false
                                showSweaters = false
                                showJackets = false
//                                showBlazers = false
                                showSocks = false
                                showAccessories = false
                                showShoes = false
                            } else if (category == "Shorts") {
                                showAll = false
                                showShirts = false
                                showPants = false
//                                showBlouses = false
//                                showTShirts = false
                                showSkirts = false
                                showDresses = false
                                showShorts = true
                                showSweaters = false
                                showJackets = false
//                                showBlazers = false
                                showSocks = false
                                showAccessories = false
                                showShoes = false
                            } else if (category == "Sweaters") {
                                showAll = false
                                showShirts = false
                                showPants = false
//                                showBlouses = false
//                                showTShirts = false
                                showSkirts = false
                                showDresses = false
                                showShorts = false
                                showSweaters = true
                                showJackets = false
//                                showBlazers = false
                                showSocks = false
                                showAccessories = false
                                showShoes = false
                            } else if (category == "Jackets") {
                                showAll = false
                                showShirts = false
                                showPants = false
//                                showBlouses = false
//                                showTShirts = false
                                showSkirts = false
                                showDresses = false
                                showShorts = false
                                showSweaters = false
                                showJackets = true
//                                showBlazers = false
                                showSocks = false
                                showAccessories = false
                                showShoes = false
                            } else if (category == "Blazers") {
                                showAll = false
                                showShirts = false
                                showPants = false
//                                showBlouses = false
//                                showTShirts = false
                                showSkirts = false
                                showDresses = false
                                showShorts = false
                                showSweaters = false
                                showJackets = false
//                                showBlazers = true
                                showSocks = false
                                showAccessories = false
                                showShoes = false
                            } else if (category == "Socks") {
                                showAll = false
                                showShirts = false
                                showPants = false
//                                showBlouses = false
//                                showTShirts = false
                                showSkirts = false
                                showDresses = false
                                showShorts = false
                                showSweaters = false
                                showJackets = false
//                                showBlazers = false
                                showSocks = true
                                showAccessories = false
                                showShoes = false
                            } else if (category == "Accessories") {
                                showAll = false
                                showShirts = false
                                showPants = false
//                                showBlouses = false
//                                showTShirts = false
                                showSkirts = false
                                showDresses = false
                                showShorts = false
                                showSweaters = false
                                showJackets = false
//                                showBlazers = false
                                showSocks = false
                                showAccessories = true
                                showShoes = false
                            } else if (category == "Shoes") {
                                showAll = false
                                showShirts = false
                                showPants = false
//                                showBlouses = false
//                                showTShirts = false
                                showSkirts = false
                                showDresses = false
                                showShorts = false
                                showSweaters = false
                                showJackets = false
//                                showBlazers = false
                                showSocks = false
                                showAccessories = false
                                showShoes = true
                            }
                        }) {
                            Text(text = category)
                        }
                    }
                }
                if (
                    showEmpty
                ) {
                    EmptyScreen()
                }
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(2.dp)
                ) {
                    if (showAll) {
                        if (dbClothes.getClothingItems().isEmpty()) {
                            showEmpty = true
                        } else {
                            showEmpty = false
                        }
                        items(dbClothes.getClothingItems()) { ci ->
                            showItems(context = context, ci = ci) { clothDetail() }
                        }
                    } else if (showShirts) {
                        if (shirts.isEmpty()) {
                            showEmpty = true
                        } else {
                            showEmpty = false
                        }
                        items(shirts) { ci ->
                            showItems(context = context, ci = ci) { clothDetail() }
                        }
                    } else if (showPants) {
                        if (pants.isEmpty()) {
                            showEmpty = true
                        } else {
                            showEmpty = false
                        }
                        items(pants) { ci ->
                            showItems(context = context, ci = ci) { clothDetail() }
                        }
//                    } else if (showBlouses) {
//                        if (blouses.isEmpty()) {
//                            showEmpty = true
//                        } else {
//                            showEmpty = false
//                        }
//                        items(blouses) { ci ->
//                            showItems(context = context, ci = ci) { clothDetail() }
//                        }
//                    } else if (showTShirts) {
//                        if (tshirts.isEmpty()) {
//                            showEmpty = true
//                        } else {
//                            showEmpty = false
//                        }
//                        items(tshirts) { ci ->
//                            showItems(context = context, ci = ci) { clothDetail() }
//                        }
                    } else if (showSkirts) {
                        if (skirts.isEmpty()) {
                            showEmpty = true
                        } else {
                            showEmpty = false
                        }
                        items(skirts) { ci ->
                            showItems(context = context, ci = ci) { clothDetail() }
                        }
                    } else if (showDresses) {
                        if (dresses.isEmpty()) {
                            showEmpty = true
                        } else {
                            showEmpty = false
                        }
                        items(dresses) { ci ->
                            showItems(context = context, ci = ci) { clothDetail() }
                        }
                    } else if (showShorts) {
                        if (shorts.isEmpty()) {
                            showEmpty = true
                        } else {
                            showEmpty = false
                        }
                        items(shorts) { ci ->
                            showItems(context = context, ci = ci) { clothDetail() }
                        }
                    } else if (showSweaters) {
                        if (sweaters.isEmpty()) {
                            showEmpty = true
                        } else {
                            showEmpty = false
                        }
                        items(sweaters) { ci ->
                            showItems(context = context, ci = ci) { clothDetail() }
                        }
                    } else if (showJackets) {
                        if (jackets.isEmpty()) {
                            showEmpty = true
                        } else {
                            showEmpty = false
                        }
                        items(jackets) { ci ->
                            showItems(context = context, ci = ci) { clothDetail() }
                        }
//                    } else if (showBlazers) {
//                        if (blazers.isEmpty()) {
//                            showEmpty = true
//                        } else {
//                            showEmpty = false
//                        }
//                        items(blazers) { ci ->
//                            showItems(context = context, ci = ci) { clothDetail() }
//                        }
                    }
                    else if (showSocks) {
                        if (socks.isEmpty()) {
                            showEmpty = true
                        } else {
                            showEmpty = false
                        }
                        items(socks) { ci ->
                            showItems(context = context, ci = ci) { clothDetail() }
                        }
                    } else if (showAccessories) {
                        if (accessories.isEmpty()) {
                            showEmpty = true
                        } else {
                            showEmpty = false
                        }
                        items(accessories) { ci ->
                            showItems(context = context, ci = ci) { clothDetail() }
                        }
                    } else if (showShoes) {
                        if (shoes.isEmpty()) {
                            showEmpty = true
                        } else {
                            showEmpty = false
                        }
                        items(shoes) { ci ->
                            showItems(context = context, ci = ci) { clothDetail() }
                        }
                    }


                }

            }

        }

    }
}

@Composable
fun showItems(context: Context?, ci: ClothingItem?, clothDetail: () -> Unit) {
    ci?.let {
        val painter = painterResource(
            context!!.resources.getIdentifier(
                ci.code,
                "drawable",
                context.packageName
            )
        )
        Image(
            painter = painter,
            contentDescription = "Item",
            modifier = Modifier
                .padding(
                    start = 8.dp,
                    top = 4.dp,
                    bottom = 4.dp,
                    end = 8.dp
                )
                .size(100.dp, 200.dp)
                .clip(MaterialTheme.shapes.large)
                .background(Color.White)
                .clickable {
                    DetailImage.setItem(ci)
                    clothDetail()
                },
            alignment = Alignment.Center
        )
    }
}

fun isEmtry(list: List<Any>) {

}