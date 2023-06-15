package com.example.cs330_p01.common

import android.content.Context
import android.content.Intent
import android.provider.MediaStore
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.Preview
import androidx.camera.view.PreviewView
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.cs330_p01.R

@Composable
fun LogoNHelpCard() {

    Card(
        modifier = Modifier
            .padding(start = 16.dp, top = 16.dp, bottom = 4.dp, end = 16.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ), colors = CardDefaults.cardColors(
            MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f)
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.oblacak_icon),
                contentDescription = "Oblacak Icon",
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 50.dp)
                    .size(90.dp),
                alignment = Alignment.Center
            )
            Image(
                painter = painterResource(id = R.drawable.baseline_question_mark_24),
                contentDescription = "Question mark",
                modifier = Modifier
                    .size(60.dp)
            )
        }
    }
}

@Composable
fun NavigationBar(
    homeScreen: () -> Unit,
    pickerScreen: () -> Unit,
    addScreen: () -> Unit,
    closetScreen: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        BottomAppBar(containerColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f)) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                horizontalArrangement = Arrangement.Center
            ) {
                Spacer(modifier = Modifier.weight(1f))
                Image(
                    painter = painterResource(id = R.drawable.baseline_home_24),
                    contentDescription = "Home button",
                    modifier = Modifier
                        .size(70.dp)
                        .clickable { homeScreen() }
                )
                Spacer(modifier = Modifier.weight(1f))
                Image(
                    painter = painterResource(id = R.drawable.baseline_checklist_rtl_24),
                    contentDescription = "Picker button",
                    modifier = Modifier
                        .size(70.dp)
                        .clickable { pickerScreen() }
                )
                Spacer(modifier = Modifier.weight(1f))
                Image(
                    painter = painterResource(id = R.drawable.baseline_add_circle_outline_24),
                    contentDescription = "Add button",
                    modifier = Modifier
                        .size(70.dp)
                        .clickable { addScreen() }
                )
                Spacer(modifier = Modifier.weight(1f))
                Image(
                    painter = painterResource(id = R.drawable.baseline_all_inbox_24),
                    contentDescription = "Closet button",
                    modifier = Modifier
                        .size(70.dp)
                        .clickable { closetScreen() }
                )
                Spacer(modifier = Modifier.weight(1f))
            }
        }
    }
}

fun openCamera(context: Context) {
    val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
    context.startActivity(intent)
}

@Composable
fun EmptyScreen() {
    Column(
        modifier = Modifier
            .fillMaxWidth().padding(top = 100.dp),
        horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center

    ) {
        Image(
            painter = painterResource(id = R.drawable.oblacak_icon),
            contentDescription = "Oblacak Icon",
            modifier = Modifier
                .size(250.dp),
            alignment = Alignment.Center
        )
        Text(
            text = "Empty :(",
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center,
        )

    }
}