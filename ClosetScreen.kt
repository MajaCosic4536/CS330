package com.example.cs330_p01.screens

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.cs330_p01.R
import com.example.cs330_p01.common.AppViewModel
import com.example.cs330_p01.common.LogoNHelpCard
import com.example.cs330_p01.common.NavigationBar
import com.example.cs330_p01.database.getSortingCategory

@Composable
fun ClosetScreen(viewModel: AppViewModel, paddingValues: PaddingValues) {
    Image(
        painter = painterResource(id = R.drawable.background),
        contentDescription = "Background",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.FillWidth
    )
    ClosetScreenView()
    NavigationBar(
        { viewModel.goToHomeScreen() },
        { viewModel.goToPickerScreen() },
        { viewModel.goToAddScreen() },
        { viewModel.goToClosetScreen() }
    )
}

@Composable
fun ClosetScreenView() {
    Column {
        LogoNHelpCard()
        AllClothesCard()
    }
}

@Composable
fun AllClothesCard() {
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
                MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f)
            )
        ) {
            Column() {
                LazyRow(
                    modifier = Modifier.padding(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    items(getSortingCategory()) { category ->
                        Button(onClick = { /*TODO*/ }) {
                            Text(text = category)
                        }
                    }
                }
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(
                        4.dp
                    )
                ) {
                    items(6) {
                        Image(
                            painter = painterResource(id = R.drawable.clothingitem1),
                            contentDescription = "Oblacak Icon",
                            modifier = Modifier
                                .padding(
                                    start = 2.dp,
                                    top = 8.dp,
                                    bottom = 8.dp,
                                    end = 2.dp
                                )
                                .weight(1f)
                                .size(350.dp),
                            alignment = Alignment.Center
                        )
                    }
                }
            }
        }
    }
}