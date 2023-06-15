package com.example.cs330_p01.navigation

sealed class NavigationRoutes(val route: String) {
    object SplashScreen : NavigationRoutes("splash")
    object HomeScreen : NavigationRoutes("home")
    object PickerScreen : NavigationRoutes("picker")
    object AddScreen : NavigationRoutes("add")
    object ClosetScreen : NavigationRoutes("closet")
    object SortClothingItem : NavigationRoutes("sort")
}