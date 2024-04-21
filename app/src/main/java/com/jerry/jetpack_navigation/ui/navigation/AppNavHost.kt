package com.jerry.jetpack_navigation.ui.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.jerry.jetpack_navigation.ui.feature.dashboard.DashBoardNavHost

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController(),
){
    NavHost(
        modifier = Modifier
            .fillMaxSize(),
        navController = navController,
        startDestination = Screen.Auth.route
    ){
        navigation(
            route = Screen.Auth.route,
            startDestination = Screen.Auth.LoginScreen.route,
        ) {
            composable(route = Screen.Auth.LoginScreen.route) {
                Column {
                    Text("this is Login Screen")
                    Button(onClick = {
                        navController.navigate(Screen.Auth.RegisterScreen.route)
                    } ) {
                        Text(text = "Go to Register Screen")
                    }

                    Button(onClick = {
                        navController.navigate(Screen.Dashboard.route) {
                            popUpTo(Screen.Auth.route) {
                                inclusive = true
                            }
                        }
                    } ) {
                        Text(text = "Login -> goto app main page")
                    }
                }
            }
            composable(route = Screen.Auth.RegisterScreen.route) {
                Button(onClick = {
                    navController.popBackStack()
                } ) {
                    Text(text = "This is Register Screen, Back to login Screen")
                }

            }
        }

        composable(route = Screen.Dashboard.route){
            DashBoardNavHost()
        }
    }
}

