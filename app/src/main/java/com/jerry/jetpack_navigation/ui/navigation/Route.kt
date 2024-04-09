package com.jerry.jetpack_navigation.ui.navigation

sealed class AppRoute(val route: String) {
    data object MainScreen : AppRoute("main_screen")
    data object SomeScreen : AppRoute("some_screen")
}

sealed class MainRoute(val route: String) {
    data object HomeScreen : MainRoute("home_screen")
    data object SettingScreen : MainRoute("setting_screen")
    data object DetailScreen : MainRoute("detail_screen")
    data object FullListScreen: MainRoute("full_list_screen")
}