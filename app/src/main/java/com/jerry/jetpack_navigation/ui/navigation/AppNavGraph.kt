package com.jerry.jetpack_navigation.ui.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavGraph(
    appNavController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = appNavController,
        startDestination = AppRoute.MainScreen.route
    ) {
        composable(route = AppRoute.MainScreen.route) {
            MainNavGraph(
                appNavController = appNavController
            )
        }
        composable(route = AppRoute.SomeScreen.route) {
            Box(modifier = Modifier.fillMaxSize()){
                Text(text = "this is some screen")
            }
        }
    }
}