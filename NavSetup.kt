package com.example.cs330_p01.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cs330_p01.common.AppViewModel
import com.example.cs330_p01.screens.AddScreen
import com.example.cs330_p01.screens.ClosetScreen
import com.example.cs330_p01.screens.HomeScreen
import com.example.cs330_p01.screens.PickerScreen
import com.example.cs330_p01.screens.SplashScreen

@Composable
fun NavSetup(navHostController: NavHostController) {
    val vm: AppViewModel = viewModel()
    var paddingValues = PaddingValues()
    vm.navHostController = navHostController

    NavHost(
        navController = navHostController,
        startDestination = NavigationRoutes.SplashScreen.route
    ) {
        composable(route = NavigationRoutes.SplashScreen.route) {
            SplashScreen(vm, paddingValues)
        }
        composable(route = NavigationRoutes.HomeScreen.route) {
            HomeScreen(vm, paddingValues)
        }
        composable(route = NavigationRoutes.PickerScreen.route) {
            PickerScreen(vm, paddingValues)
        }
        composable(route = NavigationRoutes.AddScreen.route) {
            AddScreen(vm, paddingValues)
        }
        composable(route = NavigationRoutes.ClosetScreen.route) {
            ClosetScreen(vm, paddingValues)
        }
    }
}