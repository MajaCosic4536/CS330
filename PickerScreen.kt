package com.example.cs330_p01.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
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

@Composable
fun PickerScreen(viewModel: AppViewModel, paddingValues: PaddingValues) {
    Image(
        painter = painterResource(id = R.drawable.background),
        contentDescription = "Background",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.FillWidth
    )
    PickerScreenView()
    NavigationBar(
        { viewModel.goToHomeScreen() },
        { viewModel.goToPickerScreen() },
        { viewModel.goToAddScreen() },
        { viewModel.goToClosetScreen() }
    )
}

@Composable
fun PickerScreenView() {
    Column {
        LogoNHelpCard()
        OutfitTypeCard()
        ColorFocusScreen()
        ColorScreen()
    }
}

@Composable
fun OutfitTypeCard() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Card(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp
            ), colors = CardDefaults.cardColors(
                MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f)
            )
        ) {
            Image(
                painter = painterResource(id = R.drawable.sunacak),
                contentDescription = "Current Weather Icon",
                modifier = Modifier
                    .size(150.dp),
                alignment = Alignment.CenterStart
            )
        }
    }
}

@Composable
fun ColorFocusScreen() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Card(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp
            ), colors = CardDefaults.cardColors(
                MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f)
            )
        ) {
            Image(
                painter = painterResource(id = R.drawable.sunacak),
                contentDescription = "Current Weather Icon",
                modifier = Modifier
                    .size(150.dp),
                alignment = Alignment.CenterStart
            )
        }
    }
}

@Composable
fun ColorScreen() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Card(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp
            ), colors = CardDefaults.cardColors(
                MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f)
            )
        ) {
            Image(
                painter = painterResource(id = R.drawable.sunacak),
                contentDescription = "Current Weather Icon",
                modifier = Modifier
                    .size(150.dp),
                alignment = Alignment.CenterStart
            )
        }
    }
}
