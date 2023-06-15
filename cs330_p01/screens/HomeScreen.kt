package com.example.cs330_p01.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cs330_p01.ApiData.ApiService
import com.example.cs330_p01.ApiData.Constants.Companion.BASE_URL
import com.example.cs330_p01.R
import com.example.cs330_p01.common.AppViewModel
import com.example.cs330_p01.common.ChoseWeatherIcon
import com.example.cs330_p01.common.GetRecomendedOutfit
import com.example.cs330_p01.common.LogoNHelpCard
import com.example.cs330_p01.common.NavigationBar
import com.example.cs330_p01.database.DBClothes
import com.example.cs330_p01.database.DBWeatherCode
import com.example.cs330_p01.database.WeatherCode
import com.example.cs330_p01.database.WeatherData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: AppViewModel, paddingValues: PaddingValues) {
    Image(
        painter = painterResource(id = R.drawable.background),
        contentDescription = "Background",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.FillWidth
    )

    HomeScreenView()
    NavigationBar(
        { viewModel.goToHomeScreen() },
        { viewModel.goToPickerScreen() },
        { viewModel.goToAddScreen() },
        { viewModel.goToClosetScreen() }
    )
}

@Composable
fun HomeScreenView() {
    Column {
        LogoNHelpCard()
        WeatherCard()

    }
}

@Composable
fun WeatherCard() {
    val contex = LocalContext.current

    var currentTemp by remember {
        mutableStateOf(0.0)
    }
    var nextTemp01 by remember {
        mutableStateOf(0.0)
    }
    var nextTemp02 by remember {
        mutableStateOf(0.0)
    }

    var currentTime by remember {
        mutableStateOf("")
    }
    var nextTime01 by remember {
        mutableStateOf("")
    }
    var nextTime02 by remember {
        mutableStateOf("")
    }

    var currentWeatherCode by remember {
        mutableStateOf(WeatherCode(0, ""))
    }
    var wc01 by remember {
        mutableStateOf(0)
    }
    var wc02 by remember {
        mutableStateOf(0)
    }

    var nextWeatherCode01 by remember {
        mutableStateOf(WeatherCode(0, ""))
    }
    var nextWeatherCode02 by remember {
        mutableStateOf(WeatherCode(0, ""))
    }


    val dbWeatherCode = DBWeatherCode(contex)


    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService = retrofit.create(ApiService::class.java)
    val call =
        apiService.getWeather(44.80, 20.47, "temperature_2m,weathercode", true, "Europe/Berlin")


    call!!.enqueue(object : Callback<WeatherData?> {

        override fun onResponse(
            call: Call<WeatherData?>,
            response: Response<WeatherData?>
        ) {
            if (response.isSuccessful) {
                val weatherData = response.body()
                weatherData?.let {
                    // Toast.makeText(contex, it.current_weather.toString(), Toast.LENGTH_SHORT).show()
                    val temperatureValue = it.current_weather?.temperature
                    if (temperatureValue != null) {
                        currentTemp = temperatureValue
                        currentTime = it.current_weather.time

//                        Toast.makeText(contex, it.hourly.weathercode.toString(), Toast.LENGTH_SHORT)
//                            .show()
                        for (wc in dbWeatherCode.getWeatherCodes()) {
                            if (wc.code == it.current_weather.weathercode) {
                                currentWeatherCode = wc
                            }

                            val weatherList01 = it.hourly.temperature_2m
                            val currentIndexTemp =
                                weatherList01.indexOf(it.current_weather.temperature)
                            if (currentIndexTemp != -4 && currentIndexTemp + 8 < weatherList01.size) {
                                nextTemp01 = weatherList01[currentIndexTemp + 4]
                                nextTemp02 = weatherList01[currentIndexTemp + 8]
                            }

                            val weatherList02 = it.hourly.time
                            val currentIndexTime =
                                weatherList01.indexOf(it.current_weather.temperature)
                            if (currentIndexTime != -4 && currentIndexTime + 8 < weatherList02.size) {
                                nextTime01 = weatherList02[currentIndexTime + 4]
                                nextTime02 = weatherList02[currentIndexTime + 8]
                            }

                            val weatherList03 = it.hourly.weathercode
                            val currentIndexWC =
                                weatherList01.indexOf(it.current_weather.temperature)
                            if (currentIndexWC != -4 && currentIndexWC + 8 < weatherList02.size) {
                                wc01 = weatherList03[currentIndexWC + 4]
                                wc02 = weatherList03[currentIndexWC + 8]
                            }
                        }
                    }
                }
            }
        }

        override fun onFailure(call: Call<WeatherData?>, t: Throwable) {
            //Toast.makeText(contex, "Mao", Toast.LENGTH_SHORT).show()
            TODO("Not yet implemented")
        }
    })

    for (wc in dbWeatherCode.getWeatherCodes()) {
        if (wc.code == wc01) {
            nextWeatherCode01 = wc
        }
        if (wc.code == wc02) {
            nextWeatherCode02 = wc
        }
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
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                item {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(
                            start = 2.dp,
                            top = 8.dp,
                            end = 8.dp,
                            bottom = 8.dp
                        )
                    ) {
                        Image(
                            painter = painterResource(id = ChoseWeatherIcon(currentWeatherCode.code)),
                            contentDescription = "Current Weather Icon",
                            modifier = Modifier
                                .size(150.dp),
                            alignment = Alignment.CenterStart
                        )
                        val result = currentTime.substringAfter("T")
                        Text(
                            text = result,
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = currentWeatherCode.descripton.replace(":", ":\n"),
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = "$currentTemp °C",
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                item {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Image(
                            painter = painterResource(id = ChoseWeatherIcon(nextWeatherCode01.code)),
                            contentDescription = "Current Weather Icon",
                            modifier = Modifier
                                .size(100.dp),
                            alignment = Alignment.CenterStart
                        )
                        val result = nextTime01.substringAfter("T")
                        Text(
                            text = result,
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = nextWeatherCode01.descripton.substringBefore(":"),
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = "$nextTemp01 °C",
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                item {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                    ) {
                        Image(
                            painter = painterResource(id = ChoseWeatherIcon(nextWeatherCode02.code)),
                            contentDescription = "Current Weather Icon",
                            modifier = Modifier
                                .size(100.dp),
                            alignment = Alignment.CenterStart
                        )
                        val result = nextTime02.substringAfter("T")
                        Text(
                            text = result,
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = nextWeatherCode02.descripton.substringBefore(":"),
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = "$nextTemp02 °C",
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,

                            )
                    }
                }
            }
        }
    }
    ClothesCard(currentTemp)
}


@Composable
fun ClothesCard(currenTemp:Double) {

    val context = LocalContext.current

    val dbClothes = DBClothes(context)
    val clothesList = GetRecomendedOutfit(dbClothes,currenTemp)
    //Toast.makeText(context,clothesList.toString(),Toast.LENGTH_SHORT).show()

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
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(2.dp)
            ) {
                items(clothesList) { item ->
                    val painter = painterResource(
                        context.resources.getIdentifier(
                            item.code,
                            "drawable",
                            context.packageName
                        )
                    )

                    Image(
                        painter = painter,
                        contentDescription = "Item Icon",
                        modifier = Modifier
                            .padding(
                                start = 8.dp,
                                top = 4.dp,
                                bottom = 4.dp,
                                end = 8.dp
                            )
                            .size(200.dp)
                            .clip(MaterialTheme.shapes.large)
                            .background(Color.White),
                        alignment = Alignment.Center
                    )
                }
            }
        }
    }
}
