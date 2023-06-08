package com.example.cs330_p01.screens

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.example.cs330_p01.R
import com.example.cs330_p01.common.AppViewModel
import com.example.cs330_p01.common.openCamera


@Composable
fun SplashScreen(viewModel: AppViewModel, paddingValues: PaddingValues) {
    SplashScreenView {
        viewModel.goToHomeScreen()
    }
}

@Composable
fun SplashScreenView(onStart: () -> Unit) {

    var context = LocalContext.current

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

    Image(
        painter = painterResource(id = R.drawable.splashscreen),
        contentDescription = "Background",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.FillWidth
    )
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            text = "Hello!",
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.clickable { onStart() }
        )
        Card(
            modifier = Modifier
                .padding(16.dp, bottom = 140.dp)
                .fillMaxWidth(0.5f),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp
            ), colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f)
            )
        )
        {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.camera_icon),
                    contentDescription = "Camera Icon",
                    modifier = Modifier
                        .size(70.dp)
                        .padding(4.dp)
                        .weight(1f)
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
                        }, alignment = Alignment.CenterStart
                )
                Image(
                    painter = painterResource(id = R.drawable.image_icon),
                    contentDescription = "Image Icon",
                    modifier = Modifier
                        .size(70.dp)
                        .padding(4.dp)
                        .clickable {
                            galleryLauncher.launch("image/*")
                        }, alignment = Alignment.CenterEnd
                )
            }
        }
    }
}

@Preview
@Composable
fun SplashScreenPreview() {
    val viewModel = AppViewModel()
    SplashScreen(viewModel = viewModel, paddingValues = PaddingValues(0.dp))
}