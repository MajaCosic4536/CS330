package com.example.cs330_p01.screens

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import coil.compose.rememberImagePainter
import com.example.cs330_p01.R
import com.example.cs330_p01.common.AppViewModel
import com.example.cs330_p01.common.LoadedImage
import com.example.cs330_p01.common.LogoNHelpCard
import com.example.cs330_p01.common.NavigationBar
import com.example.cs330_p01.common.openCamera

@Composable
fun AddScreen(viewModel: AppViewModel, paddingValues: PaddingValues) {
    Image(
        painter = painterResource(id = R.drawable.background),
        contentDescription = "Background",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.FillWidth
    )

    AddScreenView(LocalContext.current)
    NavigationBar(
        { viewModel.goToHomeScreen() },
        { viewModel.goToPickerScreen() },
        { viewModel.goToAddScreen() },
        { viewModel.goToClosetScreen() }
    )
}


@Composable
fun AddScreenView(context: Context) {
    Column {
        LogoNHelpCard()
        CamNGalleryCard(context)
    }
}

@Composable
fun CamNGalleryCard(context: Context) {

    val cameraLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                openCamera(context)
            } else {
                Toast.makeText(context, "Camera permission denied", Toast.LENGTH_SHORT).show()
            }
        }

    val galleryLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            if (uri != null) {
                Toast.makeText(context, "Selected image: $uri", Toast.LENGTH_SHORT).show()

            } else {
                Toast.makeText(context, "Failed to get image from gallery", Toast.LENGTH_SHORT)
                    .show()
            }
        }

    Box(
        modifier = Modifier
            .fillMaxSize()
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
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.weight(1f))
                Image(
                    painter = painterResource(id = R.drawable.camera_icon),
                    contentDescription = "Camera Icon",
                    modifier = Modifier
                        .size(200.dp)
                        .padding(4.dp)
                        .clickable {
                            if (ContextCompat.checkSelfPermission(
                                    context,
                                    Manifest.permission.CAMERA
                                ) == PackageManager.PERMISSION_GRANTED
                            ) {
                                openCamera(context)
                            } else {
                                cameraLauncher.launch(Manifest.permission.CAMERA)
                            }
                        }
                )
                Spacer(modifier = Modifier.weight(1f))
                Image(
                    painter = painterResource(id = R.drawable.image_icon),
                    contentDescription = "Image Icon",
                    modifier = Modifier
                        .size(200.dp)
                        .padding(4.dp)
                        .clickable {
                            galleryLauncher.launch("image/*")
                        }
                )
                Spacer(modifier = Modifier.weight(1f))
            }
        }
    }
}




