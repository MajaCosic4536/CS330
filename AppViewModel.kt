package com.example.cs330_p01.common

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.example.cs330_p01.navigation.NavigationRoutes

class AppViewModel : ViewModel() {

    lateinit var navHostController: NavHostController
    var internetGranted = mutableStateOf(false)

    fun goBack() {
        navHostController.popBackStack()
    }

    fun goToHomeScreen() {
        navHostController.navigate(NavigationRoutes.HomeScreen.route)
    }

    fun goToPickerScreen() {
        navHostController.navigate(NavigationRoutes.PickerScreen.route)
    }

    fun goToAddScreen() {
        navHostController.navigate(NavigationRoutes.AddScreen.route)
    }

    fun goToClosetScreen() {
        navHostController.navigate(NavigationRoutes.ClosetScreen.route)
    }

}