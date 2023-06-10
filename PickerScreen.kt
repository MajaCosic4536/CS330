package com.example.cs330_p01.screens

import android.graphics.Color
import android.widget.RadioGroup
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cs330_p01.R
import com.example.cs330_p01.common.AppViewModel
import com.example.cs330_p01.common.LogoNHelpCard
import com.example.cs330_p01.common.NavigationBar
import com.example.cs330_p01.database.Category
import com.example.cs330_p01.database.DBCategory
import com.example.cs330_p01.database.DBColor
import com.example.cs330_p01.database.MyColor
import com.example.cs330_p01.database.getMyColorList


@Composable
fun PickerScreen(viewModel: AppViewModel, paddingValues: PaddingValues) {
    Image(
        painter = painterResource(id = R.drawable.background),
        contentDescription = "Background",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.FillWidth
    )
    PickerScreenView(viewModel)
//    NavigationBar(
//        { viewModel.goToHomeScreen() },
//        { viewModel.goToPickerScreen() },
//        { viewModel.goToAddScreen() },
//        { viewModel.goToClosetScreen() }
//    )
}

@Composable
fun PickerScreenView(viewModel: AppViewModel) {
//    LazyColumn(modifier = Modifier.fillMaxSize()){
//        item {
//            OutfitTypeCard()
//        }
//        item {
//            ColorFocusScreen()
//        }
//        item {
//            ColorScreen()
//        }
//
//    }
    Column {
        // LogoNHelpCard()
        OutfitTypeCard()
        ColorScreen(ColorFocusScreen())
        NavigationBar(
            { viewModel.goToHomeScreen() },
            { viewModel.goToPickerScreen() },
            { viewModel.goToAddScreen() },
            { viewModel.goToClosetScreen() }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OutfitTypeCard() {

    val context = LocalContext.current

    val dbCategory = DBCategory(context)
    var txtFieldValue by remember {
        mutableStateOf("")
    }
    var showAddCategory by remember {
        mutableStateOf(false)
    }
    var showDeleteCategory by remember {
        mutableStateOf(false)
    }
    var tempKey by remember {
        mutableStateOf(0)
    }

    var expanded by remember {
        mutableStateOf(false)
    }
    var standardItemCount = 3


    var categorySize = dbCategory.getCategories().take(standardItemCount)

    if (expanded) {
        categorySize = dbCategory.getCategories()
    }

    if (showAddCategory) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.5f))
                .clickable { showAddCategory = false }
        ) {
            Card(
                modifier = Modifier
                    .width(300.dp)
                    .align(Alignment.Center),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 8.dp
                ),
                colors = CardDefaults.cardColors(
                    MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f)
                )
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "Add category:",
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center
                    )

                    TextField(
                        value = txtFieldValue, onValueChange = { newValue ->
                            txtFieldValue = newValue
                        }, colors = TextFieldDefaults.textFieldColors(
                            textColor = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    )

                    Row(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                    ) {
                        Button(onClick = {
                            showAddCategory = false
                        }, modifier = Modifier.padding(4.dp)) {
                            Text(
                                text = "Cancel",
                                color = MaterialTheme.colorScheme.onPrimary,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold,
                            )
                        }
                        Button(onClick = {
                            dbCategory.insertCategory(txtFieldValue)
                            showAddCategory = false
                        }, modifier = Modifier.padding(4.dp)) {
                            Text(
                                text = "Add",
                                color = MaterialTheme.colorScheme.onPrimary,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold,
                            )
                        }
                    }
                }
            }
        }
    }
    if (showDeleteCategory) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.5f))
                .clickable { showDeleteCategory = false }
        ) {
            Card(
                modifier = Modifier
                    .width(300.dp)
                    .align(Alignment.Center),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 8.dp
                ),
                colors = CardDefaults.cardColors(
                    MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f)
                )
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "Do you want to delete this category?",
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center
                    )

                    Row(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                    ) {
                        Button(onClick = {
                            showDeleteCategory = false
                        }, modifier = Modifier.padding(4.dp)) {
                            Text(
                                text = "No",
                                color = MaterialTheme.colorScheme.onPrimary,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold,
                            )
                        }
                        Button(onClick = {
                            dbCategory.deleteCategory(tempKey)

                            showDeleteCategory = false
                        }, modifier = Modifier.padding(4.dp)) {
                            Text(
                                text = "Yes",
                                color = MaterialTheme.colorScheme.onPrimary,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold,
                            )
                        }
                    }
                }
            }
        }
    }


    LogoNHelpCard()
    Box(
        modifier = Modifier
            .fillMaxWidth(), contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .clickable { expanded = !expanded },
            elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp
            ), colors = CardDefaults.cardColors(
                MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f)
            )
        ) {
            Text(
                text = "What type of wardrobe do you wish to wear?",
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )

            LazyColumn() {
                items(categorySize) { category ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        var checkState by remember {
                            mutableStateOf(false)
                        }

                        Checkbox(checked = checkState, onCheckedChange = { isChecked ->
                            checkState = isChecked
                        })
                        Text(
                            text = category.name,
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 20.sp,
                            modifier = Modifier.clickable {
                                tempKey = category.key
                                showDeleteCategory = true
                            }
                        )
                    }
                }
            }
        }

        FloatingActionButton(
            onClick = {
                showAddCategory = true
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(30.dp)
        ) {
            Icon(Icons.Filled.Add, contentDescription = "Add category")
        }
    }
}

@Composable
fun ColorFocusScreen(): Boolean {
    val context = LocalContext.current
    val radioChoices = listOf("Single", "More")
    var radioOrCheckBox by remember {
        mutableStateOf(true)
    }

    var selectedChoice by remember {
        mutableStateOf(radioChoices[0])
    }

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
            Text(
                text = "Shoud the focus be ona single color or more?",
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Row() {
                radioChoices.forEach { choice ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        var value: Boolean
                        if (selectedChoice == choice) {
                            value = true
                        } else {
                            value = false
                        }
                        RadioButton(selected = value, onClick = {
                            selectedChoice = choice
                            if (selectedChoice == "Single") {
                                radioOrCheckBox = true
                            } else {
                                radioOrCheckBox = false
                            }
                        })
                        Text(
                            text = choice, color = MaterialTheme.colorScheme.onPrimaryContainer,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 20.sp
                        )
                    }
                }
            }
        }
    }

    return radioOrCheckBox
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColorScreen(radioOrCheckBox: Boolean) {
    val context = LocalContext.current
    val dbColor = DBColor(context)
    var txtFieldValue by remember {
        mutableStateOf("")
    }
    var showAddColor by remember {
        mutableStateOf(false)
    }

    var expanded by remember {
        mutableStateOf(false)
    }
    var standardItemCount = 4


    var colorSize = getMyColorList().take(standardItemCount)

    if (expanded) {
        colorSize = getMyColorList()
    }

    val radioChoices = getMyColorList()
    var selectedChoice by remember {
        mutableStateOf(radioChoices[0])
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Card(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .clickable { expanded = !expanded },
            elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp
            ), colors = CardDefaults.cardColors(
                MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f)
            )
        ) {
            Text(
                text = "Chose a color/colors.",
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            if (radioOrCheckBox) {
                LazyColumn() {
                    items(colorSize) { choice ->
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            var value: Boolean
                            if (selectedChoice == choice) {
                                value = true
                            } else {
                                value = false
                            }
                            RadioButton(selected = value, onClick = {
                                selectedChoice = choice
                            })
                            Text(
                                text = choice, color = MaterialTheme.colorScheme.onPrimaryContainer,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 20.sp
                            )
                        }
                    }
                }
            } else {
                LazyColumn() {
                    items(colorSize) { myColor ->
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            var checkState by remember {
                                mutableStateOf(false)
                            }

                            Checkbox(checked = checkState, onCheckedChange = { isChecked ->
                                checkState = isChecked
                            })
                            Text(
                                text = myColor,
                                color = MaterialTheme.colorScheme.onPrimaryContainer,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }
            }
        }
    }
}

