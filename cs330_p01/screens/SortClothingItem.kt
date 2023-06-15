package com.example.cs330_p01.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cs330_p01.R
import com.example.cs330_p01.common.AppViewModel
import com.example.cs330_p01.common.DetailImage
import com.example.cs330_p01.common.EditClothingItem
import com.example.cs330_p01.common.LogoNHelpCard
import com.example.cs330_p01.common.NavigationBar
import com.example.cs330_p01.database.ClothingItem
import com.example.cs330_p01.database.DBCategory
import com.example.cs330_p01.database.DBClothes
import com.example.cs330_p01.database.getMyColorList
import com.example.cs330_p01.database.getSortingCategory

@Composable
fun SortClothingItem(viewModel: AppViewModel, paddingValues: PaddingValues) {
    Image(
        painter = painterResource(id = R.drawable.background),
        contentDescription = "Background",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.FillWidth
    )
    SortClothingItemView(viewModel = viewModel)
    NavigationBar({ viewModel.goToHomeScreen() },
        { viewModel.goToPickerScreen() },
        { viewModel.goToAddScreen() },
        { viewModel.goToClosetScreen() })
}

@Composable
fun SortClothingItemView(viewModel: AppViewModel) {
    Column {
        LogoNHelpCard()
        ItemsInCard(
            { viewModel.goBack() },
            { viewModel.goToClosetScreen() })
    }
}

@Composable
fun ItemsInCard(goBack: () -> Unit, goFoward: () -> Unit) {

    val context = LocalContext.current

    var detailImage = DetailImage.getItem()
    var showEditOptions by remember {
        mutableStateOf(false)
    }
    EditClothingItem.setCode(detailImage.code)
    EditClothingItem.setName(detailImage.name)

    val dbClothes = DBClothes(context = context)
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Card(
            modifier = Modifier
                .padding(start = 16.dp, top = 2.dp, bottom = 90.dp, end = 16.dp)
                .fillMaxSize(), elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp
            ), colors = CardDefaults.cardColors(
                MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f)
            )
        ) {
            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                item {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Button(onClick = { goBack() }) {
                            Icon(Icons.Filled.ArrowBack, contentDescription = "Back arrow")
                            Text(
                                text = "Back",
                                color = MaterialTheme.colorScheme.onPrimary,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold,
                            )
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        Button(onClick = { goFoward() }) {
                            Text(
                                text = "Done",
                                color = MaterialTheme.colorScheme.onPrimary,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold,
                            )
                            Icon(Icons.Filled.ArrowForward, contentDescription = "Next arrow")
                        }
                    }
                }
                item {
                    Card(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth(),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 8.dp
                        ),
                        colors = CardDefaults.cardColors(
                            MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f)
                        )
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = detailImage.name,
                                color = MaterialTheme.colorScheme.onPrimaryContainer,
                                fontSize = 35.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
                item {
                    Box() {
                        Image(
                            painter = painterResource(
                                context.resources.getIdentifier(
                                    detailImage.code,
                                    "drawable",
                                    context.packageName
                                )
                            ),
                            contentDescription = "Background",
                            modifier = Modifier
                                .height(300.dp)
                                .fillMaxWidth()
                                .padding(8.dp)
                                .clip(MaterialTheme.shapes.large)
                                .background(Color.White)
                        )
                        FloatingActionButton(
                            onClick = {
                                showEditOptions = !showEditOptions
                            },
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .padding(16.dp)
                        ) {
                            Icon(Icons.Filled.Edit, contentDescription = "Edit item")
                        }
                    }
                }
                item {
                    Card(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth(),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 8.dp
                        ),
                        colors = CardDefaults.cardColors(
                            MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f)
                        )
                    ) {
                        if (!showEditOptions) {
                            Column(
                                modifier = Modifier.padding(
                                    start = 16.dp,
                                    top = 16.dp,
                                    bottom = 2.dp,
                                    end = 16.dp
                                )
                            ) {
                                Text(
                                    text = "Type: ${detailImage.itemType}",
                                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    textAlign = TextAlign.Left
                                )
                                Text(
                                    text = "Category: ${detailImage.category}",
                                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    textAlign = TextAlign.Left
                                )
                                Text(
                                    text = "Color: ${detailImage.color}",
                                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    textAlign = TextAlign.Left
                                )
                            }
                        }
                    }
                }
                if (showEditOptions) {
                    item {
                        ItemtypeCard()
                    }
                    item {
                        SortingOutfitCategoryCard()
                    }
                    item {
                        SortingColorCard(radioOrCheckBox = ColorFocusCard(text = "Is this item a single color or more?"))
                    }
                    item {
                        Button(onClick = {
                            dbClothes.updateClothingItem(EditClothingItem.getItem())
                            goFoward()
                            EditClothingItem.clothingItem = ClothingItem("", "", "", "", false, "")
                        }) {
                            Text(
                                text = "Update",
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

}

@Composable
fun SortingOutfitCategoryCard() {
    val context = LocalContext.current

    val dbCategory = DBCategory(context)
    Box(
        modifier = Modifier
            .fillMaxWidth(), contentAlignment = Alignment.Center
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
                text = "In which category does this item belong?",
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )

            Column() {
                dbCategory.getCategories().forEach { category ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        var checkState by remember {
                            mutableStateOf(false)
                        }

                        Checkbox(checked = checkState, onCheckedChange = { isChecked ->
                            checkState = isChecked
                            if (EditClothingItem.getItem().category != "") {
                                EditClothingItem.setCategory(EditClothingItem.getItem().category + "/" + category.name)
                            } else {
                                EditClothingItem.setCategory(category.name)
                            }

                        })
                        Text(
                            text = category.name,
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 20.sp,
                        )
                    }
                }
            }
        }
    }

}

@Composable
fun ItemtypeCard() {
    var expanded by remember {
        mutableStateOf(false)
    }

    val radioChoices = getSortingCategory()
    var selectedChoice by remember {
        mutableStateOf(radioChoices[0])
    }


    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Card(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .clickable { expanded = !expanded },
            elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp
            ),
            colors = CardDefaults.cardColors(
                MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f)
            )
        ) {
            Text(
                text = "What type is this clothing item?",
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

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
                        EditClothingItem.setItemtype(choice)
                    })
                    Text(
                        text = choice,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 20.sp
                    )

                }
            }
        }
    }
}

@Composable
fun SortingColorCard(radioOrCheckBox: Boolean) {
    val context = LocalContext.current

    var expanded by remember {
        mutableStateOf(false)
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
                Column() {
                    radioChoices.forEach { myColor ->
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            var value: Boolean
                            if (selectedChoice == myColor) {
                                value = true
                            } else {
                                value = false
                            }
                            RadioButton(selected = value, onClick = {
                                selectedChoice = myColor
                                EditClothingItem.setColor(myColor)
                            })
                            Text(
                                text = myColor,
                                color = MaterialTheme.colorScheme.onPrimaryContainer,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 20.sp
                            )
                        }
                    }
                }
            } else {
                Column() {
                    getMyColorList().forEach { myColor ->
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            var checkState by remember {
                                mutableStateOf(false)
                            }

                            Checkbox(checked = checkState, onCheckedChange = { isChecked ->
                                checkState = isChecked
                                if (EditClothingItem.getItem().color != "") {
                                    EditClothingItem.setColor(EditClothingItem.getItem().color + "/" + myColor)
                                } else {
                                    EditClothingItem.setColor(myColor)
                                }
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

