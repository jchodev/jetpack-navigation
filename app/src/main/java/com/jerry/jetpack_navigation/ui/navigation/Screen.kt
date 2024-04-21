package com.jerry.jetpack_navigation.ui.navigation


sealed class Screen(val route: String) {

    data object Auth: Screen("auth"){
        data object LoginScreen : Screen("login")
        data object RegisterScreen : Screen("register")
    }

    data object Dashboard: Screen("dashboard"){
        data object HomeScreen: Screen("home")
        data object SettingScreen: Screen("setting")
        data object DialogA: Screen("dialogA")
        data object DialogB: Screen("dialogB")
    }
}